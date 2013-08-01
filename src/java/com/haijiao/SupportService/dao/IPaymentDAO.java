/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.haijiao.SupportService.dao;

import com.haijiao.Domain.bean.Payment;
import java.util.List;


/**
 *
 * @author fish
 */
public interface IPaymentDAO extends GenericDAO<Payment,Integer>{
    public List<Payment> getPaymentList(String email, int first, int pageSize);
    public int getPaymentNum(String email);
}
