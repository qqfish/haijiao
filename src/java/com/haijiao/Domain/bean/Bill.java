/**
 *
 * @author Jerry Zou
 */

package com.haijiao.Domain.bean;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity    
@Table(name="bill")
public class Bill extends BaseBean{
    
    public class Status {
        static public final int pending = 0;
        static public final int notAccept = 1;
        static public final int accept = 2;
        static public final int paid = 3;
        static public final int teacherFinish = 4;
        static public final int studentFinish = 5;
        static public final int teacherCommented = 6;
        static public final int studentCommented = 7;
        static public final int studentCancel = 8;
    }
    static public final int CommitDay = 15;  //学生最大确认天数
    
    @ManyToOne(cascade = {CascadeType.PERSIST})
    @JoinColumn(name = "tid")
    private Teacher teacher;
    
    @ManyToOne(cascade = {CascadeType.PERSIST})
    @JoinColumn(name = "sid")
    private Student student;
    
    private int duration;   //课程时长
    private int status;     //账单状态
    private int money;      //总价
    @Column(columnDefinition="int default 0")
    private int day;        //确认剩余天数

    private String lesson;  //课程的名称
    private String message; //账单说明
    
    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "scommentid", unique = true)
    private Comment stot; //学生对老师的评论
    
    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "tcommentid", unique = true)
    private Comment ttos; //老师对学生的评论
    
    @ManyToOne(cascade = {CascadeType.PERSIST})
    @JoinColumn(name="did")
    private Demand demand;
    
//    public int getRealMoney(String userType){
//        if (userType.equals("teacher")) {
//            return money;
//        } else {
//            return -money;
//        }
//    }

    public Bill() {
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Comment getStot() {
        return stot;
    }

    public void setStot(Comment stot) {
        this.stot = stot;
    }

    public Comment getTtos() {
        return ttos;
    }

    public void setTtos(Comment ttos) {
        this.ttos = ttos;
    }

    public String getLesson() {
        return lesson;
    }

    public void setLesson(String lesson) {
        this.lesson = lesson;
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

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public Demand getDemand() {
        return demand;
    }

    public void setDemand(Demand demand) {
        this.demand = demand;
    }
    
}
