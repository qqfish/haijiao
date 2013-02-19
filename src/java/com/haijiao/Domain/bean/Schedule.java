/**
 *
 * @author fish
 */

package com.haijiao.Domain.bean;
import java.util.List;

public class Schedule {
    private List<Timeslice> slices;

    public List<Timeslice> getSlices() {
        return slices;
    }

    public void setSlices(List<Timeslice> slices) {
        this.slices = slices;
    }
}
