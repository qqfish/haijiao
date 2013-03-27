/**
 *
 * @author fish
 */

package com.haijiao.Domain.bean;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity    
@Table
public class Schedule extends BaseBean {
    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name="scheid")
    private List<Clazz> clazzes;  //两周内的课程（空余时间）
    
    @OneToOne(mappedBy = "schedule")
    private Teacher teacher;

    public Schedule() {
        this.clazzes = new ArrayList<Clazz>();
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public List<Clazz> getClazzes() {
        return clazzes;
    }

    public void setClazzes(List<Clazz> clazzes) {
        this.clazzes = clazzes;
    }
}
