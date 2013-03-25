/**
 *
 * @author Jerry Zou
 */

package com.haijiao.presentation.action;

import com.haijiao.Domain.bean.Teacher;
import com.haijiao.Domain.service.ITeacherService;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SearchTeacherAction extends SessionAction {
    ITeacherService teacherService;
    String searchContent;
    List<Teacher> teacherlist;
    
    @Override
    public String execute() throws Exception{
        System.out.println("1");
        List<String> strList = new ArrayList<String>();
        String[] strArray = searchContent.split(" ");
        strList.addAll(Arrays.asList(strArray));
        teacherlist = new ArrayList<Teacher>();
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
