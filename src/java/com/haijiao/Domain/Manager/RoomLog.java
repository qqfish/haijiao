/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.haijiao.Domain.Manager;

import com.haijiao.Domain.bean.BaseBean;
import com.haijiao.Domain.bean.Student;
import com.haijiao.Domain.bean.Teacher;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

/**
 *
 * @author fish
 */
@Entity
public class RoomLog extends BaseBean {

    @ManyToOne
    private Teacher teacher;
    @ManyToOne
    private Student student;
    
    private int lasttime;

    public RoomLog() {
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public int getLasttime() {
        return lasttime;
    }

    public void setLasttime(int lasttime) {
        this.lasttime = lasttime;
    }
    
    
}
