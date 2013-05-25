/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.haijiao.SupportService.dao.impl;

import com.haijiao.Domain.bean.Mail;
import com.haijiao.SupportService.dao.GenericHibernateDAO;
import com.haijiao.SupportService.dao.IMailDAO;
import java.util.List;
import org.springframework.stereotype.Repository;

/**
 *
 * @author hp
 */

@Repository
public class MailDAOImpl extends GenericHibernateDAO<Mail,Integer> implements IMailDAO{

    @Override
    public List<Mail> getMailByEmail(String email) {
        String hql = "from Mail m where m.to.email = '" + email + "'";
        return findByQuery(hql);
    }

    @Override
    public int getMailNum(String email) {
        String hql = "select count(m) from Mail m where m.to.email = '" + email + "'";
        return getNumber(hql).intValue();
    }
        
    @Override
    public List<Mail> getUnreadMailByEmail(String email) {
        String hql = "from Mail m where m.to.email = '" + email + "' and m.read is false";
        return findByQuery(hql);
    }

    @Override
    public int getUnreadMailNum(String email) {
        String hql = "select count(m) from Mail m where m.to.email = '" + email + "' and m.read is false";
        return getNumber(hql).intValue();
    }

    @Override
    public boolean setAllMailStatus(String email) {
        String hql = "update Mail set read = true where toid = (select id from User where email = '" + email + "')";
        updateByQuery(hql);
        return true;
    }

    @Override
    public boolean removeAllMail(String email) {
        String hql = "delete Mail where toid = (select id from User where email = '" + email + "')";
        updateByQuery(hql);
        return true;
    }
    
}
