package com.haijiao.Domain.room.webFc;

/*
 * To change this template, choose Tools | Templates and open the template in
 * the editor.
 */
import com.haijiao.Domain.bean.User;
import com.haijiao.Domain.room.Room;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import org.apache.catalina.websocket.StreamInbound;
import org.apache.catalina.websocket.WebSocketServlet;

/**
 *
 * @author fish
 */
@WebServlet(name = "WebFcSocketServlet", urlPatterns = {"/WebFcSocketServlet"})
public class WebFcSocketServlet extends WebSocketServlet {

    @Override
    protected StreamInbound createWebSocketInbound(String string, HttpServletRequest hsr) {
        /*get the user and room throught hsr*/
        Room room = tmpRoom.getRoom();
        User user = tmpRoom.getUser();
        if(room.checkInroomUser(user)){
            return null;
        }
        return new FcMessageInbound(user, room);
    }
}
