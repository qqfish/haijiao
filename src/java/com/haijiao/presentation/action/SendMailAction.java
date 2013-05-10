/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.haijiao.presentation.action;

import com.haijiao.SupportService.service.IMailService;

/**
 *
 * @author hp
 */
public class SendMailAction extends SessionAction{
    String name;
    IMailService mailService;
    String email;
    String content;
    String nextPageMessage;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public IMailService getMailService() {
        return mailService;
    }

    public void setMailService(IMailService mailService) {
        this.mailService = mailService;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getNextPageMessage() {
        return nextPageMessage;
    }

    public void setNextPageMessage(String nextPageMessage) {
        this.nextPageMessage = nextPageMessage;
    }
    
    @Override
    public String execute(){
        System.out.println(content);
        if(name.isEmpty()){
            nextPageMessage = "发送失败，收件人不能为空";
            return INPUT;
        }
        int id = Integer.parseInt(name);
        if(content.isEmpty()){
            nextPageMessage = "发送失败，消息内容不能为空";
            return INPUT;
        }
        mailService.sendMail((String)this.getSessionValue("email"), id, content);
        nextPageMessage = "发送成功";
        return SUCCESS;
    }
}
