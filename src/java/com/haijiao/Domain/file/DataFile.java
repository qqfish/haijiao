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
}
