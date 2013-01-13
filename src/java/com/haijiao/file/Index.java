/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.haijiao.file;

/**
 *
 * @author fish
 */
public class Index {
    private int page;
    private String indexName;

    public Index() {
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public String getIndexName() {
        return indexName;
    }

    public void setIndexName(String indexName) {
        this.indexName = indexName;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 89 * hash + this.page;
        hash = 89 * hash + (this.indexName != null ? this.indexName.hashCode() : 0);
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
        final Index other = (Index) obj;
        if (this.page != other.page) {
            return false;
        }
        return true;
    }
    
}
