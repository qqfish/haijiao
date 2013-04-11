/**
 * Comment.java
 * @author Jerry Zou
 */

package com.haijiao.Domain.bean;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity    
@Table(name="comment")
public class Comment extends BaseBean{
    
    private String content;
    private String reply;
    private Integer score;

    public Comment() {
    }

    public String getReply() {
        return reply;
    }

    public void setReply(String reply) {
        this.reply = reply;
    }


    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }
}
