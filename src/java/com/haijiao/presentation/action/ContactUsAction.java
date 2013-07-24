/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.haijiao.presentation.action;

import com.haijiao.global.config;
import java.io.File;
import java.io.FileWriter;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.stereotype.Controller;

/**
 *
 * @author fish
 */
@Controller
@ParentPackage("struts-default")
@Namespace("/")
@Action("contactUs")
@Results({
    @Result(name="success",type="redirect",location="index.action")
})
public class ContactUsAction extends RequestSessionAction {

    private String name;
    private String email;
    private String subject;
    private String context;

    @Override
    public String execute() throws Exception {
        File file = new File(config.contactFile);
        if(!file.exists()){
            file.createNewFile();
        }
        FileWriter writer = new FileWriter(file,true);
        writer.write(name + "," + email + "," + subject + "," + context + "\n");
        writer.close();
        this.sessionPutIn("nextPageMessage", "感谢您的宝贵意见");
        return SUCCESS;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }
}
