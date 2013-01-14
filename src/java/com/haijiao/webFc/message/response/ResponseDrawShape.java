/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.haijiao.webFc.message.response;

import com.haijiao.webFc.message.Response;

/**
 *
 * @author fish
 */
public class ResponseDrawShape extends ResponseData{
    private String json;
    private int id;

    public ResponseDrawShape() {
        type = Response.DrawShape;
    }

    public String getJson() {
        return json;
    }

    public void setJson(String json) {
        this.json = json;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    
}
