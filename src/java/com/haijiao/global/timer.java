/**
 *
 * @author Jerry Zou
 */

package com.haijiao.global;

import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class timer implements ServletContextListener{
    static timerTask task;
    private Timer theTimer;
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        if(task == null)
            task = new timerTask();
        theTimer = new Timer();
        Calendar c = Calendar.getInstance();
        Date now = new Date();
        Date end = new Date(now.getYear(), now.getMonth(), now.getDate(), 23, 59);
        theTimer.schedule(task, end, 86400000);     //one day
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        theTimer.cancel();
    }
}
