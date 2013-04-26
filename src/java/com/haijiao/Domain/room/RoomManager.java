/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.haijiao.Domain.room;

import com.haijiao.Domain.bean.User;
import java.util.Map;
import java.util.TreeMap;
import java.util.UUID;

/**
 *
 * @author fish
 */
public class RoomManager {
    private static Map<String, Room> rooms = new TreeMap();
    public static String createRoom(User holder){
        String id = UUID.randomUUID().toString();
        Room newRoom = new Room(holder,0);
        rooms.put(id, newRoom);
        return id;
    }
    
    public static void deleteRoom(String id){
        rooms.remove(id);
    }
    
    public static Room getRoom(String id){
        return rooms.get(id);
    }
}
