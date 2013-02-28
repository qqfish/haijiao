/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.haijiao.Domain.room;

import com.google.gson.Gson;
import com.haijiao.Domain.file.DataFile;
import com.haijiao.Domain.bean.User;
import com.haijiao.Domain.room.webFc.FcMessageInbound;
import com.haijiao.Domain.room.webFc.message.response.ResponseChangePage;
import com.haijiao.Domain.room.webFc.message.response.ResponseDrawShape;
import com.haijiao.Domain.room.webFc.message.response.ResponseEraseShape;
import java.io.IOException;
import java.nio.CharBuffer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.TreeMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author fish
 */
public class Room {

    private Gson gson;
    private String roomId;
    private User holder;
    private List<User> attendance;
    private List<FcMessageInbound> roomSocket;
    private List<RoomFile> roomFile;
    private RoomPage currentPage;

    public Room(String id, User holder) {
        gson = new Gson();
        
        roomId = id;
        this.holder = holder;
        attendance = new ArrayList();
        attendance.add(holder);
        roomSocket = new ArrayList();
        roomFile = new ArrayList();
        roomFile.add(new RoomFile(this));
        currentPage = roomFile.get(0).getPage(0);
    }

    public void addAttendance(User user) {
        if (!attendance.contains(user)) {
            attendance.add(user);
        }
    }

    public void removeAttendance(User user) {
        if (!user.equals(holder)) {
            attendance.remove(user);
        }
    }

    public boolean checkAttendance(User user) {
        return attendance.contains(user);
    }

    public void enterRoom(FcMessageInbound socket) {
        roomSocket.add(socket);
    }

    public boolean checkInroomUser(User user) {
        for (int i = 0; i < roomSocket.size(); i++) {
            if (roomSocket.get(i).getUser().equals(user)) {
                return true;
            }
        }
        return false;
    }

    public void loadFile(DataFile file) {
        /* copy this file and add it into the list */
    }

    public boolean changePage(String uuid, int page, String tmpUri) {
        try {
            currentPage.saveTmp(tmpUri);
            return choosePage(uuid, page);
        } catch (IOException ex) {
            Logger.getLogger(Room.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public boolean choosePage(String uuid, int page) {
        RoomFile file = null;
        for (int i = 0; i < roomFile.size(); i++) {
            if (roomFile.get(i).getUuid().equals(uuid)) {
                file = roomFile.get(i);
                break;
            }
        }
        if (file == null) {
            return false;
        }
        RoomPage result = null;
        if (page < 0) {
            result = file.getLastPage();
        } else {
            result = file.getPage(page);
        }

        if (result == null) {
            return false;
        }
        currentPage = result;

        broadcast(gson.toJson(getCurrentPage()));
        return true;
    }
    
    public ResponseChangePage getCurrentPage(){
        ResponseChangePage message = new ResponseChangePage();
        message.setPage(currentPage.getPageNumber());
        message.setShapeList(currentPage.getShapes());
        message.setUrl(currentPage.getOriginUrl());
        message.setUuid(currentPage.getFile().getUuid());
        return message;
    }
    
    public void drawShape(String shape){
        int id = currentPage.addShape(shape);
        ResponseDrawShape result = new ResponseDrawShape();
        result.setId(id);
        result.setJson(shape);
        broadcast(gson.toJson(result));
    }
    
    public void eraseShape(List<Integer> idArray){
        Collections.sort(idArray);
        currentPage.deleteShape(idArray);
        ResponseEraseShape result = new ResponseEraseShape();
        result.setIdArray(idArray);
        broadcast(gson.toJson(result));
    }

    public RoomFile getOneRoomFile(String uuid) {
        for (int i = 0; i < roomFile.size(); i++) {
            if (uuid.equals(roomFile.get(i).getUuid())) {
                return roomFile.get(i);
            }
        }
        return null;
    }

    public void broadcast(String message) {
        for (int i = 0; i < roomSocket.size(); i++) {
            try {
                CharBuffer buffer = CharBuffer.wrap(message);
                roomSocket.get(i).getWsOutbound().writeTextMessage(buffer);
            } catch (IOException ex) {
                Logger.getLogger(Room.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void broadcastOther(String message, FcMessageInbound from) {
        for (int i = 0; i < roomSocket.size(); i++) {
            if (!roomSocket.get(i).equals(from)) {
                try {
                    CharBuffer buffer = CharBuffer.wrap(message);
                    roomSocket.get(i).getWsOutbound().writeTextMessage(buffer);
                } catch (IOException ex) {
                    Logger.getLogger(Room.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    public void sentto(String message, String userId) throws IOException {
        CharBuffer buffer = CharBuffer.wrap(message);
        for (int i = 0; i < roomSocket.size(); i++) {
            if (roomSocket.get(i).getUser().getUserId().equals(userId)) {
                roomSocket.get(i).getWsOutbound().writeTextMessage(buffer);
            }
        }
    }

    public void exitRoom(FcMessageInbound socket) {
        roomSocket.remove(socket);
    }

    public String getRoomId() {
        return roomId;
    }
}
