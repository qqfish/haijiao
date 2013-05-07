/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.haijiao.Domain.room;

import com.haijiao.Domain.bean.User;
import com.haijiao.SupportService.SpringContext;
import com.haijiao.SupportService.service.IBillService;
import java.util.List;
import java.util.Timer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.web.context.support.XmlWebApplicationContext;

/**
 *
 * @author fish
 */
public class RoomTimer {
    //just for one to one

    private Timer timer;
    private TimeCounter counter;
    private int price;
    private Room room;
    private IBillService billService;

    public RoomTimer(){
    }
    
    public RoomTimer(Room room, int price) {
        timer = new Timer();
        counter = new TimeCounter(room);
        timer.schedule(counter, 1000, 1000);
        this.room = room;
        this.price = price;
        ApplicationContext context1 = new XmlWebApplicationContext();
        billService = (IBillService) SpringContext.getContext().getBean("billServiceImpl");
    }

    public boolean start() {
        if(room.isReady()){
            counter.setStart(true);
            return true;
        }
        return false;
    }

    public void pause() {
        counter.setStart(false);
    }

    public boolean toggle() {
        if (counter.isStart()) {
            pause();
            return true;
        } else {
            return start();
        }
    }

    public void stop() {
        pause();
        System.out.println("stoping");
        List<User> att = room.getAttendance();
        if (att.size() < 2) {
            return;
        }
        System.out.println(att.get(1).getEmail());
        System.out.println(room.getHolder().getEmail());
        System.out.println(billService);
        System.out.println(billService.produceBill(att.get(1).getEmail(), room.getHolder().getEmail(), price * counter.getSeconds() / 60 / 60 * price, "课程资费"));
        //timer.cancel();
    }

    public int getSeconds() {
        return counter.getSeconds();
    }

    public IBillService getBillService() {
        return billService;
    }

    public void setBillService(IBillService billService) {
        this.billService = billService;
    }
}
