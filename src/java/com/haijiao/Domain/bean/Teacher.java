/**
 * Teacher.java
 * @author fish
 */

package com.haijiao.Domain.bean;

import java.util.List;

public class Teacher extends User{
    private String brief_intro;
    private String intro;
    private String videoURL;    //老师的介绍视频地址
    private int money;              //该老师账户中剩下的报酬
    private List<Comment> comments;
    private List<String> classes;   //该老师教授的课程
    private boolean audition;        //该老师是否接受试听
    private Schedule schedule;      //记录老师的时间表
    private int wagePerhour;         //老师每小时的辅导费

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

    public String getVideoURL() {
        return videoURL;
    }

    public void setVideoURL(String videoURL) {
        this.videoURL = videoURL;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
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
    
}
