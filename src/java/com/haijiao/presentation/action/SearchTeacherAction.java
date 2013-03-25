/**
 *
 * @author Jerry Zou
 */

package com.haijiao.presentation.action;

import com.haijiao.Domain.bean.Teacher;
import com.haijiao.Domain.service.ITeacherService;
import java.util.ArrayList;
import java.util.List;

public class SearchTeacherAction extends SessionAction {
    ITeacherService teacherService;
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
        teacherlist = teacherService.searchTeacher(strList);
        return SUCCESS;
        /***
         *  此处加入出错处理
         */
    }

    public ITeacherService getTeacherService() {
        return teacherService;
    }

    public void setTeacherService(ITeacherService teacherService) {
        this.teacherService = teacherService;
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
