/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.haijiao.room;

import com.haijiao.file.File;
import com.haijiao.webFc.FcMessageInbound;
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
    private List<FcMessageInbound> roomSocket;
    private List<RoomFile> roomFile;
    
    private RoomPage currentPage;

    public Room(String id) {
        roomId = id;
        roomSocket = new ArrayList();
        roomFile = new ArrayList();
        currentPage = null;
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
    
    public int addShape(String json){
        return currentPage.addShape(json);
    }

    public RoomPage getCurrentPage() {
        return currentPage;
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
