/**
 *
 * @author Jerry Zou
 */

package com.haijiao.Domain.bean;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity    
@Table(name="bill")
public class Bill extends BaseBean{
    @ManyToOne(fetch=FetchType.EAGER,cascade = {CascadeType.PERSIST})
    @JoinColumn(name = "uid")
    private User from;
    
    private int money;
    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "lid")
    private Lesson lesson;
    private boolean earn;   //收入或支出
    private String message; //账单说明
    
    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "commentid", unique = true)
    private Comment comment;
    
    public int getRealMoney(){
        if (earn==true) {
            return money;
        } else {
            return -money;
        }
    }

    public Bill() {
    }

    public User getFrom() {
        return from;
    }

    public void setFrom(User from) {
        this.from = from;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public boolean isEarn() {
        return earn;
    }

    public void setEarn(boolean earn) {
        this.earn = earn;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Comment getComment() {
        return comment;
    }

    public void setComment(Comment comment) {
        this.comment = comment;
    }

    public Lesson getLesson() {
        return lesson;
    }

    public void setLesson(Lesson lesson) {
        this.lesson = lesson;
    }
}
