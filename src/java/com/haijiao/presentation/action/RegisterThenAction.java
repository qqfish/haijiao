/**
 *
 * @author Jerry Zou
 */

package com.haijiao.presentation.action;

import com.haijiao.Domain.bean.Student;
import com.haijiao.Domain.bean.Teacher;
import com.haijiao.SupportService.service.IStudentService;
import com.haijiao.SupportService.service.ITeacherService;
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
@Action("registerThen")
@Results({
    @Result(name="input",location="/register.jsp"),
    @Result(name="success",type="redirect",location="toChangeInfo.action?tab=detail")
})
public class RegisterThenAction extends SessionAction {
    @Resource
    private ITeacherService teacherService;
    @Resource
    private IStudentService studentService;
    private String id;
    private String password;
    private String name;
    private String sex;
    private String school;
    private String major;
    private String studyStatus;
    private String grade;
    private String province;
    private String city;
    private String district;
    private String net;
    private String tel;
    private String origin;
    private String telType;
    
    public String teacherRegister(){
        if(teacherService.changeInfo((String)this.getSessionValue("email"), name, sex, null, school, major,studyStatus, tel, province, city, district, net, origin)){
            Teacher theTeacher = teacherService.getTeacherByEmail((String)this.getSessionValue("email"));
            this.sessionPutIn("name", theTeacher.getName());
            this.sessionPutIn("userType", "teacher");
//            this.sessionPutIn("todayClazz", teacherService.getTodayClasses((String)this.getSessionValue("email")));
            return SUCCESS;
        } else {
            this.sessionPutIn("nextPageMessage", "注册信息提交失败！");
            return "input";
        }
    }
    
    public String studentRegister(){
        if(studentService.changeInfo((String)this.getSessionValue("email"), name, sex, null, grade, school, tel, telType)){
            Student s = studentService.getStudentByEmail((String)this.getSessionValue("email"));
            this.sessionPutIn("name", s.getName());
            this.sessionPutIn("userType", "student");
//            this.sessionPutIn("todayClazz", studentService.getTodayClasses((String)this.getSessionValue("email")));
            return SUCCESS;
        } else {
            this.sessionPutIn("nextPageMessage", "注册信息提交失败！");
            return INPUT;
        }
    }

    public ITeacherService getTeacherService() {
        return teacherService;
    }

    public void setTeacherService(ITeacherService teacherService) {
        this.teacherService = teacherService;
    }

    public IStudentService getStudentService() {
        return studentService;
    }

    public void setStudentService(IStudentService studentService) {
        this.studentService = studentService;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getStudyStatus() {
        return studyStatus;
    }

    public void setStudyStatus(String studyStatus) {
        this.studyStatus = studyStatus;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getNet() {
        return net;
    }

    public void setNet(String net) {
        this.net = net;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
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

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getTelType() {
        return telType;
    }

    public void setTelType(String telType) {
        this.telType = telType;
    }
    
}
