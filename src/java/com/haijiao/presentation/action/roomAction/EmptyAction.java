/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.haijiao.presentation.action.roomAction;

import com.haijiao.presentation.action.RequestSessionAction;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.stereotype.Controller;

/**
 *
 * @author fish
 */
@Controller
@ParentPackage("haijiao")
@Namespace("/")
@Results({
    @Result(name = "input", location = "/room/empty.jsp")
})
@Action("empty")
public class EmptyAction extends RequestSessionAction {
    @Override
    public String execute(){
        return INPUT;
    }
}
