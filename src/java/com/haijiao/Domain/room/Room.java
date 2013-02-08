/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.haijiao.Domain.room;

import com.haijiao.Domain.file.File;
import com.haijiao.Domain.user.User;
import com.haijiao.Domain.room.webFc.FcMessageInbound;
import java.io.IOException;
import java.nio.CharBuffer;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

/**
 *
 * @author fish
 */
public class Room {

    private String roomId;
    private User holder;
    private List<User> attendance;
    
    private List<FcMessageInbound> roomSocket;
    private List<RoomFile> roomFile;
    
    private RoomPage currentPage;

    public Room(String id, User holder) {
        roomId = id;
        this.holder = holder;
        attendance = new ArrayList();
        roomSocket = new ArrayList();
        roomFile = new ArrayList();
        currentPage = null;
    }
    
    public void addAttendance(User user){
        if(!attendance.contains(user)){
            attendance.add(user);
        }
    }
    
    public void removeAttendance(User user){
        attendance.remove(user);
    }
    
    public boolean checkAttendance(User user){
        return attendance.contains(user);
    }

    public void enterRoom(FcMessageInbound socket) {
        roomSocket.add(socket);
    }

    public void loadFile(File file) {
        /* copy this file and add it into the list */
    }
    
    public boolean choosePage(String uuid, int page) {
        RoomFile file = null;
        for(int i = 0; i < roomFile.size(); i++){
            if(roomFile.get(i).getUuid().equals(uuid)){
                file = roomFile.get(i);
                break;
            }
        }
        if(file == null){
            return false;
        }
        RoomPage result = file.getPage(page);
        if(result == null){
            return false;
        }
        currentPage = result;
        return true;
    }

    public RoomPage getCurrentPage() {
        return currentPage;
    }
    
    public RoomFile getOneRoomFile(String uuid){
        for(int i = 0; i < roomFile.size(); i++){
            if(uuid.equals(roomFile.get(i).getUuid())){
                return roomFile.get(i);
            }
        }
        return null;
    }
    

    public void broadcast(String message) throws IOException {
        for (int i = 0; i < roomSocket.size(); i++) {
            CharBuffer buffer = CharBuffer.wrap(message);
            roomSocket.get(i).getWsOutbound().writeTextMessage(buffer);
        }
    }
    
    public void exitRoom(FcMessageInbound socket) {
        roomSocket.remove(socket);
    }
}
