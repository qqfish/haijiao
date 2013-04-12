/**
 * Teacher.java
 * @author fish
 */

package com.haijiao.Domain.bean;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity    
@Table(name="teacher")
@PrimaryKeyJoinColumn
public class Teacher extends User{
    private String school;      //就读大学
    private String province;    //省份
    private String classNum;    //成功完成课程的次数
    
    @Column(columnDefinition="int default 0")
    private String reserveNum;  //预约次数
    
    private String tel;         //老师的手机
    private String videoUrl;    //老师的介绍视频地址
    
    @OneToMany(fetch=FetchType.EAGER)
    @Fetch(value = FetchMode.SUBSELECT)
    @JoinColumn(name = "lid")
    private List<Label> labels; //老师的标签 
    
    @OneToMany(fetch=FetchType.EAGER)
    @Fetch(value = FetchMode.SUBSELECT)
    @JoinColumn(name="tid")
    private List<Lesson> lessons;   //该老师开设课程
    
    private boolean audition;       //该老师是否接受试听
    
    @OneToMany(mappedBy = "teacher")
    private List<FreeTime> schedule;//记录老师的时间表
    
    private int wagePerhour;        //老师每小时的辅导费
    
    public Teacher() {
        this.lessons = new ArrayList<Lesson>();
        this.labels = new ArrayList<Label>();
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getClassNum() {
        return classNum;
    }

    public void setClassNum(String classNum) {
        this.classNum = classNum;
    }

    public String getReserveNum() {
        return reserveNum;
    }

    public void setReserveNum(String reserveNum) {
        this.reserveNum = reserveNum;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    public List<Label> getLabels() {
        return labels;
    }

    public void setLabels(List<Label> labels) {
        this.labels = labels;
    }

    public List<Lesson> getLessons() {
        return lessons;
    }

    public void setLessons(List<Lesson> lessons) {
        this.lessons = lessons;
    }

    public boolean isAudition() {
        return audition;
    }

    public void setAudition(boolean audition) {
        this.audition = audition;
    }

    public List<FreeTime> getSchedule() {
        return schedule;
    }

    public void setSchedule(List<FreeTime> schedule) {
        this.schedule = schedule;
    }

    public int getWagePerhour() {
        return wagePerhour;
    }

    public void setWagePerhour(int wagePerhour) {
        this.wagePerhour = wagePerhour;
    }

    public FreeTime getFreeTime(int day, int index) {
        for(int i = 0; i < schedule.size(); i++){
            if(schedule.get(i).getDay() == day && schedule.get(i).getIndex() == index){
                return schedule.get(i);
            }
        }
        return null;
    }
}
