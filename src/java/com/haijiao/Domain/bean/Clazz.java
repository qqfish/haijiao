/**
 *
 * @author Jerry Zou
 */

package com.haijiao.Domain.bean;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity    
@Table(name="clazz")     
public class Clazz extends BaseBean{ //clazz -> class
    public class Status{
        static final int notAvailable = 0;
        static final int available = 1;
        static final int notAccept = 2;
        static final int accept = 3;
    }
    
    @ManyToOne(fetch = FetchType.EAGER , cascade = {CascadeType.PERSIST})
    @JoinColumn(name = "tid")
    private Teacher teacher;
    
    @ManyToOne(fetch = FetchType.EAGER , cascade = {CascadeType.PERSIST})
    @JoinColumn(name = "sid")
    private Student student;  //固定时间的学生
    
    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name="lid", unique=true)
    private Lesson lesson;
    
    @Column(name="weekday")
    private int day;   //星期X 从0到13
    @Column(name="sliceindex")
    private int index;    //时间片index，比如 1对应 8:00-9:00，2对应 9:00-10:00
    
    private int status;  //本次预约的状态，可选值未Status类中
    private int remain;    //课程次数，-1为无限,每上一次减1，到0该课程被清除
    private int timeToBegin;    //距离开始的次数,方便学生查询

    
    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public Lesson getLesson() {
        return lesson;
    }

    public void setLesson(Lesson lesson) {
        this.lesson = lesson;
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

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getRemain() {
        return remain;
    }

    public void setRemain(int remain) {
        this.remain = remain;
    }

    public int getTimeToBegin() {
        return timeToBegin;
    }

    public void setTimeToBegin(int timeToBegin) {
        this.timeToBegin = timeToBegin;
    }
}
