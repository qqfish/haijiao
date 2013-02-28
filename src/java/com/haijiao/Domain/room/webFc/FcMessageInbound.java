/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.haijiao.Domain.room.webFc;

import com.google.gson.Gson;
import com.haijiao.Domain.room.Room;
import com.haijiao.Domain.room.Shape;
import com.haijiao.Domain.bean.User;
import com.haijiao.Domain.room.webFc.message.Request;
import com.haijiao.Domain.room.webFc.message.request.*;
import com.haijiao.Domain.room.webFc.message.response.*;
import java.io.*;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.catalina.websocket.MessageInbound;
import org.apache.catalina.websocket.WsOutbound;

/**
 *
 * @author fish
 */
public class FcMessageInbound extends MessageInbound {

    User user;
    Room room;
    Gson gson;

    public FcMessageInbound(User user, Room room) {
        this.user = user;
        this.room = room;
        gson = new Gson();
        room.enterRoom(this);
    }

    @Override
    protected void onOpen(WsOutbound outbound) {
        System.out.println(this.toString() + " ,new connection created");
        sendtoUser(gson.toJson(room.getCurrentPage()));
    }

    @Override
    protected void onClose(int status) {
        ResponseVideoChat bye = new ResponseVideoChat();
        bye.setFrom(user.getUserId());
        bye.setData("{\"type\":\"bye\"}");
        room.broadcastOther(gson.toJson(bye), this);
        room.exitRoom(this);
        System.out.println(this.toString() + "closed");
    }

    @Override
    protected void onBinaryMessage(ByteBuffer bb) throws IOException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    protected void onTextMessage(CharBuffer cb) throws IOException {
        String str = cb.toString();
        System.out.println(str);
        if (str == null || str.isEmpty()) {
            return;
        }
        RequestData textData = gson.fromJson(str, RequestData.class);
        //System.out.println(textData.getType());
        //System.out.println(idRoom + " " + username);
        switch (textData.getType()) {
            case Request.TmpShape:
                RequestTmpShape rts = gson.fromJson(str, RequestTmpShape.class);
                ResponseTmpShape tmpResult = new ResponseTmpShape(rts);
                room.broadcastOther(gson.toJson(tmpResult),this);
                break;
            case Request.DrawShape:
                RequestDrawShape shape = gson.fromJson(str, RequestDrawShape.class);
                room.drawShape(shape.getJson());
                break;
            case Request.EraseShape:
                RequestEraseShape erase = gson.fromJson(str, RequestEraseShape.class);
                room.eraseShape(erase.getIdArray());
                break;
            case Request.VideoChat:
                RequestVideoChat rvc = gson.fromJson(str, RequestVideoChat.class);
                ResponseVideoChat videoResult = new ResponseVideoChat(rvc);
                videoResult.setFrom(user.getUserId());
                if (videoResult.getTo() == null) {
                    room.broadcastOther(gson.toJson(videoResult), this);
                } else {
                    room.sentto(gson.toJson(videoResult), videoResult.getTo());
                }
                break;
            case Request.TextChat:
                RequestTextChat chat = gson.fromJson(str, RequestTextChat.class);
                ResponseTextChat chatResult = new ResponseTextChat(chat);
                chatResult.setFrom(user.getUserId());
                room.broadcast(gson.toJson(chatResult));
                break;
            case Request.ChangePage:
                RequestChangePage page = gson.fromJson(str, RequestChangePage.class);
                if (page.getTmpUrl() != null) {
                    room.changePage(page.getFileUuid(), page.getPage(), page.getTmpUrl());
                } else {
                    room.choosePage(page.getFileUuid(), page.getPage());
                }
                break;

        }


    }

    public User getUser() {
        return user;
    }

    public void sendtoUser(String message) {
        CharBuffer buffer = CharBuffer.wrap(message);
        try {
            getWsOutbound().writeTextMessage(buffer);
        } catch (IOException ex) {
            Logger.getLogger(FcMessageInbound.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
