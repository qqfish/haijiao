/**
 *
 * @author Jerry Zou
 */

package com.haijiao.presentation.action;

import com.haijiao.Domain.bean.Bill;
import com.haijiao.Domain.bean.Teacher;
import com.haijiao.SupportService.service.IBillService;
import com.haijiao.SupportService.service.ITeacherService;
import com.haijiao.presentation.bean.schedule.ScheduleBean;
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
    @Result(name="success",location="/teaInfo.jsp")
})
public class GetTeacherInfoAction extends RequestSessionAction{
    @Resource
    private ITeacherService teacherService;
    private Teacher tea;
    private ScheduleBean scheduleBean;
    private List<Bill> billList;
    @Resource
    private IBillService billService;

    @Override
    public String execute(){
        String email = (String)this.getOutRequest("teacherEmail");
        tea = teacherService.getTeacherByEmail(email);
        if(!email.equals(this.getOutSession("email")))
            teacherService.increseObNum(email);
        scheduleBean = new ScheduleBean(tea);
        billList = billService.getBill(email, "teacher");
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

    public ScheduleBean getScheduleBean() {
        return scheduleBean;
    }

    public void setScheduleBean(ScheduleBean scheduleBean) {
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
    
}
