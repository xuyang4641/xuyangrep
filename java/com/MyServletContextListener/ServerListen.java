package com.MyServletContextListener;
import java.io.*;
import java.net.Socket;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;

import java.sql.Connection;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
//import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import com.entity.DevinfoEntity;
import com.mysql.cj.jdbc.MysqlDataSource;
import com.service.DevinfoService;
import org.json.JSONObject;
import javax.sql.DataSource;

public class ServerListen  implements Runnable{
    private Socket socketListen;

    private int HBcnt=0;
    Timer timer;
    private static Lock lock;
    BufferedReader readFromClient;

    public ServerListen(Socket socketListen) {
        this.socketListen = socketListen;
        this.lock = new ReentrantLock();
    }

    public static String stringToHex(String str) {
        byte[] bytes = str.getBytes();
        StringBuilder hex = new StringBuilder();
        for (byte b : bytes) {
            hex.append(String.format("%02x", b));
        }
        return hex.toString();
    }
    @Override
    public void run()
    {
        DataOutputStream outToClient=null;
        DevinfoEntity yuangong=new DevinfoEntity<>();

        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                if(HBcnt >= 3){
                    try {
                        socketListen.shutdownInput();
                        socketListen.shutdownOutput();
                        socketListen.close();
                        socketListen=null;
                        readFromClient=null;
                    }catch (IOException e){
                        e.printStackTrace();
                    }
                }
                else{
                    lock.lock();
                    HBcnt++;
                    lock.unlock();
                }
                System.out.println("timer HB="+HBcnt);
            }
        }, 1000, 60000);

        String result="";
        try {
            readFromClient = new BufferedReader(new InputStreamReader(socketListen.getInputStream()));
            while (true) {
                String fromClientSentences = readFromClient.readLine();
                System.out.println(stringToHex(fromClientSentences));

                if(stringToHex(fromClientSentences.substring(0,2)) == "1007"){
                    lock.lock();
                    HBcnt = 0;
                    lock.unlock();
                    System.out.println("rev HB="+HBcnt);
                }
                else if(stringToHex(fromClientSentences.substring(0,2)) == "0806"){

                }
                else if(fromClientSentences != null){
                   System.out.println(fromClientSentences);
                    result = result + fromClientSentences;
                    System.out.println(result);
                    DataSource dataSource = new MysqlDataSource();
                    ((MysqlDataSource)dataSource).setURL("jdbc:mysql://127.0.0.1:3306/ssmp302b?useUnicode=true&characterEncoding=UTF-8&tinyInt1isBit=false&serverTimezone=GMT%2B8");
                    ((MysqlDataSource)dataSource).setUser("root");
                    ((MysqlDataSource)dataSource).setPassword("root");
                    //建立连接
                    Connection connection = dataSource.getConnection();
                    String sql="insert into yuangong values(?,?,?,?,?,?,?,?,?)";
                    // jdbc 中还需要搭配一个特定的对象, 来描述这里的 sql 的情况
                    PreparedStatement statement= connection.prepareStatement(sql);
                    JSONObject josonObject = new JSONObject(result);
                    statement.setLong(1,new Date().getTime()+new Double(Math.floor(Math.random()*1000)).longValue());
                    statement.setTimestamp(2,new Timestamp(System.currentTimeMillis()));
                    statement.setString(3,josonObject.getString("gonghao"));
                    statement.setString(4,josonObject.getString("mima"));
                    statement.setString(5,josonObject.getString("yuangongxingming"));
                    statement.setString(6,josonObject.getString("xingbie"));
                    statement.setString(7,josonObject.getString("bumen"));
                    statement.setString(8,josonObject.getString("shouji"));
                    statement.setString(9,josonObject.getString("youxiang"));
                    System.out.println("sql: " + statement);
                    boolean ret=statement.execute();
                    System.out.println("ret="+ret);
                    //5、断开连接, 释放资源
                    statement.close();
                    connection.close();
                }
            }
        }catch (IOException e){
            e.printStackTrace();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if(readFromClient!=null){
                    readFromClient.close();
                }
            }catch (IOException e){
                e.printStackTrace();
            }
            try {
                if(outToClient!=null){
                    outToClient.close();
                }
            }catch (IOException e){
                e.printStackTrace();
            }
            timer.cancel();
            timer=null;
            HBcnt=0;
            System.out.println("run finally");
        }
    }
}
