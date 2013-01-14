/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.haijiao.webFc;

import com.google.gson.Gson;
import com.haijiao.room.Room;
import com.haijiao.room.Shape;
import com.haijiao.user.User;
import com.haijiao.webFc.message.Request;
import com.haijiao.webFc.message.request.RequestData;
import com.haijiao.webFc.message.request.RequestDrawShape;
import com.haijiao.webFc.message.request.RequestTmpShape;
import com.haijiao.webFc.message.response.ResponseDrawShape;
import com.haijiao.webFc.message.response.ResponseTmpShape;
import java.io.*;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.sql.SQLException;
import java.util.*;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletResponse;
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
        if (textData.getType() == Request.TmpShape) {
            RequestTmpShape rts = gson.fromJson(str, RequestTmpShape.class);
            ResponseTmpShape result = new ResponseTmpShape();
            result.setJson(rts.getJson());
            room.broadcast(gson.toJson(result));
        } else if (textData.getType() == Request.DrawShape) {
            RequestDrawShape shape = gson.fromJson(str, RequestDrawShape.class);
            int id = room.addShape(shape.getJson());
            ResponseDrawShape result = new ResponseDrawShape();
            result.setId(id);
            result.setJson(shape.getJson());
            room.broadcast(gson.toJson(result));
        }
    }
}
