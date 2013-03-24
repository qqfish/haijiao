/**
 * Comment.java
 * @author Jerry Zou
 */

package com.haijiao.Domain.bean;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity    
@Table(name="comment")
public class Comment extends BaseBean{
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="uid")
    private User commenter;
    
    private String content;
    private Integer score;

    public User getCommenter() {
        return commenter;
    }

    public void setCommenter(User commenter) {
        this.commenter = commenter;
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
