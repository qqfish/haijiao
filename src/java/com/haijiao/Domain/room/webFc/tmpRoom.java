/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.haijiao.Domain.room.webFc;

import com.haijiao.Domain.bean.User;
import com.haijiao.Domain.file.DataFile;
import com.haijiao.Domain.file.UserFile;
import com.haijiao.Domain.file.UserFileGroup;
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
            user[0].setName("123");
            user[1].setName("234");
            user[2].setName("345");
            counter = 0;
            testRoom = new Room(user[0]);
            UserFile file1 = new UserFile();
            file1.setUrl("/Users/fish/Downloads/test1.pdf");
            file1.setName("test1.pdf");
            user[0].addFileGroup("new");
            user[0].addFile("new", file1);
            UserFile file2 = new UserFile();
            file2.setUrl("/Users/fish/Downloads/test2.pdf");
            file2.setName("test1.pdf");
            //testRoom.loadFile(file1);
            //testRoom.loadFile(file2);
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
