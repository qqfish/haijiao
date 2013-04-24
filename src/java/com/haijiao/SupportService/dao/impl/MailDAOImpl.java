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
    public List<Mail> getUnreadMailByEmail(String email) {
        String hql = "from Mail m where m.to.email = '" + email + "' and m.read is false";
        return findByQuery(hql);
    }
}
