/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.haijiao.Domain.room;

import com.google.gson.Gson;
import com.haijiao.Domain.room.webFc.message.response.ResponseShowTimer;
import java.util.TimerTask;

/**
 *
 * @author fish
 */
public class TimeCounter extends TimerTask {

    private int seconds;
    private boolean start;
    Room room;

    public TimeCounter(Room room) {
        this.room = room;
        this.start = false;
        seconds = 0;
    }

    @Override
    public void run() {
        if (start) {
            seconds++;
            int tmp = seconds;
            String result = "" + tmp % 60;
            tmp = tmp / 60;
            result = tmp % 60 + ":" + result;
            tmp = tmp / 60;
            result = tmp + ":" + result;
            ResponseShowTimer rst = new ResponseShowTimer();
            rst.setTime(result);
            Gson gson = new Gson();
            room.broadcast(gson.toJson(rst));
        }
    }

    public int getSeconds() {
        return seconds;
    }

    public void setSeconds(int seconds) {
        this.seconds = seconds;
    }
    public void setStart(boolean start){
        this.start = start;
    }

    public boolean isStart() {
        return start;
    }
    
}
