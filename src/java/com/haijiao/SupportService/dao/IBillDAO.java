/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.haijiao.SupportService.dao;

import com.haijiao.Domain.bean.Bill;
import java.util.List;

/**
 *
 * @author hp
 */
public interface IBillDAO extends GenericDAO<Bill,Integer>{
    public List<Bill> getBillByEmail(String email, String userType);
    public List<Bill> getUnfinishedBillByEmail(String email, String userType);
    public int getBillNum(String email, String userType);
}
