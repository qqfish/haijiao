/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.haijiao.Domain.bean;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

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
    private int reserveMax; //最大预约老师数
    private int reserved;   //已预约数
    private int duration;   //课时数
    private int total;      //总价
    @OneToMany
    @JoinColumn(name = "did")
    private List<Bill> bills;//对应预约

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
    
}
