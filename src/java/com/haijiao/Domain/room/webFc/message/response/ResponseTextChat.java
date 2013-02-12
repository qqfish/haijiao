/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.haijiao.Domain.room.webFc.message.response;

import com.haijiao.Domain.room.webFc.message.Response;
import com.haijiao.Domain.room.webFc.message.request.RequestTextChat;

/**
 *
 * @author fish
 */
public class ResponseTextChat extends ResponseData{
    private String message;
    private String from;

    public ResponseTextChat() {
        type = Response.TextChat;
    }
    
    public ResponseTextChat(RequestTextChat request){
        type = Response.TextChat;
        message = request.getMessage();
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }
    
    
    
}
