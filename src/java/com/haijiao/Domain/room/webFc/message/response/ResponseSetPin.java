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
public class ResponseSetPin extends ResponseData{
    private int pin;

    public ResponseSetPin() {
        type = Response.SetPin;
    }

    public int getPin() {
        return pin;
    }

    public void setPin(int pin) {
        this.pin = pin;
    }
    
}
