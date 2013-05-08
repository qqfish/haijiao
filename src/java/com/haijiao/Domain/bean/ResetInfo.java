/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.haijiao.Domain.bean;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 *
 * @author hp
 */
@Entity
@Table
public class ResetInfo extends BaseBean{
    int userid;
    String checkcode;

    public ResetInfo() {
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public String getCheckcode() {
        return checkcode;
    }

    public void setCheckcode(String checkcode) {
        this.checkcode = checkcode;
    }
    
}
