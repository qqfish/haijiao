/**
 *
 * @author Jerry Zou
 */
package com.haijiao.SupportService.service.impl;

//import com.haijiao.Domain.bean.Clazz;
//import com.haijiao.Domain.bean.FreeTime;
import com.haijiao.Domain.bean.Demand;
import com.haijiao.Domain.bean.Student;
import com.haijiao.SupportService.dao.IDemandDAO;
//import com.haijiao.SupportService.dao.IClazzDAO;
import com.haijiao.SupportService.service.IStudentService;
import com.haijiao.SupportService.dao.IStudentDAO;
import java.util.Date;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class StudentServiceImpl implements IStudentService {

    @Resource
    IStudentDAO studentDAO;
    @Resource
    IDemandDAO demandDAO;
//    @Resource
//    IClazzDAO clazzDAO;

    public void setStudentDAO(IStudentDAO studentDAO) {
        this.studentDAO = studentDAO;
    }

    @Override
    public Student getStudentByEmail(String email) {
        return studentDAO.getStudentByEmail(email);
    }

    @Override
    public Demand getStudentDemand(String email){
        return demandDAO.getStudentDemand(email);
    }
    
    
    @Override
    public boolean publishDemand(String email, String lesson, String demand, String way, String address, int duration, int total){
        Demand d = demandDAO.getStudentDemand(email);
        d.setAddress(address);
        d.setDemand(demand);
        d.setDuration(duration);
        d.setLesson(lesson);
        d.setTotal(total);
        d.setWay(way);
        d.setPublish(true);
        d.setPublishTime(new Date());
        demandDAO.update(d);
        return true;
    }

    @Override
    public boolean changeDemand(String email, String lesson, String demand, String way, String address, int duration, int total){
        Demand d = demandDAO.getStudentDemand(email);
        d.setAddress(address);
        d.setDemand(demand);
        d.setDuration(duration);
        d.setLesson(lesson);
        d.setTotal(total);
        d.setWay(way);
        demandDAO.update(d);
        return true;
    }
    
    @Override
    public boolean cancelDemand(String email){
        Demand d = demandDAO.getStudentDemand(email);
        d.setPublish(false);
        demandDAO.update(d);
        return true;
    }
//    public IClazzDAO getClazzDAO() {
//        return clazzDAO;
//    }
//    public void setClazzDAO(IClazzDAO clazzDAO) {
//        this.clazzDAO = clazzDAO;
//    }
    @Override
    public boolean changeInfo(String email, String name, String sex, Date birthday, String grade, String school, String tel, String telType, String province, String city, String district, String net) {
        Student s = studentDAO.getStudentByEmail(email);
        if (birthday != null) {
            s.setBirthday(birthday);
        }
        if (grade != null) {
            s.setGrade(grade);
        }
        if (name != null && !name.equals("")) {
            s.setName(name);
        }
        if (school != null && !school.equals("")) {
            s.setSchool(school);
        }
        if (sex != null) {
            s.setSex(sex);
        }
        if (tel != null && !tel.equals("")) {
            s.setTel(tel);
        }
        if (telType != null) {
            s.setTelType(telType);
        }
        if (province != null) {
            s.setProvince(province);
        }
        if (city != null) {
            s.setCity(city);
        }
        if (district != null) {
            s.setDistrict(district);
        }
        if (net != null) {
            s.setNet(net);
        }
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
    public List<Student> searchStudentPage(List<String> strList, String lesson, String way, String net, String sex, String province, String city, String district, String status, int first, int pagesize, String extOrder, int desc){
        return studentDAO.searchStudentPage(strList, lesson, way, net, sex, province, city, district, status, first, pagesize, extOrder, desc);
    }
    
    @Override
    public int searchStudentNum(List<String> strList, String lesson, String way, String net, String sex, String province, String city, String district, String status, String extOrder, int desc){
        return studentDAO.searchStudentNum(strList, lesson, way, net, sex, province, city, district, status, extOrder, desc);
    }

//    @Override
//    @Transactional(propagation=Propagation.SUPPORTS)
//    public List<Clazz> getClasses(String email) {
//        return clazzDAO.getStudentClazz(email);
//    }
//
//    @Override
//    @Transactional(propagation=Propagation.SUPPORTS)
//    public List<Clazz> getTodayClasses(String email) {
//        return clazzDAO.getStudentTodayClazz(email);
//    }
}
