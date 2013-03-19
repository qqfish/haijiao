/**
 *
 * @author Jerry Zou
 */

package com.haijiao.Domain.bean;

import java.util.ArrayList;
import java.util.List;

public class Clazz extends BaseBean { //clazz -> class
    private Teacher teacher;
    private Student student;
    private Lesson lesson;
    private List<Timeslice> timeslices;  //保存本次预约课程的时间范围
    private boolean accept;  //本次预约老师是否已接受
    private boolean message;   //本次预约在接受或拒绝后附加的信息
    private Integer pay;    //本次课程的花费
    private boolean finish;  //本次课程是否已结束（结算）

    public Clazz() {
        this.timeslices = new ArrayList<Timeslice>();
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

    public Lesson getLesson() {
        return lesson;
    }

    public void setLesson(Lesson lesson) {
        this.lesson = lesson;
    }

    public List<Timeslice> getTimeslices() {
        return timeslices;
    }

    public void setTimeslices(List<Timeslice> timeslices) {
        this.timeslices = timeslices;
    }

    public boolean isAccept() {
        return accept;
    }

    public void setAccept(boolean accept) {
        this.accept = accept;
    }

    public boolean isMessage() {
        return message;
    }

    public void setMessage(boolean message) {
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
