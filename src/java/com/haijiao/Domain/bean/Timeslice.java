/**
 *
 * @author Jerry Zou
 */

package com.haijiao.Domain.bean;

import java.sql.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity    
@Table(name="timeslice")
public class Timeslice extends BaseBean {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cid")
    private Clazz clazz;   //本时间属于哪个clazz，如果为null，则为空闲时间
    
    @Column(name="datetime")
    private Date date;   //日期
    
    private Integer week; //第一/二个星期，此处只能取值1或2
    
    @Column(name="weekday")
    private String day;   //星期X
    private int index;     //时间片index，比如 1对应 8:00-8:30，2对应 8:30-9:00

    public Clazz getClazz() {
        return clazz;
    }

    public void setClazz(Clazz clazz) {
        this.clazz = clazz;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Integer getWeek() {
        return week;
    }

    public void setWeek(Integer week) {
        this.week = week;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }
}
