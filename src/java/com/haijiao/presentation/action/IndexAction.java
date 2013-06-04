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
import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.stereotype.Controller;

@Controller
@ParentPackage("struts-default")
@Namespace("/")
@Results({
    @Result(name="success",location="/index.jsp"),
    @Result(name="teacher",location="/teacherIndex.jsp"),
    @Result(name="student",location="/studentIndex.jsp")
})
public class IndexAction extends SessionAction {
    private ScheduleBean scheduleBean;
    @Resource
    private IUserService userService;
    @Resource
    private ITeacherService teacherService;
    @Resource
    private IStudentService studentService;
    @Resource
    private IBillService billService;
    private Teacher teacher;
    private Student student;
    private List<Bill> billList;
    private List<Clazz> classList;
    
    
    @Override
    public String execute() throws Exception {
        String email = (String)this.getSessionValue("email");
        if(email==null){
            Cookie[] cookies = ServletActionContext.getRequest().getCookies();
            if (cookies != null) {
                for (Cookie cookie : cookies) {
                    if(cookie.getName().equals("user")) {
                        email = cookie.getValue();
                        User theUser = userService.getUserByEmail(email);
                        String userType = theUser.getUserType();
                        userService.setStatus(email, User.Status.onlineAndAvailable);
                        userService.setActiveDate(email);
                        this.sessionPutIn("name", theUser.getName());
                        this.sessionPutIn("userType", userType);
                        this.sessionPutIn("email",email);
                        if(userType.equals("teacher")){
                            this.sessionPutIn("todayClazz", teacherService.getTodayClasses(email));
                        } else {
                            this.sessionPutIn("todayClazz", studentService.getTodayClasses(email));
                        }
                    }
                }
            }
        }
        if(email!=null){
            User user = userService.getUserByEmail(email);
            if(user.getUserType().equals("teacher")){
                teacher = (Teacher) user;
                scheduleBean = new ScheduleBean(teacher);
                billList = billService.getBill(email, "teacher");
                classList = teacherService.getClasses(email);
                return "teacher";
            } else {
                student = (Student) user;              
                student = studentService.getStudentByEmail(email);
                billList = billService.getBill(email, "student");
                classList = studentService.getClasses(email);
                scheduleBean = new ScheduleBean(classList);
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