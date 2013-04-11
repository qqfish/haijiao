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
    @ManyToOne(fetch = FetchType.EAGER , cascade = {CascadeType.PERSIST})
    @JoinColumn(name = "tid")
    private Teacher teacher;
    
    @ManyToOne(fetch = FetchType.EAGER , cascade = {CascadeType.PERSIST})
    @JoinColumn(name = "stableSid")
    private Student stableStudent;  //固定时间的学生
    
    @ManyToOne(fetch = FetchType.EAGER , cascade = {CascadeType.PERSIST})
    @JoinColumn(name = "tmpSid")
    private Student tmpStudent;     //临时学生
    
    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name="lid", unique=true)
    private Lesson lesson;
    
    @Column(name="weekday")
    private int day;   //星期X 从0到13
    @Column(name="sliceindex")
    private int index;    //时间片index，比如 1对应 8:00-9:00，2对应 9:00-10:00
    
    private boolean accept;  //本次预约老师是否已接受
    private boolean studentPause;  //学生暂停 其他学生可以选当临时学生
    private boolean teacherPause;   //老师暂停 其他学生不能选
    private String message;   //本次预约在接受或拒绝后附加的信息
    private Integer pay;    //本次课程的花费
    private boolean finish;  //本次课程是否已结束（结算）

    
    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public Student getStableStudent() {
        return stableStudent;
    }

    public void setStableStudent(Student stableStudent) {
        this.stableStudent = stableStudent;
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

    public Student getTmpStudent() {
        return tmpStudent;
    }

    public void setTmpStudent(Student tmpStudent) {
        this.tmpStudent = tmpStudent;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public boolean isStudentPause() {
        return studentPause;
    }

    public void setStudentPause(boolean studentPause) {
        this.studentPause = studentPause;
    }

    public boolean isTeacherPause() {
        return teacherPause;
    }

    public void setTeacherPause(boolean teacherPause) {
        this.teacherPause = teacherPause;
    }

}
