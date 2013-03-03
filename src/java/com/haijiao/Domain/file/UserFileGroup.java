/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.haijiao.Domain.file;

import com.haijiao.global.config;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author fish
 */
public class UserFileGroup{
    private String groupName;
    private List<DataFile> files;
    
    public UserFileGroup(String groupName){
        this.groupName = groupName;
        this.files = new ArrayList();
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }
    
    public void addFile(DataFile file){
        if(!files.contains(file)){
            files.add(file);
        }
    }
    
    public void removeFile(DataFile file){
        files.remove(file);
    }
    
    public DataFile getFile(String url){
        for(int i = 0; i < files.size(); i++){
            if(files.get(i).getFileUrl().equals(url)){
                return files.get(i);
            }
        }
        return null;
    }

    public List<DataFile> getFiles() {
        return files;
    }
    
}
