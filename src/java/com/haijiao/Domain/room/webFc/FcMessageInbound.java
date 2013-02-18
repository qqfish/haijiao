/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.haijiao.Domain.room.webFc;

import com.google.gson.Gson;
import com.haijiao.Domain.room.Room;
import com.haijiao.Domain.room.Shape;
import com.haijiao.Domain.user.User;
import com.haijiao.Domain.room.webFc.message.Request;
import com.haijiao.Domain.room.webFc.message.request.*;
import com.haijiao.Domain.room.webFc.message.response.*;
import java.io.*;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
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
    }

    @Override
    protected void onClose(int status) {
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
                room.broadcast(gson.toJson(tmpResult));
                break;
            case Request.DrawShape:
                RequestDrawShape shape = gson.fromJson(str, RequestDrawShape.class);
                int id = room.getCurrentPage().addShape(shape.getJson());
                ResponseDrawShape drawResult = new ResponseDrawShape(shape);
                drawResult.setId(id);
                room.broadcast(gson.toJson(drawResult));
                break;
            case Request.EraseShape:
                RequestEraseShape erase = gson.fromJson(str, RequestEraseShape.class);
                erase.sort();
                room.getCurrentPage().deleteShape(erase.getIdArray());
                ResponseEraseShape eraseResult = new ResponseEraseShape(erase);
                room.broadcast(gson.toJson(eraseResult));
                break;
            case Request.VideoChat:
                RequestVideoChat rvc = gson.fromJson(str, RequestVideoChat.class);
                ResponseVideoChat videoResult = new ResponseVideoChat(rvc);
                room.broadcastOther(gson.toJson(videoResult), this);
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
                ResponseChangePage pageResult = new ResponseChangePage(page);
                pageResult.setUrl(room.getCurrentPage().getOriginUrl());
                pageResult.setShapeList(room.getCurrentPage().getShapes());
                room.broadcast(gson.toJson(pageResult));
                break;

        }


    }
}
