/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.haijiao.SupportService.dao;

import com.haijiao.Domain.bean.Demand;

/**
 *
 * @author hp
 */
public interface IDemandDAO extends GenericDAO<Demand,Integer>{
    public Demand getStudentDemand(String email);
}
