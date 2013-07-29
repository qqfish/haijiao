/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.haijiao.SupportService.dao.impl;

import com.haijiao.Domain.bean.Mail;
import com.haijiao.Domain.bean.Payment;
import com.haijiao.SupportService.dao.GenericHibernateDAO;
import com.haijiao.SupportService.dao.IPaymentDAO;
import org.springframework.stereotype.Repository;

/**
 *
 * @author fish
 */
@Repository
public class PaymentDAOImpl extends GenericHibernateDAO<Payment,Integer> implements IPaymentDAO{

}
