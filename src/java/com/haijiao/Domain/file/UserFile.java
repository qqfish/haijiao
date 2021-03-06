/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.haijiao.Domain.file;

import com.haijiao.Domain.bean.BaseBean;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 *
 * @author hp
 */

@Entity
@Table(name="userfile")
public class UserFile extends BaseBean{
    String name;
    String url;
    Boolean owned;

    public UserFile() {
    }
    
    public UserFile(DataFile file){
        this.name = file.getName();
        this.url = file.getUrl();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Boolean getOwned() {
        return owned;
    }

    public void setOwned(Boolean owned) {
        this.owned = owned;
    }
    
}
