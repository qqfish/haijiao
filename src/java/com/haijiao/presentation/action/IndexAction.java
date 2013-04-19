/**
 *
 * @author Jerry Zou
 */
package com.haijiao.presentation.action;

public class IndexAction extends SessionAction {
    
    @Override
    public String execute() throws Exception {
        Boolean login = (Boolean)this.getSessionValue("login");
        if(login!=null && login==true){
            String userType = (String)this.getSessionValue("userType");
            if(userType.equals("teacher")){
                this.sessionPutIn("message", this.getText("teacherInfo"));
                return "teacher";
            } else {
                this.sessionPutIn("message", this.getText("studentInfo"));
                return "student";
            }
        } else {
            return SUCCESS;
        }
    }
    
}
