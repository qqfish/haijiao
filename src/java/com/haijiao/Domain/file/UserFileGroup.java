/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.haijiao.Domain.file;

import com.haijiao.Domain.bean.BaseBean;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

/**
 *
 * @author fish
 */

@Entity    
@Table(name="userfilegroup")     
@PrimaryKeyJoinColumn(name="UserFileGroupId")
public class UserFileGroup extends BaseBean{
    private String groupName;
    
    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name="groupid")
    private List<UserFile> files;

    public UserFileGroup() {
        this.files = new ArrayList();
    }
    
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
    
    public void addFile(UserFile file){
        if(!files.contains(file)){
            files.add(file);
        }
    }
    
    public void removeFile(UserFile file){
        files.remove(file);
    }
    
    public UserFile getFile(String name){
        for(int i = 0; i < files.size(); i++){
            if(files.get(i).getName().equals(name)){
                return files.get(i);
            }
        }
        return null;
    }

    public List<UserFile> getFiles() {
        return files;
    }
    
}
