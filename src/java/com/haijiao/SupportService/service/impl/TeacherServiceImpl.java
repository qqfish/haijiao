/**
 *
 * @author Jerry Zou
 */

package com.haijiao.SupportService.service.impl;

import com.haijiao.Domain.bean.Clazz;
import com.haijiao.Domain.bean.FreeTime;
import com.haijiao.Domain.bean.Lesson;
import com.haijiao.Domain.bean.Teacher;
import com.haijiao.SupportService.dao.IClazzDAO;
import com.haijiao.SupportService.dao.ILessonDAO;
import com.haijiao.SupportService.dao.ITeacherDAO;
import com.haijiao.SupportService.service.ITeacherService;
import java.sql.Date;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class TeacherServiceImpl implements ITeacherService {
    @Resource
    ITeacherDAO teacherDAO;
    
    @Resource
    IClazzDAO classDAO;
    
    @Resource
    ILessonDAO lessonDAO;

    public void setTeacherDAO(ITeacherDAO teacherDAO) {
        this.teacherDAO = teacherDAO;
    }

    public void setClassDAO(IClazzDAO classDAO) {
        this.classDAO = classDAO;
    }

    public void setLessonDAO(ILessonDAO lessonDAO) {
        this.lessonDAO = lessonDAO;
    }

    @Override
    @Transactional(propagation=Propagation.SUPPORTS)
    public Teacher getTeacherByEmail(String email) {
        return teacherDAO.getTeacherByEmail(email);
    }
    
    @Override
    public boolean changeInfo(String email, String name, String sex, Date birthday, String school, String major,
                    String studyStatus, String tel, String province, String city, String district,String net) {
        Teacher t = teacherDAO.getTeacherByEmail(email);
        if (birthday!=null) {
            t.setBirthday(birthday);
        } if (school!=null) {
            t.setSchool(school);
        } if (name!=null) {
            t.setName(name);
        } if (sex!=null) {
            t.setSex(sex);
        } if (tel!=null) {
            t.setTel(tel);
        } if (province!=null) {
            t.setProvince(province);
        } if (city!=null) {
            t.setCity(city);
        } if (district!=null) {
            t.setDistrict(district);
        } if (net!=null) {
            t.setNet(net);
        } if (major!=null) {
            t.setMajor(major);
        } if (studyStatus!=null) {
            t.setStudyStatus(studyStatus);
        }
        teacherDAO.update(t);
        return true;
    }
    
    @Override
    public boolean changeMoreInfo(String email, String underlineArea, String experience, Boolean sprtUnderline, Boolean sprtOnline){
        Teacher t = teacherDAO.getTeacherByEmail(email);
        if (underlineArea!=null) {
            t.setUnderlineArea(underlineArea);
        } if (sprtUnderline!=null) {
            t.setSprtUnderline(sprtUnderline);
        } if (experience!=null) {
            t.setExperience(experience);
        } if (sprtOnline!=null) {
            t.setSprtOnline(sprtOnline);
        }
        teacherDAO.update(t);
        return true;
    }

    @Override
    public boolean takeMoney(String email, int numberOfCoin) {
        Teacher t = teacherDAO.getTeacherByEmail(email);
        int coin = t.getCoin();
        t.setCoin(coin - numberOfCoin);
        teacherDAO.update(t);
        return true;
    }

    @Override
    public boolean changeAudition(String email) {
        Teacher t = teacherDAO.getTeacherByEmail(email);
        boolean audition = t.isAudition();
        t.setAudition(!audition);
        teacherDAO.update(t);
        return true;
    }

    @Override
    public boolean increseObNum(String email){
        Teacher t = teacherDAO.getTeacherByEmail(email);
        t.setObNum(t.getObNum() +1);
        teacherDAO.update(t);
        return true;
    }
    
    @Override
    public boolean setRoomOccupied(String email, String stuemail){
        Teacher t = teacherDAO.getTeacherByEmail(email);
        t.setStudentin(stuemail);
        teacherDAO.update(t);
        return false;
    }
    
    @Override
    @Transactional(propagation=Propagation.SUPPORTS)
    public String getRoomOccupied(String email){
        Teacher t = teacherDAO.getTeacherByEmail(email);
        return t.getStudentin();
    }
    
    @Override
    @Transactional(propagation=Propagation.SUPPORTS)
    public List<FreeTime> getSchedule(String email) {
        Teacher t = teacherDAO.getTeacherByEmail(email);
        return t.getSchedule();
    }

    @Override
    @Transactional(propagation=Propagation.SUPPORTS)
    public List<Clazz> getClasses(String email) {
        return classDAO.getTeacherClazz(email);
    }

    @Override
    @Transactional(propagation=Propagation.SUPPORTS)
    public List<Clazz> getTodayClasses(String email) {
        return classDAO.getTeacherTodayClazz(email);
    }

    @Override
    public boolean addLesson(String email, String lessonName) {
        Teacher t = teacherDAO.getTeacherByEmail(email);
        Lesson l = lessonDAO.getLessonByName(email,lessonName);
        if(l == null || l.isDelete()){
            if(l != null){
                l.setDelete(false);
                lessonDAO.update(l);
            } else {
                l = new Lesson();
                l.setName(lessonName);
                lessonDAO.makePersistent(l);
            }
            List ll = t.getLessons();
            ll.add(l);
            t.setLessons(ll);
            teacherDAO.update(t);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean deleteLesson(String email, String lessonName) {
        Teacher t = teacherDAO.getTeacherByEmail(email);
        Lesson l = lessonDAO.getLessonByName(email,lessonName);
        if(l != null){
            l.setDelete(true);
            lessonDAO.update(l);
        }
        return true;
    }

}
