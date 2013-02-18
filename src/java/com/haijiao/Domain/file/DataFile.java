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
    protected List<Index> indexs;

    public DataFile() {
        indexs = new ArrayList();
    }
    
    public void addIndex(Index index){
        if(!indexs.contains(index)){
            indexs.add(index);
        }
    }

    public String getFileUrl() {
        return fileUrl;
    }

    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl;
    }

    public List<Index> getIndexs() {
        return indexs;
    }

    public void setIndexs(List<Index> indexs) {
        this.indexs = indexs;
    }
    
}
