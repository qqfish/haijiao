/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.haijiao.SupportService.dao.impl;

import com.haijiao.Domain.Manager.MoneyRequest;
import com.haijiao.SupportService.dao.GenericHibernateDAO;
import com.haijiao.SupportService.dao.IMoneyRequestDAO;
import org.springframework.stereotype.Repository;

/**
 *
 * @author fish
 */
@Repository
public class MoneyRequestDAOImpl extends GenericHibernateDAO<MoneyRequest,Integer> implements IMoneyRequestDAO{
}
