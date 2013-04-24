/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.haijiao.SupportService.service;

import com.haijiao.Domain.bean.Mail;
import java.util.List;

/**
 *
 * @author Jerry
 */
public interface IMailService {
    //以Id来获取Mail
    public Mail getMailById(int id);
    //获取本用户收到的私信
    public List<Mail> getMail(String email);
    //获取本用户未收到的私信
    public List<Mail> getUnreadMail(String email);
    //发送一封私信
    public boolean sendMail(String emailFrom, int id, String message);
    public boolean sendMail(String emailFrom, String emailTo, String message);
    //设置私信状态（已读/未读）
    public boolean setMailStatus(int mailId, boolean read);
}
