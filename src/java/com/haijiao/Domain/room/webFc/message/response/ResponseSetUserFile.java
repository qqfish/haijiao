/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.haijiao.Domain.room.webFc.message.response;

import com.haijiao.Domain.bean.User;
import com.haijiao.Domain.file.UserFileGroup;
import com.haijiao.Domain.room.webFc.message.Response;
import java.util.List;

/**
 *
 * @author fish
 */
public class ResponseSetUserFile extends ResponseData{
    List<UserFileGroup> groupList;

    public ResponseSetUserFile(User user) {
        type = Response.SetUserFile;
        groupList = user.getFileGroups();
    }

    public List<UserFileGroup> getGroupList() {
        return groupList;
    }

    public void setGroupList(List<UserFileGroup> groupList) {
        this.groupList = groupList;
    }
    
    
}
