/**
 *
 * @author Jerry Zou
 */

package com.haijiao.presentation.action;

import com.haijiao.Domain.bean.User;
import com.haijiao.SupportService.MD5Util;
import com.haijiao.SupportService.service.IStudentService;
import com.haijiao.SupportService.service.ITeacherService;
import com.haijiao.SupportService.service.IUserService;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import javax.annotation.Resource;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.InterceptorRef;
import org.apache.struts2.convention.annotation.InterceptorRefs;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.stereotype.Controller;

@Controller
@ParentPackage("haijiao")
@Namespace("/")
@InterceptorRefs({  
    @InterceptorRef("LoginCheckerStack")  
})  
@Action("changeInfo")
@Results({
    @Result(name="input",type="redirect",location="index.action"),
    @Result(name="success",type="redirect",location="toChangeInfo.action")
})
public class ChangeInfoAction extends SessionAction {
    @Resource
    private ITeacherService teacherService;
    @Resource
    private IStudentService studentService;
    @Resource
    private IUserService userService;
    private String id;
    private String password;
    private String name;
    private String sex;
    private String birthday;
    private String school;
    private String major;
    private String studyStatus;
    private String grade;
    private String tel;
    private String telType;
    private String oldpwd;
    private String newpwd;
    private String newpwd2;
    private String province;
    private String city;
    private String district;
    private String net;
    private SimpleDateFormat sdf;
    private SimpleDateFormat sdf2;
    private Date date;
    private String lessonName;
    private String underlineArea;
    private String experience;
    private String cert;
    private String intro;
    private Boolean sprtSUnderline;
    private Boolean sprtTUnderline;
    private Boolean sprtOnline;
    
    public ChangeInfoAction() throws ParseException{
        sdf= new SimpleDateFormat("MM/dd/yyyy");
        sdf2= new SimpleDateFormat("yy-MM-dd");
    }
    
    public void parseDate() throws ParseException{
        try{
            date = new Date(sdf.parse(birthday).getTime());
        } catch(ParseException e){
            try{
                date = new Date(sdf2.parse(birthday).getTime());
            } catch(ParseException e2) {
                //do nothing;
            }
        }
    }
    
    public String teacherChange() throws ParseException{
        parseDate();
        if(teacherService.changeInfo((String)this.getSessionValue("email"), name, sex, date , school, major, studyStatus, tel, province, city, district, net)){
            this.sessionPutIn("nextPageMessage", this.getText("teaChangeSuccess"));
            return SUCCESS;
        } else {
            this.sessionPutIn("nextPageMessage", this.getText("teaChangeFailure"));
            return "input";
        }
    }
    
    public String teacherMoreChange() {
        if(teacherService.changeMoreInfo((String)this.getSessionValue("email"), underlineArea, intro, cert, experience, sprtSUnderline, sprtTUnderline, sprtOnline)){
            this.sessionPutIn("nextPageMessage", this.getText("teaMoreChangeSuccess"));
            return SUCCESS;
        } else {
            this.sessionPutIn("nextPageMessage", this.getText("teaMoreChangeFailure"));
            return "input";
        }
    }
    
    public String studentChange() throws ParseException{
        parseDate();
        if(studentService.changeInfo((String)this.getSessionValue("email"), name, sex, date, grade, school, tel, telType)){
            this.sessionPutIn("nextPageMessage", this.getText("teaChangeSuccess"));
            return SUCCESS;
        } else {
            this.sessionPutIn("nextPageMessage", this.getText("stuChangeFailure"));
            return "input";
        }
    }

    public String changePassword(){
        User u = userService.getUserByEmail((String)this.getSessionValue("email"));
        if ( !(MD5Util.MD5(oldpwd)).equals(u.getPassword())) {
            this.sessionPutIn("nextPageMessage", this.getText("passwordWrong"));
            return "input";
        } 
        if ( !newpwd.equals(newpwd2) ) {
            this.sessionPutIn("nextPageMessage", this.getText("passwordNotEqual"));
            return "input";
        }
        userService.changePassword( u.getEmail(), newpwd );
        this.sessionPutIn("nextPageMessage", this.getText("changePasswordSuccess"));
        return SUCCESS;
    }
    
    public String changePasswordById(){
        if ( !newpwd.equals(newpwd2) ) {
            this.sessionPutIn("nextPageMessage", this.getText("passwordNotEqual"));
            return "input";
        }
        userService.changePasswordById( id, newpwd );
        this.sessionPutIn("nextPageMessage", this.getText("changePasswordSuccess"));
        return SUCCESS;
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

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public SimpleDateFormat getSdf() {
        return sdf;
    }

    public void setSdf(SimpleDateFormat sdf) {
        this.sdf = sdf;
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

    public IStudentService getStudentService() {
        return studentService;
    }

    public void setStudentService(IStudentService studentService) {
        this.studentService = studentService;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getTelType() {
        return telType;
    }

    public void setTelType(String telType) {
        this.telType = telType;
    }

    public IUserService getUserService() {
        return userService;
    }

    public void setUserService(IUserService userService) {
        this.userService = userService;
    }

    public String getOldpwd() {
        return oldpwd;
    }

    public void setOldpwd(String oldpwd) {
        this.oldpwd = oldpwd;
    }

    public String getNewpwd() {
        return newpwd;
    }

    public void setNewpwd(String newpwd) {
        this.newpwd = newpwd;
    }

    public String getNewpwd2() {
        return newpwd2;
    }

    public void setNewpwd2(String newpwd2) {
        this.newpwd2 = newpwd2;
    }
    
    public String getProvince() {
        return province;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNet() {
        return net;
    }

    public void setNet(String net) {
        this.net = net;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public SimpleDateFormat getSdf2() {
        return sdf2;
    }

    public void setSdf2(SimpleDateFormat sdf2) {
        this.sdf2 = sdf2;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getLessonName() {
        return lessonName;
    }

    public void setLessonName(String lessonName) {
        this.lessonName = lessonName;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getStudyStatus() {
        return studyStatus;
    }

    public void setStudyStatus(String studyStatus) {
        this.studyStatus = studyStatus;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getUnderlineArea() {
        return underlineArea;
    }

    public void setUnderlineArea(String underlineArea) {
        this.underlineArea = underlineArea;
    }

    public String getExperience() {
        return experience;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }

    public String getCert() {
        return cert;
    }

    public void setCert(String cert) {
        this.cert = cert;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public Boolean getSprtSUnderline() {
        return sprtSUnderline;
    }

    public void setSprtSUnderline(Boolean sprtSUnderline) {
        this.sprtSUnderline = sprtSUnderline;
    }

    public Boolean getSprtTUnderline() {
        return sprtTUnderline;
    }

    public void setSprtTUnderline(Boolean sprtTUnderline) {
        this.sprtTUnderline = sprtTUnderline;
    }

    public Boolean getSprtOnline() {
        return sprtOnline;
    }

    public void setSprtOnline(Boolean sprtOnline) {
        this.sprtOnline = sprtOnline;
    }
}
