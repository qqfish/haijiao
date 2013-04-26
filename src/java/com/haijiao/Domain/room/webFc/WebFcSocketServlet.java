package com.haijiao.Domain.room.webFc;

/*
 * To change this template, choose Tools | Templates and open the template in
 * the editor.
 */
import com.haijiao.Domain.bean.User;
import com.haijiao.Domain.room.Room;
import com.haijiao.SupportService.service.IRoomService;
import com.haijiao.SupportService.service.IUserService;
import com.haijiao.SupportService.service.impl.RoomServiceImpl;
import com.haijiao.SupportService.service.impl.UserServiceImpl;
import javax.annotation.Resource;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import org.apache.catalina.websocket.StreamInbound;
import org.apache.catalina.websocket.WebSocketServlet;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

/**
 *
 * @author fish
 */
@WebServlet(name = "WebFcSocketServlet", urlPatterns = {"/WebFcSocketServlet"})
public class WebFcSocketServlet extends WebSocketServlet {

    private IRoomService roomService;
    private IUserService userService;

    @Override
    protected StreamInbound createWebSocketInbound(String string, HttpServletRequest hsr) {
        /*get the user and room throught hsr*/
        int clazzId = Integer.parseInt(hsr.getParameter("clazzId"));
        String email = hsr.getParameter("email");
        ApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(hsr.getSession().getServletContext());

        roomService = (IRoomService) context.getBean("roomServiceImpl");
        userService = (IUserService) context.getBean("userServiceImpl");

        Room room = roomService.checkAndApplyRoom(clazzId);
        System.out.println(room);
        User user = userService.getUserByEmail(email);
        System.out.println(user);
        
        if (room == null || user == null || room.checkInroomUser(user)) {
            return null;
        }
        return new FcMessageInbound(user, room);
    }

    public IRoomService getRoomService() {
        return roomService;
    }

    public void setRoomService(IRoomService roomService) {
        this.roomService = roomService;
    }

    public IUserService getUserService() {
        return userService;
    }

    public void setUserService(IUserService userService) {
        this.userService = userService;
    }
}