/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.haijiao.presentation.action;

import com.haijiao.SupportService.service.IBillService;

/**
 *
 * @author hp
 */
public class MakeCommentReplyAction extends SessionAction{
    IBillService billService;
    String content;
    Integer score;
    Integer id;
    String nextPageMessage;
    
    public String comment(){
        String userType = (String)this.getSessionValue("userType");
        if(billService.commentBill(id, content, score, userType)==false){
            nextPageMessage = "你已经评论过了哦！";
        } else {
            nextPageMessage = "评论成功！";
        }
        return SUCCESS;
    }
    
    public String reply(){
        String userType = (String)this.getSessionValue("userType");
        if (billService.replyComment(id, content, userType)==false){
            nextPageMessage = "你已经回复过了哦！";
        } else {
            nextPageMessage = "回复成功！";
        }
        return SUCCESS;
    }

    public void setBillService(IBillService billService) {
        this.billService = billService;
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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNextPageMessage() {
        return nextPageMessage;
    }

    public void setNextPageMessage(String nextPageMessage) {
        this.nextPageMessage = nextPageMessage;
    }

}
