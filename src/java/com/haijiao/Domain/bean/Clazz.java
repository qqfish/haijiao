/**
 *
 * @author Jerry Zou
 */

package com.haijiao.Domain.bean;

import java.sql.Date;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity    
@Table(name="clazz")     
public class Clazz extends BaseBean{ //clazz -> class
    @ManyToOne(fetch = FetchType.EAGER , cascade = {CascadeType.PERSIST})
    @JoinColumn(name = "tid")
    private Teacher teacher;
    
    @ManyToOne(fetch = FetchType.EAGER , cascade = {CascadeType.PERSIST})
    @JoinColumn(name = "sid")
    private Student student;
    
    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name="lid", unique=true)
    private Lesson lesson;

    @Column(name="datetime")
    private Date date;   //日期
    
    private Integer week; //第一/二个星期，此处只能取值1或2
    
    @Column(name="weekday")
    private int day;   //星期X
    private int beginIndex;    //时间片index，比如 1对应 8:00-9:00，2对应 9:00-10:00
    private int endIndex;
    
    private boolean accept;  //本次预约老师是否已接受
    private String message;   //本次预约在接受或拒绝后附加的信息
    private Integer pay;    //本次课程的花费
    private boolean finish;  //本次课程是否已结束（结算）

    
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

    public Lesson getLesson() {
        return lesson;
    }

    public void setLesson(Lesson lesson) {
        this.lesson = lesson;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Integer getWeek() {
        return week;
    }

    public void setWeek(Integer week) {
        this.week = week;
    }

<<<<<<< HEAD
    public Integer getDay() {
        return day;
    }

    public void setDay(Integer day) {
=======
    public int getDay() {
        return day;
    }

    public void setDay(int day) {
>>>>>>> schedule not test yet
        this.day = day;
    }

    public int getBeginIndex() {
        return beginIndex;
    }

    public void setBeginIndex(int beginIndex) {
        this.beginIndex = beginIndex;
    }

    public int getEndIndex() {
        return endIndex;
    }

    public void setEndIndex(int endIndex) {
        this.endIndex = endIndex;
    }
    
    public boolean isAccept() {
        return accept;
    }

    public void setAccept(boolean accept) {
        this.accept = accept;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getPay() {
        return pay;
    }

    public void setPay(Integer pay) {
        this.pay = pay;
    }

    public boolean isFinish() {
        return finish;
    }

    public void setFinish(boolean finish) {
        this.finish = finish;
    }

}
