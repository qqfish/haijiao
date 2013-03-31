/**
 *
 * @author Jerry Zou
 */

package com.haijiao.presentation.action;

import com.haijiao.SupportService.service.ITeacherService;
import java.sql.Date;

public class ChangeTeacherInfoAction extends SessionAction {
    ITeacherService teacherService;
    String password;
    String name;
    String sex;
    Date birthday;
    String school;
    String tel;

    @Override
    public String execute(){
        if(teacherService.changeInfo((String)this.getValue("email"), password, name, sex, birthday, school, tel)){
            this.putIn("message", this.getText("successmessage"));
            return SUCCESS;
        } else {
            this.putIn("message", this.getText("errormessage"));
            return "unconnected";
        }
    }

    public ITeacherService getTeacherService() {
        return teacherService;
    }

    public void setTeacherService(ITeacherService teacherService) {
        this.teacherService = teacherService;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }
}
