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
public class BillDAOImpl extends GenericHibernateDAO<Bill, Integer> implements IBillDAO {

    @Override
    public List<Bill> getBillByEmail(String email, String userType) {
        String hql = "from Bill b where b." + userType + ".email = '" + email + "'";
        return findByQuery(hql);
    }

    @Override
    public List<Bill> getBillList(String email, String userType, int status, int first, int pageSize){
        String hql = "from Bill b where b." + userType + ".email = '" + email + "'";
        if(status != -1)
            hql += " and status = '" + status + "'";
        if(userType.equals("student"))
            hql += " and demand is null";
        hql += " order by id desc";
        return findPageByQuery(hql,first,pageSize);
    }
    
    @Override
    public List<Bill> getCommentBillList(String email, String userType, int first, int pageSize){
        String hql = "from Bill b where b." + userType + ".email = '" + email + "'";
        if(userType.equals("teacher"))
            hql += " and stot is not null";
        else
            hql += " and ttos is not null";
        hql += " order by id desc";
        return findPageByQuery(hql,first,pageSize);
    }
    
    @Override
    public List<Bill> getBillByEmailStatus(String email, String userType, int status) {
        String hql = "from Bill b where b." + userType + ".email = '" + email + "' and b.status = '" + status + "'";
        return findByQuery(hql);
    }

    @Override
    public int getBillNum(String email, String userType, int status) {
        String hql = "select count(b) from Bill b where b." + userType + ".email = '" + email + "'";
        if(status != -1)
            hql += " and status = '" + status + "'";
        if(userType.equals("student"))
            hql += " and demand is null";
        return getNumber(hql).intValue();
    }

    @Override
    public List<Bill> getBillByStatus(int status) {
        String hql = "from Bill b where b.status = '" + status + "'";
        return findByQuery(hql);
    }
    
}
