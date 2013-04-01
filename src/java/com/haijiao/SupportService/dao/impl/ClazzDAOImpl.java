/**
 *
 * @author Jerry Zou
 */

package com.haijiao.SupportService.dao.impl;

import com.haijiao.Domain.bean.Clazz;
import com.haijiao.SupportService.dao.GenericHibernateDAO;
import com.haijiao.SupportService.dao.IClazzDAO;
import org.springframework.stereotype.Repository;

@Repository
public class ClazzDAOImpl extends GenericHibernateDAO<Clazz,Integer> implements IClazzDAO{
}
