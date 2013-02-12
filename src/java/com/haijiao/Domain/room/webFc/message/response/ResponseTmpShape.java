/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.haijiao.Domain.room.webFc.message.response;

import com.haijiao.Domain.room.webFc.message.Response;
import com.haijiao.Domain.room.webFc.message.request.RequestTmpShape;

/**
 *
 * @author fish
 */
public class ResponseTmpShape extends ResponseData{
    private String json;

    public ResponseTmpShape() {
        type = Response.TmpShape;
    }
    
    public ResponseTmpShape(RequestTmpShape request) {
        type = Response.TmpShape;
        json = request.getJson();
    }

    public String getJson() {
        return json;
    }

    public void setJson(String json) {
        this.json = json;
    }
    
}
