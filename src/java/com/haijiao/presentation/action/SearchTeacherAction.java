/**
 *
 * @author Jerry Zou
 */

package com.haijiao.presentation.action;

import com.haijiao.Domain.bean.Teacher;
import com.haijiao.Domain.service.IUserService;
import java.util.ArrayList;
import java.util.List;

public class SearchTeacherAction extends SessionAction {
    IUserService userService;
    String searchContent;
    List<Teacher> teacherlist;
    
    public SearchTeacherAction(){
        teacherlist = new ArrayList<Teacher>();
    }
    
    public String execute(){
        List<String> strList = new ArrayList<String>();
        String[] strArray = searchContent.split(" ");
        for(int i=0; i<strArray.length; i++){
            strList.add(strArray[i]);
        }
        teacherlist = userService.searchTeacher(strList);
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
