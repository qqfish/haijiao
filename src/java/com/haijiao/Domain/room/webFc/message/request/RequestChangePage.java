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
public class RequestChangePage extends RequestData{
    private String fileUuid;
    private int page;
    private String tmpUrl;

    public RequestChangePage() {
        type = Request.ChangePage;
    }

    public String getFileUuid() {
        return fileUuid;
    }

    public void setFileUuid(String fileUuid) {
        this.fileUuid = fileUuid;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public String getTmpUrl() {
        return tmpUrl;
    }

    public void setTmpUri(String tmpUrl) {
        this.tmpUrl = tmpUrl;
    }
    
    
    
    
}
