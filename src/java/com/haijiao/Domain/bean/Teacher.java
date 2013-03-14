/**
 * Teacher.java
 * @author fish
 */

package com.haijiao.Domain.bean;

import java.util.ArrayList;
import java.util.List;

public class Teacher extends User{
    private String brief_intro;
    private String intro;
    private String videoUrl;    //老师的介绍视频地址
    private List<String> classes = new ArrayList<String>();   //该老师教授的课程
    private boolean audition;        //该老师是否接受试听
    private Schedule schedule;      //记录老师的时间表
    private int wagePerhour;         //老师每小时的辅导费
    private List<Clazz> clazzlist;    //老师的课程列表
    private List<Clazz> booklist;    //老师的课程预约列表

    
    
    public String getBrief_intro() {
        return brief_intro;
    }

    public void setBrief_intro(String brief_intro) {
        this.brief_intro = brief_intro;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public List<String> getClasses() {
        return classes;
    }

    public void setClasses(List<String> classes) {
        this.classes = classes;
    }

    public boolean isAudition() {
        return audition;
    }

    public void setAudition(boolean audition) {
        this.audition = audition;
    }

    public Schedule getSchedule() {
        return schedule;
    }

    public void setSchedule(Schedule schedule) {
        this.schedule = schedule;
    }

    public int getWagePerhour() {
        return wagePerhour;
    }

    public void setWagePerhour(int wagePerhour) {
        this.wagePerhour = wagePerhour;
    }

    public List<Clazz> getClazzlist() {
        return clazzlist;
    }

    public void setClazzlist(List<Clazz> clazzlist) {
        this.clazzlist = clazzlist;
    }

    public List<Clazz> getBooklist() {
        return booklist;
    }

    public void setBooklist(List<Clazz> booklist) {
        this.booklist = booklist;
    }
}
