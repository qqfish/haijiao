/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.haijiao.SupportService.dao;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author hp
 */
public interface GenericDAO <T,ID extends Serializable>{
    T findById(ID id); 
    List<T> findAll();
    //save
    boolean makePersistent(T entity); 
    //remove
    boolean makeTransient(T entity);
    void update(T instance);
    void updateByQuery(String hql);
    public List<T> findByQuery(String hql);
    public List<T> findPageByQuery(String hql, int first, int pagesize);
    public Number getNumber(String hql);
}
