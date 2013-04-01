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
public class RequestUploadFile extends RequestData{
    private String postfix;
    private String data;

    public RequestUploadFile() {
        type = Request.UploadFile;
    }

    public String getPostfix() {
        return postfix;
    }

    public void setPostfix(String postfix) {
        this.postfix = postfix;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
    
}
