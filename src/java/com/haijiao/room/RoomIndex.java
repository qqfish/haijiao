/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.haijiao.room;

import com.haijiao.file.Index;

/**
 *
 * @author fish
 */
public class RoomIndex {

    private int page;
    private String indexName;

    public RoomIndex(Index index) {
        page = index.getPage();
        indexName = index.getIndexName();
    }
    
    public RoomIndex(){
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
        int hash = 7;
        hash = 17 * hash + this.page;
        hash = 17 * hash + (this.indexName != null ? this.indexName.hashCode() : 0);
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
        final RoomIndex other = (RoomIndex) obj;
        if (this.page != other.page) {
            return false;
        }
        return true;
    }
    
}
