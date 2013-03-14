/**
 *
 * @author Jerry Zou
 */

package com.haijiao.Domain.bean;

import java.util.ArrayList;
import java.util.List;

public class Clazz { //clazz -> class
    private Teacher tc;
    private Student sd;
    private List<Timeslice> ts;
    private boolean accept;
    
    public Clazz(){
        ts = new ArrayList<Timeslice>();
    }

    public Teacher getTc() {
        return tc;
    }

    public void setTc(Teacher tc) {
        this.tc = tc;
    }

    public Student getSd() {
        return sd;
    }

    public void setSd(Student sd) {
        this.sd = sd;
    }

    public List<Timeslice> getTs() {
        return ts;
    }

    public void setTs(List<Timeslice> ts) {
        this.ts = ts;
    }

    public boolean isAccept() {
        return accept;
    }

    public void setAccept(boolean accept) {
        this.accept = accept;
    }
    
    
}
