/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.haijiao.SupportService.dao.impl;

import com.haijiao.Domain.bean.Picture;
import com.haijiao.SupportService.dao.GenericHibernateDAO;
import com.haijiao.SupportService.dao.IPictureDAO;
import org.springframework.stereotype.Repository;

/**
 *
 * @author hp
 */
@Repository
public class PictureDAOImpl extends GenericHibernateDAO<Picture,Integer> implements IPictureDAO{

}
