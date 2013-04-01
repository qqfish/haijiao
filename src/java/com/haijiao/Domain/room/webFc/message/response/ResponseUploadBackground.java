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
public class ResponseUploadBackground extends ResponseData{
    String dataUrl;

    public ResponseUploadBackground() {
        type = Response.UploadBackground;
    }

    public String getDataUrl() {
        return dataUrl;
    }

    public void setDataUrl(String dataUrl) {
        this.dataUrl = dataUrl;
    }
    
}
