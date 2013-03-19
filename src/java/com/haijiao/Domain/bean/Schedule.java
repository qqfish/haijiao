/**
 *
 * @author fish
 */

package com.haijiao.Domain.bean;
import java.util.ArrayList;
import java.util.List;

public class Schedule extends BaseBean {
    private List<Timeslice> slices;  //两周内的时间片
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
