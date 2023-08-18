package com.MyServletContextListener;
import java.io.*;
import java.net.Socket;
import java.net.URL;
import java.net.URLConnection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Logger;

import java.sql.Connection;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
//import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import com.entity.DevinfoEntity;
import com.mysql.cj.jdbc.MysqlDataSource;
import com.service.DevinfoService;
import com.websocket.WebSocketTest;
import org.json.JSONArray;
import org.json.JSONObject;
import javax.sql.DataSource;

import org.python.core.PyFunction;
import org.python.core.PyInteger;
import org.python.core.PyObject;
import org.python.util.PythonInterpreter;

import com.websocket.WebSocketTest;

import static com.websocket.WebSocketTest.webSocketSet;

public class ServerListen  implements Runnable{
    private Socket socketListen;
    private static final Logger LOGGER = Logger.getLogger(ServerListen.class.getName());
    private int HBcnt=0;
    Timer timer;
    private static Lock lock;
    BufferedReader readFromClient;
    PrintWriter sendToClient;

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
        //DevinfoEntity yuangong=new DevinfoEntity<>();

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
            PrintWriter sendToClient = new PrintWriter(socketListen.getOutputStream(), true);
            while (true) {
                String fromClientSentences = readFromClient.readLine();
                System.out.println(stringToHex(fromClientSentences));
                LOGGER.info("run here");
                JSONObject jsendObj = new JSONObject();
                JSONObject josonObject = new JSONObject(fromClientSentences);
                if(josonObject.getString("instructionCode").equals("reportHeartbeat")){
                    lock.lock();
                    HBcnt = 0;
                    lock.unlock();
                    System.out.println("rev HB="+HBcnt);
                    jsendObj.put("robotId",josonObject.getString("robotId"));
                    jsendObj.put("instructionCode","replyHeartbeat");
                    jsendObj.put("instructionNo",josonObject.getString("instructionNo"));
                    sendToClient.println(jsendObj.toString());
                }
                else if(josonObject.getString("instructionCode").equals("uploadImg")){
                    String robotId = josonObject.getString("robotId");
                    String instructionNo = josonObject.getString("instructionNo");
                    int pkgTotal = josonObject.getInt("pkgTotal");
                    int pkgNum = josonObject.getInt("pkgNum");
                    JSONArray imgList = josonObject.getJSONArray("imgList");
                    Calendar calendar = Calendar.getInstance();
                    String savePath = String.format("D:\\%d-%d-%d", calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH) + 1,calendar.get(Calendar.DAY_OF_MONTH));
                    File file = new File(savePath);
                    if(!file.exists()){
                        if(file.mkdir()){
                            System.out.println("创建目录成功！");
                        }else{
                            System.out.println("创建目录失败！");
                        }
                    }
                    for (int i = 0; i < imgList.length();i++){
                        String imgId = imgList.getJSONObject(i).getString("imgId");
                        String imageUrl = imgList.getJSONObject(i).getString("imageUrl");
                        URL url = new URL(imageUrl);
                        URLConnection connection = url.openConnection();
                        // 设置请求超时为5秒
                        connection.setConnectTimeout(5 * 1000);
                        // 读取数据流并保存到本地
                        InputStream input = connection.getInputStream();
                        byte[] data = new byte[1024];
                        int len;
                        FileOutputStream output = new FileOutputStream(savePath+"\\"+imgId);
                        while ((len = input.read(data)) != -1) {
                            output.write(data, 0, len);
                        }
                        output.close();
                        input.close();
                        System.out.println("图片保存成功");
                    }
                    jsendObj.put("robotId",josonObject.getString("robotId"));
                    jsendObj.put("instructionCode","replyUploadImg");
                    jsendObj.put("instructionNo",josonObject.getString("instructionNo"));
                    sendToClient.println(jsendObj.toString());
                    if(pkgTotal == pkgNum+1){
                        PythonInterpreter interpreter = new PythonInterpreter();
                        interpreter.execfile("./plus.py");
                        PyFunction pyFunction = interpreter.get("add", PyFunction.class);
                        int a = 5, b = 10;
                        //调用函数，如果函数需要参数，在Java中必须先将参数转化为对应的“Python类型”
                        PyObject pyobj = pyFunction.__call__(new PyInteger(a), new PyInteger(b));

                        JSONObject jsendToTerm = new JSONObject();
                        for(WebSocketTest item: webSocketSet){
                            try {
                                if(item.getRobotId()==robotId){
                                    jsendToTerm.put("terminalId",item.getTerminalId());
                                    jsendToTerm.put("robotId",robotId);
                                    jsendToTerm.put("instructionCode","replyWarningList");
                                    jsendToTerm.put("instructionNo",Long.toString(System.currentTimeMillis())+Long.toString(Math.round(Math.random() * (9999999 - 1000000) + 1) ));
                                    jsendToTerm.put("alarmCount",13);
                                    item.sendMessage(jsendToTerm.toString());
                                }
                            } catch (IOException e) {

                                e.printStackTrace();

                                continue;

                            }

                        }
                    }

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
