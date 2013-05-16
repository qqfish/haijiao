/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.haijiao.SupportService.dao;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import javax.annotation.Resource;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;

/**
 *
 * @author hp
 */
public abstract class GenericHibernateDAO <T,ID extends Serializable> implements GenericDAO<T,ID>{
    private Class<T> persistentClass;
    
    @Resource(name="sessionFactory")
    private SessionFactory  sessionFactory;

    public GenericHibernateDAO() {
        this.persistentClass = (Class<T>) ((ParameterizedType) getClass()
                                .getGenericSuperclass()).getActualTypeArguments()[0];
    }

    public SessionFactory getSessionFactory() {
        if (sessionFactory == null)
            throw new IllegalStateException("SessionFactory has not been set on DAO before usage");
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public Class<T> getPersistentClass() {
        return persistentClass;
    }
    
    @Override
    public T findById(ID id) {
        T entity = (T) sessionFactory.getCurrentSession().get(getPersistentClass(), id);
        return entity;
    }

    @Override
    public List<T> findAll() {
        return findByCriteria();
    }

    @Override
    public boolean makePersistent(T entity) {
        sessionFactory.getCurrentSession().save(entity);
        return true;
    }

    @Override
    public boolean makeTransient(T entity) {
        sessionFactory.getCurrentSession().delete(entity);
        return true;
    }

    @Override
    public void update(T instance) {
        sessionFactory.getCurrentSession().saveOrUpdate(instance);
    }

    @Override
    public void updateByQuery(String hql){
        sessionFactory.getCurrentSession().createQuery(hql).executeUpdate();
    }
    
    @Override
    public List<T> findByQuery(String hql) {
        return sessionFactory.getCurrentSession().createQuery(hql).list();
    }

    @Override
    public List<T> findPageByQuery(String hql, int first, int pagesize){
        return sessionFactory.getCurrentSession().createQuery(hql).setFirstResult(first).setMaxResults(pagesize).list();
    }
    
    @Override
    public Number getNumber(String hql){
        return (Number) sessionFactory.getCurrentSession().createQuery(hql).uniqueResult();
    }
    
    private List<T> findByCriteria(Criterion... criterion) {
        Criteria crit = sessionFactory.getCurrentSession().createCriteria(getPersistentClass());
        for (Criterion c : criterion) {
            crit.add(c);
        }
        return crit.list();
    }
    
}
