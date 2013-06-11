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
public class RequestDownloadPdf extends RequestData{
    private String tmpUrl;

    public RequestDownloadPdf() {
        type = Request.DownloadPdf;
    }

    public String getTmpUrl() {
        return tmpUrl;
    }

    public void setTmpUrl(String tmpUrl) {
        this.tmpUrl = tmpUrl;
    }
    
    
}
