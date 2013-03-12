/**
 *
 * @author fish
 */

package com.haijiao.Domain.bean;
import java.util.ArrayList;
import java.util.List;

public class Schedule {
    private List<Timeslice> slices = new ArrayList<Timeslice>();
    private Teacher teacher;

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
