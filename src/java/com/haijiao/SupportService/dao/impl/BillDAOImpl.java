/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.haijiao.SupportService.dao.impl;

import com.haijiao.Domain.bean.Bill;
import com.haijiao.SupportService.dao.GenericHibernateDAO;
import com.haijiao.SupportService.dao.IBillDAO;
import java.util.List;

/**
 *
 * @author hp
 */
public class BillDAOImpl extends GenericHibernateDAO<Bill,Integer> implements IBillDAO{

    @Override
    public List<Bill> getBillByEmail(String email) {
        String hql = "from Bill b where b.user.email = '" + email + "'";
        return findByQuery(hql);
    }
    
}
