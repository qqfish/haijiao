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
import javax.persistence.JoinColumns;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity    
@Table(name="bird")     
@PrimaryKeyJoinColumn(name="BirdId")
public class Schedule extends BaseBean {
    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name="scheid")
    private List<Timeslice> slices;  //两周内的时间片
    
    @OneToOne(mappedBy = "schedule")
    private Teacher teacher;

    public Schedule() {
        this.slices = new ArrayList<Timeslice>();
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public List<Timeslice> getSlices() {
        return slices;
    }

    public void setSlices(List<Timeslice> slices) {
        this.slices = slices;
    }
}
