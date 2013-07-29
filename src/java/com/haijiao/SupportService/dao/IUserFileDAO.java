/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.haijiao.SupportService.dao;

import com.haijiao.Domain.file.UserFile;
import java.util.List;

/**
 *
 * @author hp
 */
public interface IUserFileDAO extends GenericDAO<UserFile,Integer>{
    UserFile getFile(int groupId, String fileName);
    public List<UserFile> getUserFile(String email, String groupName);
    public int getUserFileNum(String email, String groupName);
}
