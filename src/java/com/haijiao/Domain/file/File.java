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
public class File {
    protected String uuid;
    protected String fileName;
    protected int pageNum;
    protected String fileUrl;
    protected String postfix;
    protected List<Index> indexs;

    public File() {
        indexs = new ArrayList();
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }
    
    public void addIndex(Index index){
        if(!indexs.contains(index)){
            indexs.add(index);
        }
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
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

    public String getPostfix() {
        return postfix;
    }

    public void setPostfix(String postfix) {
        this.postfix = postfix;
    }
    
}
