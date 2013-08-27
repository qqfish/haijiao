/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.haijiao.SupportService.dao.impl;

import com.haijiao.Domain.bean.Demand;
import com.haijiao.SupportService.dao.GenericHibernateDAO;
import com.haijiao.SupportService.dao.IDemandDAO;
import java.util.List;
import org.springframework.stereotype.Repository;

/**
 *
 * @author hp
 */

@Repository
public class DemandDAOImpl extends GenericHibernateDAO<Demand,Integer> implements IDemandDAO{

    @Override
    public Demand getStudentDemand(String email) {
        String hql = "select d from Student s right join Demand d where s.email='"+ email + "'";
        List<Demand> ld = findByQuery(hql);
        if(ld.size() == 1){
            return findByQuery(hql).get(0);
        } else {
            return null;
        }
    }
    
}
