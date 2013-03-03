/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.haijiao.Domain.file;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author fish
 */
public class DataFile extends AllFile{
    protected String fileUrl;

    public DataFile() {
    }

    public String getFileUrl() {
        return fileUrl;
    }

    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final DataFile other = (DataFile) obj;
        if ((this.fileUrl == null) ? (other.fileUrl != null) : !this.fileUrl.equals(other.fileUrl)) {
            return false;
        }
        return true;
    }
    
    
}
