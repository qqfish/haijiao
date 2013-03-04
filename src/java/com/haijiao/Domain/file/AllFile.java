/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.haijiao.Domain.file;

/**
 *
 * @author fish
 */
public class AllFile {
    protected String name;
    protected String url;

    public AllFile() {
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
    
    public boolean isFile(){
        return false;
    }
}
