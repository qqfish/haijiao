/**
 *
 * @author fish
 */

package com.haijiao.Domain.bean;
import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity    
@Table
public class Schedule extends BaseBean {
    @OneToMany(fetch = FetchType.EAGER)
    @Fetch(value = FetchMode.SUBSELECT)
    @JoinColumn(name="scheid")
    private List<FreeTime> freeTimes;  //教师的空闲时间
    
    @OneToOne(mappedBy = "schedule")
    private Teacher teacher;

    public Schedule() {
        this.freeTimes = new ArrayList<FreeTime>();
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public List<FreeTime> getFreeTimes() {
        return freeTimes;
    }

    public void setFreeTimes(List<FreeTime> freeTimes) {
        this.freeTimes = freeTimes;
    }
}
