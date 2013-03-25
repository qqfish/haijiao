/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.haijiao.Domain.bean;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 *
 * @author hp
 */
@Entity
@Table
public class Lesson extends BaseBean{
    private String name;

    public Lesson() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
}
