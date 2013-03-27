/**
 *
 * @author Jerry Zou
 */

package com.haijiao.Domain.service.impl;

import com.haijiao.Domain.bean.Clazz;
import com.haijiao.Domain.service.GenericService;
import com.haijiao.Domain.service.IClazzService;

public class ClazzServiceImpl extends GenericService<Clazz,Integer> implements IClazzService{

    @Override
    public boolean bookTeacher(Clazz clazz) {
        return makePersistent(clazz);
    }

    @Override
    public boolean dealWithReservation(int id, boolean accept) {
        Clazz c = findById(id);
        c.setAccept(accept);
        update(c);
        return true;
    }

}
