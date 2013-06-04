/**
 *
 * @author Jerry Zou
 */
package com.haijiao.presentation.action;

import com.haijiao.Domain.bean.Teacher;
import com.haijiao.SupportService.service.IUserService;
import com.haijiao.global.PageBean;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
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
@ParentPackage("struts-default")
@Namespace("/")
@Action("searchTeacher")
@Results({
    @Result(name="success",location="/teachers.jsp"),
    @Result(name="input",location="/teachers.jsp"),
    @Result(name="dynamic",location="/teacherspart.jsp")
})
public class SearchTeacherAction extends RequestSessionAction {
    @Resource
    private IUserService userService;
    private String searchContent;
    private String currentPage;
    private PageBean pb;
    private Integer desc;
    private String lessonGet;
    private String netGet;
    private String role;
    private String province;
    private String city;
    private String district;
    private String sex;
    private String status;

    public SearchTeacherAction() {
    }
    
    public String normal() throws Exception{
        this.putIn("extOrder", null);
        return execute();
    }
    
    public String score() throws Exception{
        this.putIn("extOrder", "score");
        return execute();
    }
    
    public String hot() throws Exception{
        this.putIn("extOrder", "reserveNum");
        return execute();
    }
    
    public String price() throws Exception{
        this.putIn("extOrder", "wagePerhour");
        return execute();
    }
    
    public String time() throws Exception{
        this.putIn("extOrder", "lastActiveDate");
        return execute();
    }

    @Override
    public String execute() throws Exception {
        String extOrder = (String) this.getOutSession("extOrder");
        if (desc==null) {
            desc = 1;
        }
        List<String> strList = new ArrayList<String>();
        if (searchContent != null) {
            String[] strArray = searchContent.split(" ");
            strList.addAll(Arrays.asList(strArray));
        } else {
            strList.add("");
        }
        int cp;
        String returnValue;
        if (currentPage == null) {
            cp = 1;
            returnValue = SUCCESS;
        } else if(currentPage.equals("")){
            cp = 1;
            returnValue = "dynamic";
        }
        else {
            cp = Integer.parseInt(currentPage);
            returnValue = "dynamic";
        }
        int pageSize = 20;
        List<Teacher> teacherlist = userService.searchTeacherPage(strList, lessonGet, netGet, sex, role, province, city, district, status, (cp - 1) * pageSize, pageSize, extOrder, desc);
        int num = userService.getTeacherNum(strList, lessonGet, netGet, sex, role, province, city, district, status, extOrder, desc);
        pb = new PageBean(teacherlist, num, cp, pageSize);

        if (!teacherlist.isEmpty()) {
            this.putIn("message", this.getText("searchSuccess"));
        } else {
            this.putIn("message", this.getText("searchNull"));

        }
        return returnValue;
        /**
         * *
         * 此处加入出错处理
         */
    }

    public IUserService getUserService() {
        return userService;
    }

    public void setUserService(IUserService userService) {
        this.userService = userService;
    }

    public String getSearchContent() {
        return searchContent;
    }

    public void setSearchContent(String searchContent) {
        this.searchContent = searchContent;
    }

    public String getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(String currentPage) {
        this.currentPage = currentPage;
    }

    public PageBean getPb() {
        return pb;
    }
    
    public void setPb(PageBean pb) {
        this.pb = pb;
    }

    public Integer getDesc() {
        return desc;
    }

    public void setDesc(Integer desc) {
        this.desc = desc;
    }

    public String getLessonGet() {
        return lessonGet;
    }

    public void setLessonGet(String lessonGet) {
        this.lessonGet = lessonGet;
    }

    public String getNetGet() {
        return netGet;
    }

    public void setNetGet(String netGet) {
        this.netGet = netGet;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
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

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
}
