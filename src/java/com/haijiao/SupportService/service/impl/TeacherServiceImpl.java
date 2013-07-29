/**
 *
 * @author Jerry Zou
 */
package com.haijiao.SupportService.service.impl;

//import com.haijiao.Domain.bean.Clazz;
//import com.haijiao.Domain.bean.FreeTime;
import com.haijiao.Domain.Manager.MoneyRequest;
import com.haijiao.Domain.bean.Lesson;
import com.haijiao.Domain.bean.Teacher;
//import com.haijiao.SupportService.dao.IClazzDAO;
import com.haijiao.SupportService.dao.ILessonDAO;
import com.haijiao.SupportService.dao.IMoneyRequestDAO;
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
//    @Resource
//    IClazzDAO classDAO;
    @Resource
    ILessonDAO lessonDAO;
    @Resource
    IMoneyRequestDAO moneyRequestDAO;

    public void setTeacherDAO(ITeacherDAO teacherDAO) {
        this.teacherDAO = teacherDAO;
    }

//    public void setClassDAO(IClazzDAO classDAO) {
//        this.classDAO = classDAO;
//    }
    public void setLessonDAO(ILessonDAO lessonDAO) {
        this.lessonDAO = lessonDAO;
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public Teacher getTeacherByEmail(String email) {
        return teacherDAO.getTeacherByEmail(email);
    }

    @Override
    public boolean changeInfo(String email, String name, String sex, Date birthday, String school, String major,
            String studyStatus, String tel, String province, String city, String district, String net) {
        Teacher t = teacherDAO.getTeacherByEmail(email);
        if (birthday != null) {
            t.setBirthday(birthday);
        }
        if (school != null) {
            t.setSchool(school);
        }
        if (name != null) {
            t.setName(name);
        }
        if (sex != null) {
            t.setSex(sex);
        }
        if (tel != null) {
            t.setTel(tel);
        }
        if (province != null) {
            t.setProvince(province);
        }
        if (city != null) {
            t.setCity(city);
        }
        if (district != null) {
            t.setDistrict(district);
        }
        if (net != null) {
            t.setNet(net);
        }
        if (major != null) {
            t.setMajor(major);
        }
        if (studyStatus != null) {
            t.setStudyStatus(studyStatus);
        }
        teacherDAO.update(t);
        return true;
    }

    @Override
    public boolean changeMoreInfo(String email, String underlineArea, String intro, String experience, String cert, Boolean sprtSUnderline, Boolean sprtTUnderline, Boolean sprtOnline) {
        Teacher t = teacherDAO.getTeacherByEmail(email);
        if (underlineArea != null) {
            t.setUnderlineArea(underlineArea);
        }
        if (intro != null) {
            t.setIntro(intro);
        }
        if (cert != null) {
            t.setCert(cert);
        }
        if (sprtSUnderline != null) {
            t.setSprtSUnderline(sprtSUnderline);
        }
        if (sprtTUnderline != null) {
            t.setSprtTUnderline(sprtTUnderline);
        }
        if (experience != null) {
            t.setExperience(experience);
        }
        if (sprtOnline != null) {
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
    public boolean increseObNum(String email) {
        Teacher t = teacherDAO.getTeacherByEmail(email);
        t.setObNum(t.getObNum() + 1);
        teacherDAO.update(t);
        return true;
    }

    @Override
    public boolean setRoomOccupied(String email, String stuemail) {
        Teacher t = teacherDAO.getTeacherByEmail(email);
        t.setStudentin(stuemail);
        teacherDAO.update(t);
        return false;
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public String getRoomOccupied(String email) {
        Teacher t = teacherDAO.getTeacherByEmail(email);
        return t.getStudentin();
    }

//    @Override
//    @Transactional(propagation=Propagation.SUPPORTS)
//    public List<FreeTime> getSchedule(String email) {
//        Teacher t = teacherDAO.getTeacherByEmail(email);
//        return t.getSchedule();
//    }
//
//    @Override
//    @Transactional(propagation=Propagation.SUPPORTS)
//    public List<Clazz> getClasses(String email) {
//        return classDAO.getTeacherClazz(email);
//    }
//
//    @Override
//    @Transactional(propagation=Propagation.SUPPORTS)
//    public List<Clazz> getTodayClasses(String email) {
//        return classDAO.getTeacherTodayClazz(email);
//    }
    @Override
    public boolean addLesson(String email, String lessonName, int price) {
        Teacher t = teacherDAO.getTeacherByEmail(email);
        Lesson l = lessonDAO.getLessonByName(email, lessonName);
        if (l != null) {
            l.setDelete(false);
            l.setPrice(price);
            lessonDAO.update(l);
        } else {
            l = new Lesson();
            l.setName(lessonName);
            l.setPrice(price);
            lessonDAO.makePersistent(l);
            List ll = t.getLessons();
            ll.add(l);
            t.setLessons(ll);

        }
        if (t.getWagePerhour() > price || t.getWagePerhour() <= 0) {
            t.setWagePerhour(price);
        }
        teacherDAO.update(t);
        return true;
    }

    @Override
    public boolean deleteLesson(String email, String lessonName) {
        Teacher t = teacherDAO.getTeacherByEmail(email);
        Lesson l = lessonDAO.getLessonByName(email, lessonName);
        if (l != null) {
            l.setDelete(true);
            lessonDAO.update(l);
            List ll = t.getLessons();
            ll.remove(l);
            if (t.getWagePerhour() == l.getPrice()) {
                if (t.getLessons().isEmpty()) {
                    t.setWagePerhour(0);
                } else {
                    int min = Integer.MAX_VALUE;
                    for (int i = 0; i < t.getLessons().size(); i++) {
                        if (t.getLessons().get(i).getPrice() < min) {
                            min = t.getLessons().get(i).getPrice();
                        }
                    }
                    t.setWagePerhour(min);
                }
            }
            teacherDAO.update(t);
            lessonDAO.makeTransient(l);
        }
        return true;
    }

    @Override
    public boolean changeReserve(String email) {
        Teacher t = teacherDAO.getTeacherByEmail(email);
        boolean reserve = t.getReserve();
        t.setReserve(!reserve);
        teacherDAO.update(t);
        return true;
    }

    @Override
    public boolean requestMoney(String email, String bankcard, String bankname) {
        Teacher t = teacherDAO.getTeacherByEmail(email);
        MoneyRequest mr = new MoneyRequest();
        mr.setTeacher(t);
        mr.setBankcard(bankcard);
        mr.setBankname(bankname);
        mr.setMoney(t.getCoin());
        moneyRequestDAO.makePersistent(mr);
        return true;
    }

    @Override
    public boolean requestMoneyAndUpdate(String email, String bankcard, String bankname) {
        Teacher t = teacherDAO.getTeacherByEmail(email);
        MoneyRequest mr = new MoneyRequest();
        mr.setTeacher(t);
        mr.setBankcard(bankcard);
        mr.setBankname(bankname);
        mr.setMoney(t.getCoin());
        t.setBankcard(bankcard);
        t.setBankname(bankname);
        moneyRequestDAO.makePersistent(mr);
        teacherDAO.update(t);
        return true;
    }
}
