/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.haijiao.presentation.action;

import com.haijiao.Domain.file.PublicFile;
import com.haijiao.SupportService.service.IUserService;
import com.haijiao.global.PageBean;
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
@Action("getPublicFilelist")
@Results({
    @Result(name="success",location="/publicfilepart.jsp")
})
public class GetPublicFilelistAction extends SessionAction{
    @Resource
    IUserService userService;
    private String currentPage;
    private String name;
    private PageBean pb;
    
    @Override
    public String execute(){
        int cp = Integer.parseInt(currentPage);
        int pageSize = 20;
        List<PublicFile> lpf =userService.getPublicFilelist(name, (cp -1) * pageSize, pageSize);
        int num = userService.getPubliFileNum(name);
        pb = new PageBean(lpf, num, cp, pageSize);
        return SUCCESS;
    }

    public void setUserService(IUserService userService) {
        this.userService = userService;
    }

    public String getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(String currentPage) {
        this.currentPage = currentPage;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public PageBean getPb() {
        return pb;
    }

    public void setPb(PageBean pb) {
        this.pb = pb;
    }
    
}
