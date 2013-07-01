/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.haijiao.Domain.room.webFc.message.request;

import com.haijiao.Domain.room.webFc.message.Request;

/**
 *
 * @author fish
 */
public class RequestCheckPin extends RequestData{
    private int pin;

    public RequestCheckPin() {
        type = Request.CheckPin;
    }

    public int getPin() {
        return pin;
    }

    public void setPin(int pin) {
        this.pin = pin;
    }
        
}
