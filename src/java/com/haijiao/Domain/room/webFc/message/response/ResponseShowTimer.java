/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.haijiao.Domain.room.webFc.message.response;

import com.haijiao.Domain.room.webFc.message.Response;

/**
 *
 * @author fish
 */
public class ResponseShowTimer extends ResponseData{
    String time;

    public ResponseShowTimer() {
        type = Response.ShowTimer;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
    
}
