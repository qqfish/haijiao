/**
 *
 * @author Jerry Zou
 */

package com.haijiao.Domain.bean;

public class Mail {
    private User from;
    private User to;
    private String message;
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
