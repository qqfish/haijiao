/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.haijiao.SupportService.dao;

import com.haijiao.Domain.bean.FreeTime;

/**
 *
 * @author hp
 */
public interface IFreeTimeDAO extends GenericDAO<FreeTime,Integer>{
    public FreeTime getTeacherFreeTime(String eamil, int day, int index);
}
