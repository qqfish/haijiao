/**
 *
 * @author Jerry Zou
 */

//package com.haijiao.global;
//
//import java.util.Calendar;
//import java.util.Date;
//import java.util.Timer;
//import javax.servlet.ServletContextEvent;
//import javax.servlet.ServletContextListener;
//
//public class timer implements ServletContextListener{
//    static timerTask task;
//    private Timer theTimer;
//    @Override
//    public void contextInitialized(ServletContextEvent sce) {
//        if(task == null)
//            task = new timerTask();
//        theTimer = new Timer();
//        Calendar c = Calendar.getInstance();
//        task.setNowDay((c.get(Calendar.DAY_OF_WEEK) + 12) % 7);
//        Date now = new Date();
//        Date end = new Date(now.getYear(), now.getMonth(), now.getDate(), 23, 59);
//        theTimer.schedule(task, end, 86400000);
//    }
//
//    @Override
//    public void contextDestroyed(ServletContextEvent sce) {
//        theTimer.cancel();
//    }
//    
//    static public int getNowDay(){
//        return task.getNowDay();
//    }
//}
