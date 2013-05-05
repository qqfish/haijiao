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

public class GetTeacherInfoAction extends RequestAction{
    ITeacherService teacherService;
    Teacher tea;
    ScheduleBean scheduleBean;
    List<Bill> billList;
    IBillService billService;

    @Override
    public String execute(){
        String email = (String)this.getRequestValue("teacherEmail");
        tea = teacherService.getTeacherByEmail(email);
        scheduleBean = new ScheduleBean(tea);
        billList = billService.getBill(email);
        System.out.println(scheduleBean.toJson());
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
