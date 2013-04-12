/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.haijiao.Domain.bean;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

/**
 *
 * @author fish
 */
@Entity
@Table
public class FreeTime extends BaseBean{
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="tid")
    private Teacher teacher;
    
    @Column(name="weekday")
    private int day;   //星期X 从0到13
    
    @Column(name="sliceindex")
    private int index;    //时间片index，比如 1对应 8:00-9:00，2对应 9:00-10:00
    
    @OneToMany(mappedBy = "freeTime")
    private List<Clazz> clazzList;  //该空闲时间选的课程，第一个为本次的课程，完成后第一个clazz的remain减1，为0则删除, timeToClass 也减一

    public FreeTime() {
        clazzList = new ArrayList();
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public List<Clazz> getClazzList() {
        return clazzList;
    }

    public void setClazzList(List<Clazz> clazzList) {
        this.clazzList = clazzList;
    }
    
    
}
