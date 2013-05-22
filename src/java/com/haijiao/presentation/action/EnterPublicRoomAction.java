/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.haijiao.presentation.action;

import com.haijiao.SupportService.service.IMailService;

/**
 *
 * @author fish
 */
public class EnterPublicRoomAction extends RequestSessionAction {
    private IMailService mailService;
    private String teaEmail;
    private int isHolder;
    
    @Override
    public String execute(){
        teaEmail = (String)this.getOutRequest("teaEmail");
        isHolder = 0;
        if(teaEmail == null){
            return "false";
        }
        String email = (String) this.getOutSession("email");
        if(email == null){
            return "false";
        }
        if(email.equals(teaEmail)){
            isHolder = 1;
        }
        mailService.sendMail( (String)this.getOutSession("email"), teaEmail,
                "��ʦ���ã���ͬѧ���������Ĺ���������Ҫ�������Ŀγ̡�"
                + "���ʼ���ϵͳ���ͣ���������ɻظ����ʼ���ѧ��������ϵ��"
        );
        return SUCCESS;
    }

    public String getTeaEmail() {
        return teaEmail;
    }

    public void setTeaEmail(String teaEmail) {
        this.teaEmail = teaEmail;
    }

    public int getIsHolder() {
        return isHolder;
    }

    public void setIsHolder(int isHolder) {
        this.isHolder = isHolder;
    }

    public IMailService getMailService() {
        return mailService;
    }

    public void setMailService(IMailService mailService) {
        this.mailService = mailService;
    }
}
