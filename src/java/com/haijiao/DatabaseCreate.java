/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.haijiao;

import java.io.File;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.classic.Session;
import org.hibernate.tool.hbm2ddl.SchemaExport;

/**
 *
 * @author hp
 */
public class DatabaseCreate {
    
    public static void main(String[] args) {
            Configuration config = new Configuration().configure();
            System.out.println("Creating tables...");
            SchemaExport schemaExport = new SchemaExport(config);
            schemaExport.create(true, true);
            System.out.println("Table created.");
    }
}
