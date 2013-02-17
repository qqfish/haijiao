/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.haijiao.Domain.file;

import com.haijiao.global.config;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author fish
 */
public class FileManager {

    private List<DirectoryFile> DirectoryFile;

    public FileManager() {
        DirectoryFile = new ArrayList();
    }

    public String getCurrentUrl() {
        String url = config.fileRoot;
        for (int i = 0; i < DirectoryFile.size(); i++) {
            url += "/" + DirectoryFile.get(i).getName();
        }
        return url;
    }

    public boolean addFile(FileInputStream input, String FileName) throws IOException {
        File file = new File(getCurrentUrl() + "/" + FileName);
        if (file.exists()) {
            return false;
        }
        file.createNewFile();
        FileOutputStream out = new FileOutputStream(file);
        byte[] bt = new byte[1024];
        int count;
        while ((count = input.read(bt)) > 0) {
            out.write(bt, 0, count);
        }
        out.close();
        return true;
    }
    
    public List<AllFile> getFileList(){
        List<AllFile> result = new ArrayList();
        
        File dir = new File(getCurrentUrl());
        File[] file = dir.listFiles();
        for(int i = 0; i < file.length; i++){
            if(file[i].isDirectory()){
                DirectoryFile dirFile = new DirectoryFile();
                dirFile.setName(file[i].getName());
                result.add(dirFile);
            } else {
                DataFile dFile = new DataFile();
                dFile.setFileUrl(file[i].getAbsolutePath());
                dFile.setName(file[i].getName());
                result.add(dFile);
            }
        }
        return result;
    }
    
    public void enterDir(DirectoryFile dir){
        DirectoryFile.add(dir);
    }
    
    public void enterRoot(){
        DirectoryFile.clear();
    }
    
    
}
