/**
 *
 * @author Jerry Zou
 */

package com.haijiao.presentation.action;

import com.haijiao.Domain.bean.Student;
import com.haijiao.Domain.bean.Teacher;
import com.haijiao.Domain.bean.User;
import com.haijiao.SupportService.service.IStudentService;
import com.haijiao.SupportService.service.ITeacherService;
import com.haijiao.SupportService.service.IUserService;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class ChangeInfoAction extends SessionAction {
    ITeacherService teacherService;
    IStudentService studentService;
    IUserService userService;
    String id;
    String password;
    String name;
    String sex;
    String birthday;
    String school;
    String grade;
    String tel;
    String telType;
    String oldpwd;
    String newpwd;
    String newpwd2;
    String province;
    SimpleDateFormat sdf;
    SimpleDateFormat sdf2;
    Date date;
    String lessonName;
    String nextPageMessage;
    
    public ChangeInfoAction() throws ParseException{
        sdf= new SimpleDateFormat("MM/dd/yyyy");
        sdf2= new SimpleDateFormat("yy-MM-dd");
    }
    
    public void parseDate() throws ParseException{
        try{
            date = new Date(sdf.parse(birthday).getTime());
        } catch(ParseException e){
            date = new Date(sdf2.parse(birthday).getTime());
        }
    }
    
    public String teacherRegister() throws ParseException{
        if(teacherService.changeInfo((String)this.getSessionValue("email"), name, sex, null, school, null, province)){
            Teacher theTeacher = teacherService.getTeacherByEmail((String)this.getSessionValue("email"));
            this.sessionPutIn("name", theTeacher.getName());
            this.sessionPutIn("userType", "teacher");
            this.sessionPutIn("todayClazz", teacherService.getTodayClasses((String)this.getSessionValue("email")));
            return SUCCESS;
        } else {
            nextPageMessage = this.getText("teaRegisterFailure");
            return "input";
        }
    }
    
    public String studentRegister() throws ParseException{
        if(studentService.changeInfo((String)this.getSessionValue("email"), name, sex, null, grade, school, null, null)){
            Student s = studentService.getStudentByEmail((String)this.getSessionValue("email"));
            this.sessionPutIn("name", s.getName());
            this.sessionPutIn("userType", "student");
            this.sessionPutIn("todayClazz", studentService.getTodayClasses((String)this.getSessionValue("email")));
            return SUCCESS;
        } else {
            nextPageMessage = this.getText("stuRegisterFailure");
            return "input";
        }
    }
    
    public String teacherChange() throws ParseException{
        parseDate();
        if(teacherService.changeInfo((String)this.getSessionValue("email"), name, sex, date , school, tel, province)){
            Teacher theTeacher = teacherService.getTeacherByEmail((String)this.getSessionValue("email"));
            this.sessionPutIn("name", theTeacher.getName());
            this.sessionPutIn("userType", "teacher");
            this.sessionPutIn("todayClazz", teacherService.getTodayClasses((String)this.getSessionValue("email")));
            return SUCCESS;
        } else {
            nextPageMessage = this.getText("teaChangeFailure");
            return "input";
        }
    }
    
    public String studentChange() throws ParseException{
        parseDate();
        if(studentService.changeInfo((String)this.getSessionValue("email"), name, sex, date, grade, school, tel, telType)){
            Student s = studentService.getStudentByEmail((String)this.getSessionValue("email"));
            this.sessionPutIn("name", s.getName());
            this.sessionPutIn("userType", "student");
            this.sessionPutIn("todayClazz", studentService.getTodayClasses((String)this.getSessionValue("email")));
            return SUCCESS;
        } else {
            nextPageMessage = this.getText("stuChangeFailure");
            return "input";
        }
    }

    public String changePassword(){
        User u = userService.getUserByEmail((String)this.getSessionValue("email"));
        if ( !oldpwd.equals(u.getPassword())) {
            nextPageMessage = this.getText("passwordWrong");
            return "input";
        } 
        if ( !newpwd.equals(newpwd2) ) {
            nextPageMessage = this.getText("passwordNotEqual");
            return "input";
        }
        userService.changePassword( u.getEmail(), newpwd );
        nextPageMessage = this.getText("changePasswordSuccess");
        return SUCCESS;
    }
    
    public String changePasswordById(){
        if ( !newpwd.equals(newpwd2) ) {
            nextPageMessage = this.getText("passwordNotEqual");
            return "input";
        }
        userService.changePasswordById( id, newpwd );
        nextPageMessage = this.getText("changePasswordSuccess");
        return SUCCESS;
    }
    
    public String addLesson(){
        teacherService.addLesson((String)this.getSessionValue("email"), lessonName);
        nextPageMessage = "成功添加课程";
        return SUCCESS;
    }
    
    public String deleteLesson(){
        teacherService.deleteLesson((String)this.getSessionValue("email"), lessonName);
        nextPageMessage = "成功删除课程【" + lessonName + "】";
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

    public String getNextPageMessage() {
        return nextPageMessage;
    }

    public void setNextPageMessage(String nextPageMessage) {
        this.nextPageMessage = nextPageMessage;
    }
}
