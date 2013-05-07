/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.haijiao.Domain.room.webFc.message.response.Info;

import com.haijiao.Domain.room.webFc.message.Response;
import com.haijiao.Domain.room.webFc.message.response.ResponseData;

/**
 *
 * @author fish
 */
public class InfoData extends ResponseData{
    private int infoType;
    private String message;

    public InfoData() {
        type = Response.Info;
    }

    public int getInfoType() {
        return infoType;
    }

    public void setInfoType(int infoType) {
        this.infoType = infoType;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    
}
