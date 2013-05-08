/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.haijiao.presentation.action;

import com.haijiao.Domain.bean.User;
import com.haijiao.SupportService.service.IUserService;
import com.haijiao.presentation.bean.mail.SendMail;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import sun.misc.BASE64Encoder;

/**
 *
 * @author hp
 */
public class ForgetPasswordAction extends RequestAction{
    String email;
    IUserService userService;
    
    @Override
    public String execute(){
        User u = userService.getUserByEmail(email);
        u.getPassword();
        SendMail sm = new SendMail();
        MessageDigest md;
        try {
            md = MessageDigest.getInstance("md5");
            md.update(UUID.randomUUID().toString().getBytes("UTF-8"));
            BASE64Encoder base64en = new BASE64Encoder();
            String checkCode = base64en.encode(md.digest());
            String content = "请点击以下链接设置新密码：http://localhost:8080/haijiao/resetPassword.action?id="+
                u.getId() + "&checkCode=" + checkCode;
            userService.saveResetInfo(u.getId(), checkCode);
            sm.send(email, "忘记密码", content);
            return SUCCESS;
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(ForgetPasswordAction.class.getName()).log(Level.SEVERE, null, ex);
            return INPUT;
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(ForgetPasswordAction.class.getName()).log(Level.SEVERE, null, ex);
            return INPUT;
        }
    }

    public void setUserService(IUserService userService) {
        this.userService = userService;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
}
