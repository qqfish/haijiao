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

public class SearchTeacherAction extends RequestSessionAction {

    IUserService userService;
    String searchContent;
    String currentPage;
    PageBean pb;
    Integer desc;
    String lessonGet;
    String gradeGet;

    public SearchTeacherAction() {
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

    @Override
    public String execute() throws Exception {
        String extOrder = (String) this.getOutSession("extOrder");
        if (desc==null) {
            desc = 0;
        }
        List<String> strList = new ArrayList<String>();
        if (searchContent != null) {
            String[] strArray = searchContent.split(" ");
            strList.addAll(Arrays.asList(strArray));
        } else {
            strList.add("");
        }
        List<String> strList2 = new ArrayList<String>();
        strList2.add(lessonGet);
        strList2.add(gradeGet);
        int cp;
        String returnValue;
        if (currentPage == null) {
            cp = 1;
            returnValue = SUCCESS;
        } else {
            cp = Integer.parseInt(currentPage);
            returnValue = "dynamic";
        }
        int pageSize = 20;
        List<Teacher> teacherlist = userService.searchTeacherPage(strList, strList2, (cp - 1) * pageSize, pageSize, extOrder, desc);
        int num = userService.getTeacherNum(strList);
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

    public String getGradeGet() {
        return gradeGet;
    }

    public void setGradeGet(String gradeGet) {
        this.gradeGet = gradeGet;
    }
}
