/**
 *
 * @author Jerry Zou
 */

package com.haijiao.Domain.bean;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity    
@Table(name="lesson")     
@PrimaryKeyJoinColumn(name="LessonId")
public class Lesson extends BaseBean{
    private String name;
    private String grade;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }
}
