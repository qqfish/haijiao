/**
 *
 * @author Jerry Zou
 */

package com.haijiao.Domain.bean;

import java.sql.Date;

public class BaseBean {
    private Integer id;
    private boolean delete;
    private Date createTime;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isDelete() {
        return delete;
    }

    public void setDelete(boolean delete) {
        this.delete = delete;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
