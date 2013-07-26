/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.haijiao.presentation.action;

import com.haijiao.SupportService.service.IBillService;
import javax.annotation.Resource;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.InterceptorRef;
import org.apache.struts2.convention.annotation.InterceptorRefs;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.stereotype.Controller;

/**
 *
 * @author hp
 */
@Controller
@ParentPackage("haijiao")
@Namespace("/")
@InterceptorRefs({  
    @InterceptorRef("LoginCheckerStack")  
})  
@Action("makeCommentReply")
@Results({
    @Result(name="success",type="redirect",location="index.action?tab=comment")
})
public class MakeCommentReplyAction extends SessionAction{
    @Resource
    private IBillService billService;
    private String content;
    private Integer score;
    private Integer id;
    
    public String comment(){
        String userType = (String)this.getSessionValue("userType");
        if(billService.commentBill(id, content, score, userType)==false){
            this.sessionPutIn("nextPageMessage", "你已经评论过了哦！");
        } else {
            this.sessionPutIn("nextPageMessage", "评论成功！");
        }
        return SUCCESS;
    }
    
    public String reply(){
        String userType = (String)this.getSessionValue("userType");
        if (billService.replyComment(id, content, userType)==false){
            this.sessionPutIn("nextPageMessage", "你已经回复过了哦！");
        } else {
            this.sessionPutIn("nextPageMessage", "回复成功！");
        }
        return SUCCESS;
    }

    public void setBillService(IBillService billService) {
        this.billService = billService;
    }

    public IBillService getBillService() {
        return billService;
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

}
