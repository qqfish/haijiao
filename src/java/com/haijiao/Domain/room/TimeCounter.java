/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.haijiao.Domain.room;

import java.util.TimerTask;

/**
 *
 * @author fish
 */
public class TimeCounter extends TimerTask{
    private int seconds;
    Room room;

    public TimeCounter(Room room){
        this.room = room;
        seconds = 0;
    }
    @Override
    public void run() {
        seconds++;
    }

    public int getSeconds() {
        return seconds;
    }

    public void setSeconds(int seconds) {
        this.seconds = seconds;
    }
    
}
