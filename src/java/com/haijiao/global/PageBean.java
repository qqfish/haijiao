/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.haijiao.global;

import java.util.List;

/**
 *
 * @author hp
 */
public class PageBean {
    private List list;
    
    private int allRow;
    private int totalPage;
    private int currentPage;
    private int pageSize;
    
    private boolean firstPage;
    private boolean lastPage;
    private boolean hasPreviousPage;
    private boolean hasNextPage;

    public PageBean(List list, int allRow, int currentPage, int pageSize) {
        this.list = list;
        this.allRow = allRow;
        this.currentPage = currentPage;
        this.pageSize = pageSize;
        
        this.totalPage = allRow % pageSize == 0 ? allRow / pageSize : allRow / pageSize + 1;
        this.firstPage = (currentPage == 1);
        this.lastPage = (currentPage == this.totalPage);
        this.hasNextPage = (currentPage != 1);
        this.hasPreviousPage = (currentPage != totalPage);
    }
    
    public List getList() {
        return list;
    }

    public void setList(List list) {
        this.list = list;
    }

    public int getAllRow() {
        return allRow;
    }

    public void setAllRow(int allRow) {
        this.allRow = allRow;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public boolean isFirstPage() {
        return firstPage;
    }

    public boolean isLastPage() {
        return lastPage;
    }

    public boolean isHasPreviousPage() {
        return hasPreviousPage;
    }

    public boolean isHasNextPage() {
        return hasNextPage;
    }
    
}
