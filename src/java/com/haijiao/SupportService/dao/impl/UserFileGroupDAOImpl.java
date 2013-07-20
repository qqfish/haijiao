/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.haijiao.SupportService.dao.impl;

import com.haijiao.Domain.file.UserFileGroup;
import com.haijiao.SupportService.dao.GenericHibernateDAO;
import com.haijiao.SupportService.dao.IUserFileGroupDAO;
import java.util.List;
import org.springframework.stereotype.Repository;

/**
 *
 * @author hp
 */

@Repository
public class UserFileGroupDAOImpl extends GenericHibernateDAO<UserFileGroup, Integer> implements IUserFileGroupDAO{

    @Override
    public UserFileGroup getGroupByName(String email, String name) {
        String hql = "select distinct fg from User u left join u.fileGroups fg where u.email = '"
                + email + "' and fg.groupName = '" + name + "'";
        List<UserFileGroup> ufg = findByQuery(hql);
        if(ufg.isEmpty())
            return null;
        else
            return ufg.get(0);
    }
    
    @Override
    public List<UserFileGroup> getUserFileGroup(String email) {
        String hql = "select fg from User u left join u.fileGroups fg where u.email ='" + email + "'";
        return findByQuery(hql);
    }
    
}
