/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.haijiao.presentation.action;

import com.haijiao.Domain.bean.Student;
import com.haijiao.SupportService.service.IStudentService;
import com.haijiao.global.PageBean;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.annotation.Resource;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.stereotype.Controller;

/**
 *
 * @author hp
 */

@Controller
@ParentPackage("struts-default")
@Namespace("/")
@Action("searchStudent")
@Results({
    @Result(name="success",location="/students.jsp"),
    @Result(name="input",location="/students.jsp"),
    @Result(name="dynamic",location="/studentspart.jsp")
})
public class SearchStudentAction extends RequestSessionAction{
    @Resource
    IStudentService studentService;
    private String searchContent;
    private String currentPage;
    private PageBean pb;
    private Integer desc;
    private String lesson;
    private String way;
    private String net;
    private String province;
    private String city;
    private String district;
    private String sex;
    private String status;
    
    public String normal() {
        this.sessionPutIn("extOrder", null);
        return execute();
    }
    
    public String price() throws Exception{
        this.sessionPutIn("extOrder", "total");
        return execute();
    }
    
    @Override
    public String execute() {
        String extOrder = (String) this.getOutSession("extOrder");
        if(extOrder == null)
            extOrder = "publishTime";
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
        List<Student> studentlist = studentService.searchStudentPage(strList, lesson, way, net, sex, province, city, district, status, (cp - 1) * pageSize, pageSize, extOrder, desc);
        int num = studentService.searchStudentNum(strList, lesson, way, net, sex, province, city, district, status, extOrder, desc);
        pb = new PageBean(studentlist, num, cp, pageSize);
        if (studentlist == null || studentlist.isEmpty()) {
            this.sessionPutIn("message", this.getText("searchNull"));
        } else {
            this.sessionPutIn("message", this.getText("searchSuccess"));
        }
        return returnValue;
    }

    public void setStudentService(IStudentService studentService) {
        this.studentService = studentService;
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

    public String getLesson() {
        return lesson;
    }

    public void setLesson(String lesson) {
        this.lesson = lesson;
    }

    public String getWay() {
        return way;
    }

    public void setWay(String way) {
        this.way = way;
    }

    public String getNet() {
        return net;
    }

    public void setNet(String net) {
        this.net = net;
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
