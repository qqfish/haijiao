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
public class RequestChangeFile extends RequestData{
    private String uuid;
    private String tmpUrl;

    public RequestChangeFile() {
        type = Request.ChangeFile;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getTmpUrl() {
        return tmpUrl;
    }

    public void setTmpUrl(String tmpUrl) {
        this.tmpUrl = tmpUrl;
    }
    
    
}
