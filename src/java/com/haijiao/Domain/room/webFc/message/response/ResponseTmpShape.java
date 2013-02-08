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
public class ResponseTmpShape extends ResponseData{
    private String json;

    public ResponseTmpShape() {
        type = Response.TmpShape;
    }

    public String getJson() {
        return json;
    }

    public void setJson(String json) {
        this.json = json;
    }
    
}
