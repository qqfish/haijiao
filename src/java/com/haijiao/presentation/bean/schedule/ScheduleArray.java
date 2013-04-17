/**
 *
 * @author Jerry Zou
 */
package com.haijiao.presentation.bean.schedule;

import com.haijiao.SupportService.service.IClassService;
import com.haijiao.global.scheduleLocation;
import java.util.ArrayList;
import java.util.List;

public class ScheduleArray {

    List<List<Integer>> array;

    public ScheduleArray() {
    }

    public List<List<Integer>> getArray() {
        return array;
    }

    public void setArray(List<List<Integer>> array) {
        this.array = array;
    }
    
    public List<scheduleLocation> toList(){
        List<scheduleLocation> result = new ArrayList();
        for(int i = 0; i < array.size(); i++){
            for(int j = 0; j < array.get(i).size(); j++){
                if(array.get(i).get(j) > 0){
                    scheduleLocation sc = new scheduleLocation();
                    sc.setDay(i);
                    sc.setIndex(j);
                    result.add(sc);
                }
            }
        }
        return result;
    }
}
