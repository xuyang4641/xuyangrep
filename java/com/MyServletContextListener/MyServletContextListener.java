package com.MyServletContextListener;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;


public class MyServletContextListener implements ServletContextListener{
    protected sthread thread1;
    public void contextDestroyed(ServletContextEvent arg0) {
        System.out.println("Destroyed 被执行");
    }
    public void contextInitialized(ServletContextEvent arg0) {
        ServletContext sc=arg0.getServletContext();
        System.out.println("马克-to-win Initialized 被执行"+sc.getMajorVersion());
        thread1 = new sthread();
        thread1.setName("线程1");
        thread1.start();
    }
}
