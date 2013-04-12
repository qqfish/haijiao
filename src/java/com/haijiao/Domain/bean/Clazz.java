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
        static public final int notAvailable = 0;
        static public final int available = 1;
        static public final int notAccept = 2;
        static public final int accept = 3;
    }
   
    @ManyToOne(fetch = FetchType.EAGER , cascade = {CascadeType.PERSIST})
    @JoinColumn(name = "sid")
    private Student student;  //固定时间的学生
    
    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name="lid", unique=true)
    private Lesson lesson;

    private FreeTime freeTime;
    
    private int status;  //本次预约的状态，可选值未Status类中
    private int remain;    //课程次数，-1为无限,每上一次减1，到0该课程被清除
    private int timeToBegin;    //距离开始的次数,方便学生查询

    public Clazz(Clazz c){
        student = c.getStudent();
        lesson = c.getLesson();
        freeTime = c.getFreeTime();
        status = c.getStatus();
        remain = c.getRemain();
        timeToBegin = c.getTimeToBegin();
    }

    public Clazz() {
    }

    public Lesson getLesson() {
        return lesson;
    }

    public void setLesson(Lesson lesson) {
        this.lesson = lesson;
    }

    public FreeTime getFreeTime() {
        return freeTime;
    }

    public void setFreeTime(FreeTime freeTime) {
        this.freeTime = freeTime;
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
    public void addTimeToBegin(int num) {
        timeToBegin += num;
    }
}
