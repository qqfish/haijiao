/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.haijiao.SupportService.dao.impl;

import com.haijiao.Domain.file.PublicFile;
import com.haijiao.SupportService.dao.GenericHibernateDAO;
import com.haijiao.SupportService.dao.IPublicFileDAO;
import java.util.List;
import org.springframework.stereotype.Repository;

/**
 *
 * @author hp
 */
@Repository
public class PublicFileDAOImpl extends GenericHibernateDAO<PublicFile, Integer> implements IPublicFileDAO{

    @Override
    public PublicFile getPublicFileById(int id) {
        return findById(id);
    }

    @Override
    public List<PublicFile> getPublicFiles(String name, int first, int pageSize) {
        String hql;
        if(name == null || name.equals(""))
            hql = "from PublicFile";
        else
            hql = "from PublicFile where name = '" + name + "' and pass = true";
        return findPageByQuery(hql, first, pageSize);
    }

    @Override
    public int getPublicFileNum(String name) {
        String hql = "select count(distinct pf) from PublicFile pf where pf.name = '" + name + "' and pass = true";
        return getNumber(hql).intValue();
    }
    
}
