/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.haijiao.Domain.room.webFc;

import com.haijiao.Domain.bean.User;
import com.haijiao.Domain.room.Room;

/**
 *
 * @author fish
 */
public class tmpRoom {
    private static Room testRoom;
    private static User[] user;
    private static int counter;
    
    public static Room getRoom(){
        if(testRoom == null){
            user = new User[3];
            user[0] = new User();
            user[1] = new User();
            user[2] = new User();
            user[0].setUserId("123");
            user[1].setUserId("234");
            user[2].setUserId("345");
            counter = 0;
            testRoom = new Room("1",user[0]);
            for(int i = 1; i < 3; i++){
                testRoom.addAttendance(user[i]);
            }
        }
        return testRoom;
    }
    
    public static User getUser(){
        int u = counter % 3;
        counter++;
        return user[u];
    }
}