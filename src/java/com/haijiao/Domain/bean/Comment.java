/**
 * Comment.java
 * @author Jerry Zou
 */

package com.haijiao.Domain.bean;

public class Comment {
    private User commenter;
    private User commentee;
    private String content;
    private Integer score;

    public User getCommenter() {
        return commenter;
    }

    public void setCommenter(User commenter) {
        this.commenter = commenter;
    }

    public User getCommentee() {
        return commentee;
    }

    public void setCommentee(User commentee) {
        this.commentee = commentee;
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
