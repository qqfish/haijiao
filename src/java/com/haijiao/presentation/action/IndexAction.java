/**
 *
 * @author Jerry Zou
 */
package com.haijiao.presentation.action;

public class IndexAction extends SessionAction {
    
    @Override
    public String execute() throws Exception {
        Boolean login = (Boolean)this.getValue("login");
        if(login!=null && login==true){
            String userType = (String)this.getValue("userType");
            if(userType.equals("teacher")){
                this.putIn("message", this.getText("teacherInfo"));
                return "teacher";
            } else {
                this.putIn("message", this.getText("studentInfo"));
                return "student";
            }
        } else {
            return SUCCESS;
        }
    }
    
}
