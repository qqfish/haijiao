/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.haijiao.SupportService.dao.impl;

import com.haijiao.Domain.bean.Demand;
import com.haijiao.SupportService.dao.GenericHibernateDAO;
import com.haijiao.SupportService.dao.IDemandDAO;
import org.springframework.stereotype.Repository;

/**
 *
 * @author hp
 */

@Repository
public class DemandDAOImpl extends GenericHibernateDAO<Demand,Integer> implements IDemandDAO{
    
}
