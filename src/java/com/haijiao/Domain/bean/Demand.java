/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.haijiao.Domain.bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

/**
 *
 * @author hp
 */

@Entity
@Table
public class Demand extends BaseBean{
    private String lesson;  //课程名称
    private String demand;  //详细需求
    private String address; //地址
    private String way;     //授课方式
    @Column(columnDefinition = "int default 5")
    private int reserveMax; //最大预约老师数
    @Column(columnDefinition = "int default 0")
    private int reserved;   //已预约数
    private int duration;   //课时数
    private int total;      //总价
    @Column(columnDefinition = "bool default false")
    private boolean publish;//是否发布
    @Column(name = "publishtime")
    @Temporal(TemporalType.TIMESTAMP)
    private Date publishTime;
    
    @OneToMany(mappedBy="demand")
    @Fetch(value = FetchMode.SUBSELECT)
    private List<Bill> bills;//对应预约

    public Demand() {
        bills = new ArrayList<Bill>();
    }

    public String getLesson() {
        return lesson;
    }

    public void setLesson(String lesson) {
        this.lesson = lesson;
    }

    public String getDemand() {
        return demand;
    }

    public void setDemand(String demand) {
        this.demand = demand;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getWay() {
        return way;
    }

    public void setWay(String way) {
        this.way = way;
    }

    public int getReserveMax() {
        return reserveMax;
    }

    public void setReserveMax(int reserveMax) {
        this.reserveMax = reserveMax;
    }

    public int getReserved() {
        return reserved;
    }

    public void setReserved(int reserved) {
        this.reserved = reserved;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<Bill> getBills() {
        return bills;
    }

    public void setBills(List<Bill> bills) {
        this.bills = bills;
    }

    public boolean isPublish() {
        return publish;
    }

    public void setPublish(boolean publish) {
        this.publish = publish;
    }

    public Date getPublishTime() {
        return publishTime;
    }

    public void setPublishTime(Date publishTime) {
        this.publishTime = publishTime;
    }
    
}
