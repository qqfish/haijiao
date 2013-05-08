/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.haijiao.SupportService.dao.impl;

import com.haijiao.Domain.bean.ResetInfo;
import com.haijiao.SupportService.dao.GenericHibernateDAO;
import com.haijiao.SupportService.dao.IResetInfoDAO;
import java.util.List;
import org.springframework.stereotype.Repository;

/**
 *
 * @author hp
 */
@Repository
public class ResetInfoDAOImpl extends GenericHibernateDAO<ResetInfo,Integer> implements IResetInfoDAO{

    @Override
    public List<ResetInfo> getResetInfoByUser(int id) {
        String hql = "from ResetInfo r where r.userid = '" + id + "' order by r.createTime desc";
        return findByQuery(hql);
    }
    
}
