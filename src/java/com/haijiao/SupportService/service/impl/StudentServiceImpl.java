/**
 *
 * @author Jerry Zou
 */

package com.haijiao.SupportService.service.impl;

import com.haijiao.Domain.bean.Clazz;
import com.haijiao.Domain.bean.FreeTime;
import com.haijiao.Domain.bean.Student;
import com.haijiao.SupportService.service.IStudentService;
import com.haijiao.SupportService.dao.IStudentDAO;
import java.sql.Date;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class StudentServiceImpl implements IStudentService{
    @Resource
    IStudentDAO studentDAO;

    public void setStudentDAO(IStudentDAO studentDAO) {
        this.studentDAO = studentDAO;
    }

    @Override
    public Student getStudentByEmail(String email) {
        return studentDAO.getStudentByEmail(email);
    }
    
    @Override
    public boolean changeInfo(String email, String name, String sex, Date birthday, String grade, String school, String tel, String telType) {
        Student s = studentDAO.getStudentByEmail(email);
        if(birthday != null)
            s.setBirthday(birthday);
        if(grade != null)
            s.setGrade(grade);
        if(name != null)
            s.setName(name);
        if(school != null)
            s.setSchool(school);
        if(sex != null)
            s.setSex(sex);
        if(tel != null)
            s.setTel(tel);
        if(telType != null)
            s.setTelType(telType);
        studentDAO.update(s);
        return true;
    }

    @Override
    public boolean topUpMoney(String email, int numberOfCoin) {
        Student s = studentDAO.getStudentByEmail(email);
        int coin = s.getCoin();
        s.setCoin(coin + numberOfCoin);
        studentDAO.update(s);
        return true;
    }

    @Override
    public List<Clazz> getFinishedClasses(String email) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<FreeTime> getSchedule(String email) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
