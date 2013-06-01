/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.haijiao.SupportService.dao;

import com.haijiao.Domain.file.UserFile;

/**
 *
 * @author hp
 */
public interface IUserFileDAO extends GenericDAO<UserFile,Integer>{
    UserFile getFile(int groupId, String fileName);
}
