/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.haijiao.SupportService.dao.impl;

import com.haijiao.Domain.bean.Bill;
import com.haijiao.SupportService.dao.GenericHibernateDAO;
import com.haijiao.SupportService.dao.IBillDAO;
import java.util.List;
import org.springframework.stereotype.Repository;

/**
 *
 * @author hp
 */

@Repository
public class BillDAOImpl extends GenericHibernateDAO<Bill,Integer> implements IBillDAO{

    @Override
    public List<Bill> getBillByEmail(String email, String userType) {
        String hql = "from Bill b where b."+userType+".email = '" + email + "'";
        return findByQuery(hql);
    }

    @Override
    public int getBillNum(String email, String userType) {
       String hql = "select count(b) from Bill b where b."+userType+".email = '" + email + "'";
       return getNumber(hql).intValue();
    }
    
}
