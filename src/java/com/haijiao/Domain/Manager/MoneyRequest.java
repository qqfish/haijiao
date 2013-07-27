/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.haijiao.Domain.Manager;

import com.haijiao.Domain.bean.BaseBean;
import com.haijiao.Domain.bean.Teacher;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

/**
 *
 * @author fish
 */
@Entity    
public class MoneyRequest extends BaseBean{
    public class Status{
        public final static int newRequest = 0;
        public final static int finish = 1;
    }
    @ManyToOne
    private Teacher teacher;
    private String bankcard;
    private String bankname;
    private int status;
    private int money;
    
    public MoneyRequest(){
        status = Status.newRequest;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
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

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }
}
