/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.haijiao.global;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import org.artofsolving.jodconverter.OfficeDocumentConverter;
import org.artofsolving.jodconverter.office.DefaultOfficeManagerConfiguration;
import org.artofsolving.jodconverter.office.OfficeManager;

/**
 *
 * @author hp
 */
public class Converter implements ServletContextListener{

    public static final String PARAMETER_OFFICE_HOME = "C:\\Program Files\\OpenOffice.org";
    //public static final String PARAMETER_OFFICE_HOME = "/usr/lib/libreoffice";
    private OfficeManager officeManager;
    static OfficeDocumentConverter documentConverter;


    @Override
    public void contextInitialized(ServletContextEvent sce) {
        officeManager = new DefaultOfficeManagerConfiguration().setOfficeHome(PARAMETER_OFFICE_HOME).buildOfficeManager();
 //       officeManager = new DefaultOfficeManagerConfiguration().buildOfficeManager();
        documentConverter = new OfficeDocumentConverter(officeManager);
        officeManager.start();
    }
    
    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        officeManager.stop();
    }

    public static OfficeDocumentConverter getDocumentConverter(){
        return documentConverter;
    }
}
