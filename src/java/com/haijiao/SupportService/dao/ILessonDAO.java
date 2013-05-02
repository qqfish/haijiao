/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.haijiao.SupportService.dao;

import com.haijiao.Domain.bean.Lesson;

/**
 *
 * @author hp
 */
public interface ILessonDAO extends GenericDAO<Lesson,Integer>{
    public Lesson getLessonByName(String email, String lesson);
}
