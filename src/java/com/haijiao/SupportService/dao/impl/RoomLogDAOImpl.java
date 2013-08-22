/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.haijiao.SupportService.dao.impl;

import com.haijiao.Domain.Manager.RoomLog;
import com.haijiao.SupportService.dao.GenericHibernateDAO;
import com.haijiao.SupportService.dao.IRoomLogDAO;
import org.springframework.stereotype.Repository;

/**
 *
 * @author fish
 */
@Repository
public class RoomLogDAOImpl extends GenericHibernateDAO<RoomLog,Integer> implements IRoomLogDAO{
}
