/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.haijiao.Domain.room.webFc.message.response;

import com.haijiao.Domain.room.Shape;
import com.haijiao.Domain.room.webFc.message.Response;
import com.haijiao.Domain.room.webFc.message.request.RequestChangePage;
import java.util.List;

/**
 *
 * @author fish
 */
public class ResponseChangePage extends ResponseData{
    private String uuid;
    private int page;
    private String url;
    private List<Shape> shapeList;
    
    public ResponseChangePage(){
        type = Response.ChangePage;
    }
    
    public ResponseChangePage(RequestChangePage request){
        type = Response.ChangePage;
        uuid = request.getFileUuid();
        page = request.getPage();
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public List<Shape> getShapeList() {
        return shapeList;
    }

    public void setShapeList(List<Shape> shapeList) {
        this.shapeList = shapeList;
    }
    
    
}
