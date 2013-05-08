/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.haijiao.SupportService.dao;

import com.haijiao.Domain.bean.ResetInfo;
import java.util.List;

/**
 *
 * @author hp
 */
public interface IResetInfoDAO extends GenericDAO<ResetInfo,Integer>{
    public List<ResetInfo> getResetInfoByUser(int userid);
}
