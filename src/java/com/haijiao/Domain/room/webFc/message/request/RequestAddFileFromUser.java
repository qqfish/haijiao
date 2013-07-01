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
public class RequestAddFileFromUser extends RequestData{
    String name;
    String group;
    String url;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public RequestAddFileFromUser() {
        type = Request.AddFileFromUser;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }
    
}
