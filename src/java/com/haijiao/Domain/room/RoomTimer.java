/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.haijiao.Domain.room;

import com.haijiao.Domain.bean.User;
import com.haijiao.SupportService.service.IBillService;
import java.util.List;
import java.util.Timer;

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

    public RoomTimer(Room room, int price) {
        timer = new Timer();
        counter = new TimeCounter(room);
        this.room = room;
        this.price = price;
    }
    
    public void begin(){
        timer.schedule(counter, 1000);
    }
    
    public void pause(){
        timer.cancel();
    }
    
    public void stop(){
        timer.cancel();
        List<User> att = room.getAttendance();
        if(att.size() == 0)
            return;
        billService.produceBill(att.get(0).getEmail(), room.getHolder().getEmail(), price * counter.getSeconds() / 60 / 60 * price, "课程资费");
    }
    
    public int getSeconds(){
        return counter.getSeconds();
    }

    public IBillService getBillService() {
        return billService;
    }

    public void setBillService(IBillService billService) {
        this.billService = billService;
    }
}
