/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.haijiao.SupportService.dao;

import com.haijiao.Domain.file.UserFileGroup;
import java.util.List;

/**
 *
 * @author hp
 */
public interface IUserFileGroupDAO extends GenericDAO<UserFileGroup,Integer>{
    UserFileGroup getGroupByName(String email, String name);
        
    public List<UserFileGroup> getUserFile(String email);
}
