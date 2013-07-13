/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.haijiao.SupportService.service;

import com.haijiao.Domain.bean.Student;
import com.haijiao.Domain.bean.Teacher;
import com.haijiao.Domain.room.Room;

/**
 *
 * @author fish
 */
public interface IRoomService {
    public Room checkAndApplyRoom(Teacher tea, Student stu);
    public void removeRoom(Teacher tea, Student stu);
    public void clearUnuseRoom();
    public Room enterPublicRoom(String teacherEmail);
    public void removeRoom(Teacher teacher);
}
