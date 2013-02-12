/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.haijiao.Domain.room.webFc.message.response;

import com.haijiao.Domain.room.webFc.message.Response;
import com.haijiao.Domain.room.webFc.message.request.RequestVideoChat;

/**
 *
 * @author fish
 */
public class ResponseVideoChat extends ResponseData {

    private String from;
    private String to;
    private String data;

    public ResponseVideoChat() {
        type = Response.VideoChat;
    }

    public ResponseVideoChat(RequestVideoChat request) {
        type = Response.VideoChat;
        to = request.getTo();
        data = request.getData();
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
