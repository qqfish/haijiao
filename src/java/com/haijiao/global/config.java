/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.haijiao.global;

import org.apache.struts2.ServletActionContext;

/**
 *
 * @author fish
 */
public class config {
    // room config
    static public String roomFileUrl = "roomUpload/roomFile";
    static public String pageImageType = "png";
    static public String pageDataUriPrefix = "data:image/png;base64,";
    static public String newDocumentName = "草稿纸.pdf";
    static public String tmpRoomFile = "roomUpload/tmp";
    static public String downloadDir = "download";
    
    //File config
    static public String fileRoot = "e:\\file";
    static public String defaultGroupName = "no group";
    static public String fileHome = "upload/public";
    
    //User file
    static public String userHome = "upload/user";
    static public String imageFolder = "image";
    static public String videoFolder = "video";
    static public String fileFolder = "file";
    
    //contact
    static public String contactFile = "contact.csv";
    
    static public String websiteURI = "localhost:8080";
}
