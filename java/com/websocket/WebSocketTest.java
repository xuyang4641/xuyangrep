package com.websocket;

import com.mysql.cj.jdbc.MysqlDataSource;
import com.mysql.cj.protocol.Resultset;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;

import java.math.BigDecimal;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.CopyOnWriteArraySet;

import javax.sql.DataSource;
import javax.websocket.*;
import java.util.*;
import javax.websocket.server.ServerEndpoint;
/**

 * @ServerEndpoint 注解是一个类层次的注解，它的功能主要是将目前的类定义成一个websocket服务器端,

 * 注解的值将被用于监听用户连接的终端访问URL地址,客户端可以通过这个URL来连接到WebSocket服务器端

 */

@ServerEndpoint("/websocket")

public class WebSocketTest {

    //静态变量，用来记录当前在线连接数。应该把它设计成线程安全的。

    private static int onlineCount = 0;

    //concurrent包的线程安全Set，用来存放每个客户端对应的MyWebSocket对象。

    // 若要实现服务端与单一客户端通信的话，可以使用Map来存放，其中Key可以为用户标识

    private static CopyOnWriteArraySet<WebSocketTest> webSocketSet = new CopyOnWriteArraySet<WebSocketTest>();

    //与某个客户端的连接会话，需要通过它来给客户端发送数据

    private Session session;
    private long index=0;
    private int pageSize=30;
    private String terminalId;
    private String robotId;

    private String devid="123";

    /**

     * 连接建立成功调用的方法

     * @param session  可选的参数。session为与某个客户端的连接会话，需要通过它来给客户端发送数据

     */

    @OnOpen

    public void onOpen(Session session){

        this.session = session;

        webSocketSet.add(this);     //加入set中

        addOnlineCount();           //在线数加1

        System.out.println("有新连接加入！当前在线人数为" + getOnlineCount());

    }

    /**

     * 连接关闭调用的方法

     */

    @OnClose

    public void onClose(){

        webSocketSet.remove(this);  //从set中删除

        subOnlineCount();           //在线数减1

        System.out.println("有一连接关闭！当前在线人数为" + getOnlineCount());

    }

    /**

     * 收到客户端消息后调用的方法

     * @param message 客户端发送过来的消息

     * @param session 可选的参数

     */

    @OnMessage

    public void onMessage(String message, Session session) throws SQLException {
        Timestamp startTime,endTime;
        System.out.println("来自客户端的消息:" + message);

        JSONObject josonObject = new JSONObject(message);
        JSONObject jsendObj = new JSONObject();
        JSONArray jsonArray = new JSONArray();
        JSONObject innerJsonObject = new JSONObject();

        if(josonObject.has("instructionCode")){
            if(josonObject.getString("instructionCode").equals("getWarningList")){
                System.out.println("run2");
                DataSource dataSource = new MysqlDataSource();
                ((MysqlDataSource)dataSource).setURL("jdbc:mysql://127.0.0.1:3306/ssmp302b?useUnicode=true&characterEncoding=UTF-8&tinyInt1isBit=false&serverTimezone=GMT%2B8");
                ((MysqlDataSource)dataSource).setUser("root");
                ((MysqlDataSource)dataSource).setPassword("root");
                System.out.println("run3");
                //建立连接
                Connection connection = dataSource.getConnection();
                //String sql="select * from alarmlist ORDER BY id DESC LIMIT ?,30";
                // jdbc 中还需要搭配一个特定的对象, 来描述这里的 sql 的情况
                PreparedStatement statement= connection.prepareStatement("select * from alarmlist where devid="+devid+" ORDER BY id DESC LIMIT "+index+","+pageSize);
                System.out.println("run4");
                ResultSet resultSet =statement.executeQuery();
                resultSet.beforeFirst();
                while(resultSet.next()){
                    System.out.println("id: " + resultSet.getLong("id"));
                    System.out.println("alarmid: " + resultSet.getString("alarmid"));
                    System.out.println("alarmtype: " + resultSet.getString("alarmtype"));
                    innerJsonObject.put("alarmId",resultSet.getString("alarmid"));
                    innerJsonObject.put("trainNum",resultSet.getString("trainid"));
                    innerJsonObject.put("fleetNum","0");
                    innerJsonObject.put("carNum",resultSet.getString("carriageno"));
                    innerJsonObject.put("faultType",resultSet.getString("alarmtype"));
                    innerJsonObject.put("faultLevel",resultSet.getString("alarmlevel"));
                    innerJsonObject.put("detectionPart",resultSet.getString("position"));
                    innerJsonObject.put("detectionArea",resultSet.getString("region"));
                    innerJsonObject.put("detectionTime",resultSet.getTimestamp("addtime"));
                    innerJsonObject.put("reportingTime",new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
                    innerJsonObject.put("imageId",resultSet.getString("src"));
                    jsonArray.put(innerJsonObject);
                }
                jsendObj.put("terminalId","f487c56f29eb");
                jsendObj.put("robotId","5cea1e22f112");
                jsendObj.put("instructionCode","replyWarningList");
                jsendObj.put("instructionNo","16446582605221234784");
                jsendObj.put("warningList",jsonArray);
            }
            else if(josonObject.getString("instructionCode").equals("reportLoginInfo")){
                String instructionNo;
                instructionNo = josonObject.getString("instructionNo");
                terminalId = josonObject.getString("terminalId");
                robotId = josonObject.getString("robotId");
                jsendObj.put("robotId",robotId);
                jsendObj.put("instructionCode","replyLoginInfo");
                jsendObj.put("instructionNo",instructionNo);
                jsendObj.put("terminalId",terminalId);
            }
            else if(josonObject.getString("instructionCode").equals("reportHeartbeat")){
                if(!josonObject.getString("terminalId").equals(terminalId)){
                    System.out.println("get err terminalId:"+josonObject.getString("terminalId"));
                }
                if(!josonObject.getString("robotId").equals(robotId)){
                    System.out.println("get err robotId:"+josonObject.getString("robotId"));
                }
                jsendObj.put("terminalId",terminalId);
                jsendObj.put("robotId",robotId);
                jsendObj.put("instructionCode","replyHeartbeat");
                jsendObj.put("instructionNo",josonObject.getString("instructionNo"));
            }
            else if(josonObject.getString("instructionCode").equals("replyWarningInfo")){
                System.out.println("recv replyWarningInfo!!!");
            }
            else if(josonObject.getString("instructionCode").equals("getWarningImage")){
                System.out.println("recv getWarningImage!!!");
                jsendObj.put("terminalId",terminalId);
                jsendObj.put("robotId",robotId);
                jsendObj.put("instructionCode","replyWarningImage");
                jsendObj.put("instructionNo",josonObject.getString("instructionNo"));
                jsendObj.put("imageId",josonObject.getString("imageId"));
                jsendObj.put("ImageUrl","http://192.168.1.17:8080/XXXX/XXXX/"+josonObject.getString("imageId"));
            }
        }
        else{
            System.out.println("recv err!!!");
            return;
        }

        for(WebSocketTest item: webSocketSet){

            try {
                if(item.getSession()==session){
                    item.sendMessage(jsendObj.toString());
                }

            } catch (IOException e) {

                e.printStackTrace();

                continue;

            }

        }

    }

    /**

     * 发生错误时调用

     * @param session

     * @param error

     */

    @OnError

    public void onError(Session session, Throwable error){

        System.out.println("发生错误");

        error.printStackTrace();

    }

    /**

     * 这个方法与上面几个方法不一样。没有用注解，是根据自己需要添加的方法。

     * @param message

     * @throws IOException

     */

    public void sendMessage(String message) throws IOException{

        this.session.getBasicRemote().sendText(message);

        //this.session.getAsyncRemote().sendText(message);

    }

    public static synchronized int getOnlineCount() {

        return onlineCount;

    }

    public static synchronized void addOnlineCount() {

        WebSocketTest.onlineCount++;

    }

    public static synchronized void subOnlineCount() {

        WebSocketTest.onlineCount--;

    }

    public synchronized Session getSession(){

        return this.session;
    }

    public String getTerminalId() {
        return terminalId;
    }

    public void setTerminalId(String terminalId) {
        this.terminalId = terminalId;
    }

    public String getRobotId() {
        return robotId;
    }

    public void setRobotId(String robotId) {
        this.robotId = robotId;
    }
}
