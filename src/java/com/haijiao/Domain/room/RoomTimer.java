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

/**
 *
 * @author fish
 */
public class RoomTimer {
    //just for one to one

    private Timer timer;
    private TimeCounter counter;
    private Room room;

    public RoomTimer(){
    }
    
    public RoomTimer(Room room) {
        timer = new Timer();
        counter = new TimeCounter(room);
        timer.schedule(counter, 1000, 1000);
        this.room = room;
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
        //timer.cancel();
    }

    public int getSeconds() {
        return counter.getSeconds();
    }

    public void setMaxTime(int max){
        counter.setMax(max);
    }
}
