/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.haijiao.SupportService.dao.impl;

import com.haijiao.Domain.file.UserFile;
import com.haijiao.SupportService.dao.GenericHibernateDAO;
import com.haijiao.SupportService.dao.IUserFileDAO;
import java.util.List;
import org.springframework.stereotype.Repository;

/**
 *
 * @author hp
 */

@Repository
public class UserFileDAOImpl extends GenericHibernateDAO<UserFile, Integer> implements IUserFileDAO {
    
    @Override
    public UserFile getFile(int groupId, String fileName){
        String hql = "select distinct uf from UserFileGroup ufg left join ufg.files uf where ufg.id = '"
                + groupId + "' and uf.name = '" + fileName + "'";
        List<UserFile> luf = findByQuery(hql);
        if(luf.isEmpty())
            return null;
        else
            return luf.get(0);
    }
    
}
