/**
 *
 * @author Jerry Zou
 */

package com.haijiao.presentation.action;

import com.haijiao.Domain.bean.Bill;
import com.haijiao.Domain.bean.Student;
import com.haijiao.Domain.bean.Teacher;
import com.haijiao.SupportService.service.IBillService;
import com.haijiao.SupportService.service.IStudentService;
import com.haijiao.SupportService.service.ITeacherService;
import com.haijiao.presentation.bean.infoSchedule.InfoScheduleBean;
import java.util.List;
import javax.annotation.Resource;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.stereotype.Controller;

@Controller
@ParentPackage("struts-default")
@Namespace("/")
@Action("getTeacherInfo")
@Results({
    @Result(name="success",location="/teaInfo.jsp"),
    @Result(name="teacher",type="chain",location="index")
})
public class GetTeacherInfoAction extends RequestSessionAction{
    @Resource
    private ITeacherService teacherService;
    @Resource
    private IStudentService studentService;
    private Teacher tea;
    private InfoScheduleBean scheduleBean;
    private InfoScheduleBean studentScheduleBean;
    private List<Bill> billList;
    @Resource
    private IBillService billService;

    @Override
    public String execute(){
        String email = (String)this.getOutRequest("teacherEmail");
        tea = teacherService.getTeacherByEmail(email);
        if(!email.equals(this.getOutSession("email")))
            teacherService.increseObNum(email);
        scheduleBean = new InfoScheduleBean(tea);
        billList = billService.getBill(email, "teacher");
        
        String stuEmail = (String) this.getOutSession("email");
        if(stuEmail != null && stuEmail.equals(email)){
            return "teacher";
        }
        String userType = (String) this.getOutSession("userType");
        if(stuEmail != null && userType.equals("student")){
            Student stu = studentService.getStudentByEmail(stuEmail);
            studentScheduleBean = new InfoScheduleBean(stu.getSchedule());
        }
        return SUCCESS;
    }

    public ITeacherService getTeacherService() {
        return teacherService;
    }

    public void setTeacherService(ITeacherService teacherService) {
        this.teacherService = teacherService;
    }

    public Teacher getTea() {
        return tea;
    }

    public void setTea(Teacher tea) {
        this.tea = tea;
    }

    public InfoScheduleBean getScheduleBean() {
        return scheduleBean;
    }

    public void setScheduleBean(InfoScheduleBean scheduleBean) {
        this.scheduleBean = scheduleBean;
    }

    public List<Bill> getBillList() {
        return billList;
    }

    public void setBillList(List<Bill> billList) {
        this.billList = billList;
    }

    public IBillService getBillService() {
        return billService;
    }

    public void setBillService(IBillService billService) {
        this.billService = billService;
    }

    public IStudentService getStudentService() {
        return studentService;
    }

    public void setStudentService(IStudentService studentService) {
        this.studentService = studentService;
    }

    public InfoScheduleBean getStudentScheduleBean() {
        return studentScheduleBean;
    }

    public void setStudentScheduleBean(InfoScheduleBean studentScheduleBean) {
        this.studentScheduleBean = studentScheduleBean;
    }
    
}
