/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.haijiao.Domain.bean;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author fish
 
 */ 
@Entity
@Table
public class Payment extends BaseBean{
    public class Type{
        public final static int lessonPay = 0;
        public final static int charge = 1;
        public final static int getMoney = 2;
    }
    
    @Column(columnDefinition="int default -1")
    private int billId;
    private String username;
    private int type;
    private int money;
    
    public Payment(Bill b){
        billId = b.getId();
        username = b.getStudent().getName();
        type = Type.lessonPay;
        money = b.getMoney();
    }
    
    public Payment(){
    }

    public int getBillId() {
        return billId;
    }

    public void setBillId(int billId) {
        this.billId = billId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }
    
}
