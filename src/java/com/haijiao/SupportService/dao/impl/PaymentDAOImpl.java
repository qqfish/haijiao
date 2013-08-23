/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.haijiao.SupportService.dao.impl;

import com.haijiao.Domain.bean.Payment;
import com.haijiao.SupportService.dao.GenericHibernateDAO;
import com.haijiao.SupportService.dao.IPaymentDAO;
import java.util.List;
import org.springframework.stereotype.Repository;

/**
 *
 * @author fish
 */
@Repository
public class PaymentDAOImpl extends GenericHibernateDAO<Payment,Integer> implements IPaymentDAO{

    @Override
    public List<Payment> getPaymentList(String email, int first, int pageSize) {
        String hql = "select p from Teacher t right join t.paymentList p where t.email = '" + email + "'";
        return findPageByQuery(hql,first,pageSize);
    }

    @Override
    public int getPaymentNum(String email) {
        String hql = "select count(distinct p) from Teacher t right join t.paymentList p where t.email = '" + email + "'";
        return getNumber(hql).intValue();
    }

}
