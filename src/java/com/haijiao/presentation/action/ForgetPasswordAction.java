/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.haijiao.presentation.action;

import com.haijiao.Domain.bean.User;
import com.haijiao.SupportService.service.IUserService;
import com.haijiao.global.config;
import com.haijiao.presentation.bean.mail.SendMail;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.stereotype.Controller;
import sun.misc.BASE64Encoder;

/**
 *
 * @author hp
 */
@Controller
@ParentPackage("struts-default")
@Namespace("/")
@Action("forgetPassword")
@Results({
    @Result(name="success",type="redirect",location="index.action"),
    @Result(name="input",location="/forgetPassword.jsp")
})
public class ForgetPasswordAction extends RequestSessionAction{
    @Resource
    private IUserService userService;
    private String email;
    
    @Override
    public String execute(){
        User u = userService.getUserByEmail(email);
        if(u == null){
            this.sessionPutIn("nextPageMessage", "未注册的用户,请检查邮箱是否填写正确");
            return INPUT;
        }
        u.getPassword();
        SendMail sm = new SendMail();
        MessageDigest md;
        try {
            md = MessageDigest.getInstance("md5");
            md.update(UUID.randomUUID().toString().getBytes("UTF-8"));
            BASE64Encoder base64en = new BASE64Encoder();
            String checkCode = base64en.encode(md.digest());
            String content = "请点击以下链接设置新密码：http://" +config.websiteURI +
                    "/haijiao/resetPassword.action?id=" + u.getId() + "&checkCode=" + checkCode;
            userService.saveResetInfo(u.getId(), checkCode);
            sm.send(email, "忘记密码", content);
            this.sessionPutIn("nextPageMessage", "已发送至邮箱，请查收");
            return SUCCESS;
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(ForgetPasswordAction.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(ForgetPasswordAction.class.getName()).log(Level.SEVERE, null, ex);
        }
        return INPUT;
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
