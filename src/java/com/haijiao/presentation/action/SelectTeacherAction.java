/**
 *
 * @author Jerry Zou
 */

package com.haijiao.presentation.action;

import com.haijiao.Domain.bean.Teacher;

public class SelectTeacherAction extends SessionAction {
    Teacher selectedTeacher;
    
    public String execute(){
        /* 直接把取到的教师信息传递到下一页详细信息，
         * so do nothing
         */
        return SUCCESS;
    }

    public Teacher getSelectedTeacher() {
        return selectedTeacher;
    }

    public void setSelectedTeacher(Teacher selectedTeacher) {
        this.selectedTeacher = selectedTeacher;
    }
    

}
