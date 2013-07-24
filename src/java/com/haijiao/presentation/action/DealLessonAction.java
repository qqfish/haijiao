/**
 *
 * @author Jerry Zou
 */

package com.haijiao.presentation.action;

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
@Action("dealLesson")
@Results({
    @Result(name="input",type="redirect",location="index.action"),
    @Result(name="success",type="redirect",location="index.action")
})
public class DealLessonAction extends SessionAction {
    @Resource
    private ITeacherService teacherService;
    private String lessonName;
    private int price;
    
    public String addLesson(){
        teacherService.addLesson((String)this.getSessionValue("email"), lessonName, price);
        this.sessionPutIn("nextPageMessage","成功添加课程");
        return SUCCESS;
    }
    
    public String deleteLesson(){
        teacherService.deleteLesson((String)this.getSessionValue("email"), lessonName);
        this.sessionPutIn("nextPageMessage","成功删除课程");
        return SUCCESS;
    }

    public ITeacherService getTeacherService() {
        return teacherService;
    }

    public void setTeacherService(ITeacherService teacherService) {
        this.teacherService = teacherService;
    }

    public String getLessonName() {
        return lessonName;
    }

    public void setLessonName(String lessonName) {
        this.lessonName = lessonName;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
    
}
