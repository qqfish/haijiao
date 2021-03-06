/**
 * Teacher.java
 *
 * @author fish
 */
package com.haijiao.Domain.bean;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@org.hibernate.annotations.Entity(dynamicUpdate = true, dynamicInsert = true)
@Table(name = "teacher")
@PrimaryKeyJoinColumn
public class Teacher extends User {

    private String school;      //就读大学
    private String major;       //就读专业
    private String studyStatus;  //教师身份
    private String origin;      //生源地

    @Column(columnDefinition = "int default 0")
    private Integer classNum;       //成功完成课程的次数    
    @Column(columnDefinition = "int default 0")
    private Integer obNum;          //浏览数

    private String underlineArea;   //线下授课区域
    private String experience;        //个人经历
    private String address;             //家庭地址
    private String cert;            //证书
    @Column(columnDefinition = "bool default true")
    private Boolean sprtOnline;      //支持线上
    @Column(columnDefinition = "bool default true")
    private Boolean sprtSUnderline;  //学生上门
    @Column(columnDefinition = "bool default true")
    private Boolean sprtTUnderline;  //老师上门
    private String studentin;  //公共房间学生email

    @Column(columnDefinition = "int default 0")
    private Integer reserveNum;     //预约次数
    @Column(columnDefinition = "int default 0")
    private Integer newReserveNum;     //新预约次数

    private String tel;         //老师的手机
    private String videoUrl;    //老师的介绍视频地址

    @OneToMany
    @JoinColumn(name = "lid")
    private List<Label> labels; //老师的标签 

    @OneToMany
    @JoinColumn(name = "tid")
    private List<Lesson> lessons;   //该老师开设课程

    @Column(columnDefinition = "bool default true")
    private Boolean audition;       //该老师是否接受试听
    @Column(columnDefinition = "bool default true")
    private Boolean reserve;        //该老师是否接受预定

//    @OneToMany(mappedBy = "teacher")
//    private List<FreeTime> schedule;//记录老师的时间表
    @OneToMany(mappedBy = "teacher")
    protected List<Bill> billList;  //预约列表

    @Column(columnDefinition = "int default 0")
    private int wagePerhour = Integer.MAX_VALUE;        //老师最小每小时的辅导费

    @OneToMany
    @JoinColumn(name = "tid")
    protected List<Payment> paymentList;    //账单列表

    private String bankcard;
    private String bankname;

    @Column(columnDefinition = "int default 0")
    protected int level;            //老师的等级，用来获取分成比例

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

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public int getClassNum() {
        return classNum;
    }

    public void setClassNum(Integer classNum) {
        this.classNum = classNum;
    }

    public int getReserveNum() {
        return reserveNum;
    }

    public void setReserveNum(Integer reserveNum) {
        this.reserveNum = reserveNum;
    }

    public Integer getNewReserveNum() {
        return newReserveNum;
    }

    public void setNewReserveNum(Integer newReserveNum) {
        this.newReserveNum = newReserveNum;
    }

    public int getObNum() {
        return obNum;
    }

    public void setObNum(Integer obNum) {
        this.obNum = obNum;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getStudyStatus() {
        return studyStatus;
    }

    public void setStudyStatus(String studyStatus) {
        this.studyStatus = studyStatus;
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

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
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

    public String getUnderlineArea() {
        return underlineArea;
    }

    public void setUnderlineArea(String underlineArea) {
        this.underlineArea = underlineArea;
    }

    public String getExperience() {
        return experience;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCert() {
        return cert;
    }

    public void setCert(String cert) {
        this.cert = cert;
    }

    public Boolean getSprtOnline() {
        return sprtOnline;
    }

    public void setSprtOnline(Boolean sprtOnline) {
        this.sprtOnline = sprtOnline;
    }

    public Boolean getSprtSUnderline() {
        return sprtSUnderline;
    }

    public void setSprtSUnderline(Boolean sprtSUnderline) {
        this.sprtSUnderline = sprtSUnderline;
    }

    public Boolean getSprtTUnderline() {
        return sprtTUnderline;
    }

    public void setSprtTUnderline(Boolean sprtTUnderline) {
        this.sprtTUnderline = sprtTUnderline;
    }

    public void setAudition(Boolean audition) {
        this.audition = audition;
    }

    public Boolean getReserve() {
        return reserve;
    }

    public void setReserve(Boolean reserve) {
        this.reserve = reserve;
    }

    public String getStudentin() {
        return studentin;
    }

    public void setStudentin(String studentin) {
        this.studentin = studentin;
    }

//    public List<FreeTime> getSchedule() {
//        return schedule;
//    }
//
//    public void setSchedule(List<FreeTime> schedule) {
//        this.schedule = schedule;
//    }
    public int getWagePerhour() {
        return wagePerhour;
    }

    public void setWagePerhour(Integer wagePerhour) {
        this.wagePerhour = wagePerhour;
    }

    public List<Bill> getBillList() {
        return billList;
    }

    public void setBillList(List<Bill> billList) {
        this.billList = billList;
    }

    public Lesson getLessonByName(String lesson) {
        for (int i = 0; i < lessons.size(); i++) {
            if (lessons.get(i).getName().equals(lesson)) {
                return lessons.get(i);
            }
        }
        return null;
    }

//    public FreeTime getFreeTime(int day, int index) {
//        for(int i = 0; i < schedule.size(); i++){
//            if(schedule.get(i).getWeekday() == day && schedule.get(i).getSliceIndex() == index){
//                return schedule.get(i);
//            }
//        }
//        return null;
//    }
    public List<Payment> getPaymentList() {
        return paymentList;
    }

    public void setPaymentList(List<Payment> paymentList) {
        this.paymentList = paymentList;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public void addPayment(Payment p) {
        paymentList.add(p);
    }

    public String getBankcard() {
        return bankcard;
    }

    public void setBankcard(String bankcard) {
        this.bankcard = bankcard;
    }

    public String getBankname() {
        return bankname;
    }

    public void setBankname(String bankname) {
        this.bankname = bankname;
    }
    
    public String getSecretName() {
        String result = name.substring(0, 1) + "老师";
        return result;
    }
}
