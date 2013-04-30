/**
 *
 * @author Jerry Zou
 */
package com.haijiao.presentation.action;

import com.haijiao.Domain.bean.Bill;
import com.haijiao.Domain.bean.Clazz;
import com.haijiao.Domain.bean.Student;
import com.haijiao.Domain.bean.Teacher;
import com.haijiao.Domain.bean.User;
import com.haijiao.SupportService.service.IBillService;
import com.haijiao.SupportService.service.IStudentService;
import com.haijiao.SupportService.service.ITeacherService;
import com.haijiao.SupportService.service.IUserService;
import com.haijiao.presentation.bean.schedule.ScheduleBean;
import java.util.List;

public class IndexAction extends SessionAction {
    ScheduleBean scheduleBean;
    IUserService userService;
    ITeacherService teacherService;
    IStudentService studentService;
    IBillService billService;
    Teacher teacher;
    Student student;
    List<Bill> billList;
    List<Clazz> classList;
    
    
    @Override
    public String execute() throws Exception {
        String email = (String)this.getSessionValue("email");
        if(email!=null){
            User user = userService.getUserByEmail(email);
            if(user.getUserType().equals("teacher")){
                Teacher tea = (Teacher) user;
                this.sessionPutIn("message", this.getText("teacherInfo"));
                scheduleBean = new ScheduleBean(tea.getSchedule());
                teacher = teacherService.getTeacherByEmail(email);
                billList = billService.getBill(email);
                classList = teacherService.getClasses(email);
                System.out.println(classList.size());
                return "teacher";
            } else {
                this.sessionPutIn("message", this.getText("studentInfo"));
                student = studentService.getStudentByEmail(email);
                billList = billService.getBill(email);
                classList = studentService.getClasses(email);
                return "student";
            }
        } else {
            return SUCCESS;
        }
    }

    public ScheduleBean getScheduleBean() {
        return scheduleBean;
    }

    public void setScheduleBean(ScheduleBean scheduleBean) {
        this.scheduleBean = scheduleBean;
    }

    public IUserService getUserService() {
        return userService;
    }

    public void setUserService(IUserService userService) {
        this.userService = userService;
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

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public IBillService getBillService() {
        return billService;
    }

    public void setBillService(IBillService billService) {
        this.billService = billService;
    }

    public List<Bill> getBillList() {
        return billList;
    }

    public void setBillList(List<Bill> billList) {
        this.billList = billList;
    }

    public List<Clazz> getClassList() {
        return classList;
    }

    public void setClassList(List<Clazz> classList) {
        this.classList = classList;
    }
}