/**
 *
 * @author Jerry Zou
 */

package com.haijiao.Domain.bean;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table
public class Mail extends BaseBean{
    
    @ManyToOne(fetch = FetchType.EAGER , cascade = {CascadeType.PERSIST})
    @JoinColumn(name = "fromid")
    private User from;
    
    @ManyToOne(fetch = FetchType.EAGER , cascade = {CascadeType.PERSIST})
    @JoinColumn(name = "toid")
    private User to;
    
    private String message;
    
    @Column(name="isread")
    private boolean read;

    public Mail() {
    }

    public User getFrom() {
        return from;
    }

    public void setFrom(User from) {
        this.from = from;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isRead() {
        return read;
    }

    public void setRead(boolean read) {
        this.read = read;
    }

    public User getTo() {
        return to;
    }

    public void setTo(User to) {
        this.to = to;
    }
}
