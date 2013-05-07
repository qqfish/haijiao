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
    private int classNum;    //成功完成课程的次数
    
    @Column(columnDefinition="int default 0")
    private int reserveNum;  //预约次数
    
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
    
    @OneToMany(mappedBy = "teacher",fetch=FetchType.EAGER)
    @Fetch(value = FetchMode.SUBSELECT)
    private List<FreeTime> schedule;//记录老师的时间表
    
    @OneToMany(mappedBy = "teacher",fetch=FetchType.EAGER)
    @Fetch(value = FetchMode.SUBSELECT)
    protected List<Bill> billList;  //账单列表
    
    private int wagePerhour;        //老师每小时的辅导费
    
    public Teacher() {
        this.lessons = new ArrayList<Lesson>();
        this.labels = new ArrayList<Label>();
        this.billList = new ArrayList<Bill>();
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

    public int getClassNum() {
        return classNum;
    }

    public void setClassNum(int classNum) {
        this.classNum = classNum;
    }

    public int getReserveNum() {
        return reserveNum;
    }

    public void setReserveNum(int reserveNum) {
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

    public List<Bill> getBillList() {
        return billList;
    }

    public void setBillList(List<Bill> billList) {
        this.billList = billList;
    }
    
    public Lesson getLessonByName(String lesson){
        for(int i = 0; i < lessons.size(); i++){
            if(lessons.get(i).getName().equals(lesson)){
                return lessons.get(i);
            }
        }
        return null;
    }

    public FreeTime getFreeTime(int day, int index) {
        for(int i = 0; i < schedule.size(); i++){
            if(schedule.get(i).getWeekday() == day && schedule.get(i).getSliceIndex() == index){
                return schedule.get(i);
            }
        }
        return null;
    }
}
