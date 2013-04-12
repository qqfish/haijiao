/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.haijiao.SupportService.dao.impl;

import com.haijiao.Domain.bean.Mail;
import com.haijiao.SupportService.dao.GenericHibernateDAO;
import com.haijiao.SupportService.dao.IMailDAO;
import java.util.List;

/**
 *
 * @author hp
 */
public class MailDAOImpl extends GenericHibernateDAO<Mail,Integer> implements IMailDAO{

    @Override
    public List<Mail> getMailByEmail(String email) {
        String hql = "from Mail m where m.to.email = '" + email + "'";
        return findByQuery(hql);
    }
}
