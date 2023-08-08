package com.MyServletContextListener;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class sthread extends Thread {
    @Override
    public void run() {
        ServerSocket welcomeSocket=null;

        try {
            welcomeSocket = new ServerSocket(12345);
            while (true) {
                System.out.println("start accept");
                Socket clientSocket = welcomeSocket.accept();
                System.out.println("end accept");
                new Thread(new ServerListen(clientSocket)).start();//为每一个连接创建一个接收线程
            }
        }catch (IOException e){
            e.printStackTrace();
        }finally {
            try {
                if(welcomeSocket!=null){
                    welcomeSocket.close();
                }
            }catch (IOException e){
                e.printStackTrace();
            }
        }
    }
}
