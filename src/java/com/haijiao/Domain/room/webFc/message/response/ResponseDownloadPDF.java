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
public class ResponseDownloadPDF extends ResponseData{
    private String path;

    public ResponseDownloadPDF() {
        type = Response.DownloadPDF;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
    
    
    
    
}
