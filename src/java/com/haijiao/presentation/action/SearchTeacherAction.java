/**
 *
 * @author Jerry Zou
 */

package com.haijiao.presentation.action;

import com.haijiao.Domain.bean.Teacher;
import com.haijiao.SupportService.service.IUserService;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SearchTeacherAction extends SessionAction {
    IUserService userService;
    String searchContent;
    List<Teacher> teacherlist;
    
    @Override
    public String execute() throws Exception{
        List<String> strList = new ArrayList<String>();
        String[] strArray = searchContent.split(" ");
        strList.addAll(Arrays.asList(strArray));
        teacherlist = new ArrayList<Teacher>();
        teacherlist = userService.searchTeacher(strList);
        if(!teacherlist.isEmpty()){
            this.putIn("message", this.getText("searchSuccess"));
        } else {
            this.putIn("message", this.getText("searchNull"));
        }
        return SUCCESS;
        /***
         *  此处加入出错处理
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

    public List<Teacher> getTeacherlist() {
        return teacherlist;
    }

    public void setTeacherlist(List<Teacher> teacherlist) {
        this.teacherlist = teacherlist;
    }
            
    

}
