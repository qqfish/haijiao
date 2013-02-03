/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.haijiao.dao;

import com.haijiao.dao.impl.StudentDAOImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import com.haijiao.persist.Student;

/**
 *
 * @author hp
 */
public class TestStudent {
    public void testAdd(){
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        
        Student s = new Student();
        s.setUserId(1);
        s.setGrade(100);
        s.setPassword("123");
        //s.setStudentfiles(null);
        System.out.println(s.getGrade());
        ((StudentDAOImpl)context.getBean("studentDAO")).makePersistent(s);//.getHibernateTemplate().save(s);
    }
    
    public static void main(String args[]){
        TestStudent ts = new TestStudent();
        ts.testAdd();
        System.out.println("ok");
    }
}
