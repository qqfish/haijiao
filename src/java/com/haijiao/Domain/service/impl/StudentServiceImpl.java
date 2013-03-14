/**
 *
 * @author Jerry Zou
 */

package com.haijiao.Domain.service.impl;
import com.haijiao.Domain.bean.Student;
import com.haijiao.Domain.bean.Timeslice;
import com.haijiao.Domain.service.IStudentService;
import java.util.List;

public class StudentServiceImpl extends UserServiceImpl implements IStudentService{ 

    @Override
    public Student getStudentByAccount(String account) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean bookTeacher(String studentAccount, String teacherAccount, List<Timeslice> timeslices) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
