/**
 *
 * @author Jerry Zou
 */

package com.haijiao.Domain.bean;

public class Bill extends BaseBean{
    private User from;
    private int money;
    private boolean earn;   //收入或支出
    private String message; //账单说明
    private Comment comment;

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

    public Comment getComment() {
        return comment;
    }

    public void setComment(Comment comment) {
        this.comment = comment;
    }
    
    
}
