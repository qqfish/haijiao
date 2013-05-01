/**
 *
 * @author Jerry Zou
 */
package com.haijiao.presentation.action;

import com.haijiao.SupportService.service.IClassService;

public class DealApplyAction extends RequestSessionAction {

    IClassService classService;

    @Override
    public String execute() {
        if ("stop".equals((String) this.getOutRequest("button"))) {
            stop();
        } else if ("accept".equals((String) this.getOutRequest("button"))) {
            accept();
        } else if ("decline".equals((String) this.getOutRequest("button"))) {
            decline();
        } else if("studentStop".equals((String)this.getOutRequest("button"))){
            studentStop();
        } else {
            //do nothing;
        }
        return SUCCESS;
    }

    public void stop() {
        Integer classId = Integer.parseInt((String) this.getOutRequest("classId"));
        classService.teacherPauseBook(classId, 1);
    }

    public void accept() {
        Integer classId = Integer.parseInt((String) this.getOutRequest("classId"));
        classService.dealWithReservation(classId, true);
    }

    public void decline() {
        Integer classId = Integer.parseInt((String) this.getOutRequest("classId"));
        classService.dealWithReservation(classId, false);
    }
    
    public void studentStop(){
        Integer classId = Integer.parseInt((String)this.getOutRequest("classId"));
        classService.studentPauseBook(classId, 1);
    }

    public IClassService getClassService() {
        return classService;
    }

    public void setClassService(IClassService classService) {
        this.classService = classService;
    }
}
