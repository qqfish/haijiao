/**
 *
 * @author Jerry Zou
 */
package com.haijiao.SupportService.service.impl;

import com.haijiao.Domain.bean.ResetInfo;
import com.haijiao.Domain.bean.Student;
import com.haijiao.Domain.bean.Teacher;
import com.haijiao.Domain.bean.User;
import com.haijiao.Domain.file.PublicFile;
import com.haijiao.Domain.file.UserFile;
import com.haijiao.Domain.file.UserFileGroup;
import com.haijiao.SupportService.MD5Util;
import com.haijiao.SupportService.dao.ICommentDAO;
import com.haijiao.SupportService.dao.IPublicFileDAO;
import com.haijiao.SupportService.dao.IResetInfoDAO;
import com.haijiao.SupportService.dao.IStudentDAO;
import com.haijiao.SupportService.dao.ITeacherDAO;
import com.haijiao.SupportService.dao.IUserDAO;
import com.haijiao.SupportService.dao.IUserFileDAO;
import com.haijiao.SupportService.dao.IUserFileGroupDAO;
import com.haijiao.SupportService.service.IUserService;
import com.haijiao.global.config;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserServiceImpl implements IUserService {

    @Resource
    IUserDAO userDAO;
    @Resource
    ITeacherDAO teacherDAO;
    @Resource
    IStudentDAO studentDAO;
    @Resource
    ICommentDAO commentDAO;
    @Resource
    IResetInfoDAO resetInfoDAO;
    @Resource
    IUserFileDAO userFileDAO;
    @Resource
    IUserFileGroupDAO userFileGroupDAO;
    @Resource
    IPublicFileDAO publicFileDAO;

    public void setUserDAO(IUserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public void setTeacherDAO(ITeacherDAO teacherDAO) {
        this.teacherDAO = teacherDAO;
    }

    public void setStudentDAO(IStudentDAO studentDAO) {
        this.studentDAO = studentDAO;
    }

    public void setCommentDAO(ICommentDAO commentDAO) {
        this.commentDAO = commentDAO;
    }

    public void setResetInfoDAO(IResetInfoDAO resetInfoDAO) {
        this.resetInfoDAO = resetInfoDAO;
    }

    public void setUserFileDAO(IUserFileDAO userFileDAO) {
        this.userFileDAO = userFileDAO;
    }

    public void setUserFileGroupDAO(IUserFileGroupDAO userFileGroupDAO) {
        this.userFileGroupDAO = userFileGroupDAO;
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public boolean confirmExist(String email) {
        return userDAO.confirmExist(email);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public User getUserByEmail(String email) {
        User u = userDAO.getUserByEmail(email);
        if (u == null) {
            return null;
        }
        String type = u.getUserType();
        if (type.equals("teacher")) {
            return teacherDAO.getTeacherByEmail(email);
        } else if (type.equals("student")) {
            return studentDAO.getStudentByEmail(email);
        } else {
            return null;
        }
    }

    @Override
    public boolean setStatus(String email, int status) {
        User u = userDAO.getUserByEmail(email);
        u.setStatus(status);
        userDAO.update(u);
        return true;
    }

    @Override
    public boolean setActiveDate(String email) {
        User u = userDAO.getUserByEmail(email);
        java.util.Date datetime = new java.util.Date();
        Date time = new Date(datetime.getTime());
        u.setLastActiveDate(time);
        if (u.getLoginNum() == null) {
            u.setLoginNum(1);
        } else {
            u.setLoginNum(u.getLoginNum() + 1);
        }
        userDAO.update(u);
        return true;
    }

    @Override
    public boolean changePassword(String email, String password) {
        User u = userDAO.getUserByEmail(email);
        password = MD5Util.MD5(password);
        u.setPassword(password);
        userDAO.update(u);
        return true;
    }

    @Override
    public boolean changePasswordById(String id, String password) {
        User u = userDAO.findById(Integer.parseInt(id));
        password = MD5Util.MD5(password);
        u.setPassword(password);
        userDAO.update(u);
        return true;
    }

    @Override
    public boolean changeIntro(String email, String intro) {
        Teacher t = teacherDAO.getTeacherByEmail(email);
        t.setIntro(intro);
        teacherDAO.update(t);
        return true;
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public String confirmLogin(String email, String password) {
        password = MD5Util.MD5(password);
        return userDAO.confirmLogin(email, password);
    }

    @Override
    public boolean register(String account, String password, String userType) {
        if (userType.equals("student")) {
            Student s = new Student();
            s.setEmail(account);
            password = MD5Util.MD5(password);
            s.setPassword(password);
            s.setUserType(userType);
            java.util.Date datetime = new java.util.Date();
            Date time = new Date(datetime.getTime());
            s.setCreateTime(time);
            s.setLastActiveDate(time);
            s.setPicUrl("images/figure-default.png"); //temp
            s.setLoginNum(0);
            s.setScoreNum(0);
            s.setScore(0);
            UserFileGroup fg = new UserFileGroup();
            fg.setGroupName("默认分组");
            s.addPersistFileGroup(fg);
            userFileGroupDAO.makePersistent(fg);
            return studentDAO.makePersistent(s);
        } else if (userType.equals("teacher")) {
            Teacher t = new Teacher();
            t.setEmail(account);
            password = MD5Util.MD5(password);
            t.setPassword(password);
            t.setUserType(userType);
            java.util.Date datetime = new java.util.Date();
            Date time = new Date(datetime.getTime());
            t.setCreateTime(time);
            t.setLastActiveDate(time);
            t.setAudition(true);
            t.setClassNum(0);
            t.setObNum(0);
            t.setLoginNum(0);
            t.setPicUrl("images/page2-img1.jpg"); //temp
            t.setIntro("<p><br /></p><div style='text-align:center;'><strong><span style='background-color:#FFE500;'><span style='font-size:24px;'>郑重申明：欢迎使用在线试讲功能</span></span></strong> </div><ul><li><strong>自我描述</strong> </li></ul><strong>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp; 这个老师很懒，并没有留下自我描述。</strong><br /><ul><li><strong>获奖经历</strong> </li></ul><strong>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp; 这个老师很懒，并没有留下获奖经历。</strong><br /><ul><li><strong>家教经历</strong> </li></ul><strong>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp; 这个老师很懒，并没有留下家教经历。</strong> <p><br /></p>");
            UserFileGroup fg = new UserFileGroup();
            fg.setGroupName("默认分组");
            t.addPersistFileGroup(fg);
            userFileGroupDAO.makePersistent(fg);
            return teacherDAO.makePersistent(t);
        } else {
            return false;
        }
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public List<Teacher> searchTeacher(List<String> strList, List<String> strList2) {
        return teacherDAO.searchTeacher(strList, strList2);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public List<Teacher> searchTeacherPage(List<String> strList, String lesson, String way, String net, String sex, String role, String school, String province, String city, String district, String status, int first, int pagesize, String extOrder, int desc) {
        return teacherDAO.searchTeacherPage(strList, lesson, way, net, sex, role, school, province, city, district, status, first, pagesize, extOrder, desc);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public int getTeacherNum(List<String> strList, String lesson, String way, String net, String sex, String role, String school, String province, String city, String district, String status, String extOrder, int desc) {
        return teacherDAO.getTeacherNum(strList, lesson, way, net, sex, role, school, province, city, district, status, extOrder, desc);
    }

    @Override
    public boolean createGroup(String email, String name) {
        UserFileGroup fg = userFileGroupDAO.getGroupByName(email, name);
        if (fg == null) {
            fg = new UserFileGroup();
            fg.setGroupName(name);
            User u = userDAO.getUserByEmail(email);
            u.getFileGroups().add(fg);
            userFileGroupDAO.makePersistent(fg);
            userDAO.update(u);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean deleteGroup(String email, String name) {
        UserFileGroup fg = userFileGroupDAO.getGroupByName(email, name);
        if (fg == null) {
            return false;
        } else {
            User u = userDAO.getUserByEmail(email);
            u.getFileGroups().remove(fg);
            userDAO.update(u);
            return userFileGroupDAO.makeTransient(fg);
        }
    }

    @Override
    public boolean deleteFile(String email, String groupName, String fileName) {
        UserFileGroup fg = userFileGroupDAO.getGroupByName(email, groupName);
        if (fg == null) {
            return false;
        } else {
            UserFile ud = userFileDAO.getFile(fg.getId(), fileName);
            if(ud.getOwned()){
                File file = new File(ud.getUrl());
                if(file.exists())
                    file.delete();
            }
            fg.removeFile(ud);
            userFileGroupDAO.update(fg);
            return userFileDAO.makeTransient(ud);
        }
    }

    @Override
    public boolean moveFile(String email, String srcName, String destName, String fileName) {
        UserFileGroup src = userFileGroupDAO.getGroupByName(email, srcName);
        if (src == null) {
            return false;
        }
        UserFileGroup dest = userFileGroupDAO.getGroupByName(email, destName);
        if (dest == null) {
            return false;
        }
        UserFile uf = userFileDAO.getFile(src.getId(), fileName);
        src.removeFile(uf);
        dest.addFile(uf);
        userFileGroupDAO.update(src);
        userFileGroupDAO.update(dest);
        return true;
    }

    @Override
    public boolean uploadFile(String email, String groupName, String name, String fileuri) {
        UserFile uf = new UserFile();
        uf.setName(name);
        uf.setUrl(fileuri);
        uf.setOwned(true);
        UserFileGroup fg = userFileGroupDAO.getGroupByName(email, groupName);
        if (fg == null) {
            return false;
        }
        fg.addFile(uf);
        userFileDAO.makePersistent(uf);
        userFileGroupDAO.update(fg);
        return true;
    }

    @Override
    public String download(String email, String groupName, String fileName) {
        UserFileGroup fg = userFileGroupDAO.getGroupByName(email, groupName);
        if (fg == null) {
            return null;
        }
        UserFile uf = userFileDAO.getFile(fg.getId(), fileName);
        if (uf == null) {
            return null;
        }
        return uf.getUrl();
    }

    @Override
    public void setPicUrl(String email, String url) {
        User user = userDAO.getUserByEmail(email);
        user.setPicUrl(url);
        userDAO.update(user);
    }

    @Override
    public boolean validateCheckcode(int id, String checkcode) {
        List<ResetInfo> lr = resetInfoDAO.getResetInfoByUser(id);
        if (lr.size() == 1 && lr.get(0).getCheckcode().equals(checkcode)) {
            long between = new java.util.Date().getTime() - lr.get(0).getCreateTime().getTime() - 78360534;
            if ((between / (60 * 60 * 1000)) < 1) {
                return true;
            } else {
                resetInfoDAO.makeTransient(lr.get(0));
                return false;
            }
        } else {
            return false;
        }
    }

    @Override
    public void saveResetInfo(int id, String checkCode) {
        List<ResetInfo> lr = resetInfoDAO.getResetInfoByUser(id);
        if (!lr.isEmpty()) {
            for (int i = 0; i < lr.size(); i++) {
                resetInfoDAO.makeTransient(lr.get(i));
            }
        }
        ResetInfo r = new ResetInfo();
        r.setUserid(id);
        r.setCheckcode(checkCode);
        java.util.Date datetime = new java.util.Date();
        Date time = new Date(datetime.getTime());
        r.setCreateTime(time);
        resetInfoDAO.makePersistent(r);
    }

    @Override
    public boolean addFileFromPublic(String email, String groupName, int publicFileId){
        PublicFile pf = publicFileDAO.findById(publicFileId);
        UserFile uf = new UserFile();
        uf.setName(pf.getName());
        uf.setUrl(pf.getUrl());
        uf.setOwned(false);
        UserFileGroup fg = userFileGroupDAO.getGroupByName(email, groupName);
        if (fg == null) {
            return false;
        }
        fg.addFile(uf);
        userFileDAO.makePersistent(uf);
        userFileGroupDAO.update(fg);
        return true;
    }
    
    @Override
    public List<UserFileGroup> getUserFileGroup(String email) {
        return userFileGroupDAO.getUserFileGroup(email);
    }

    @Override
    public List<UserFile> getUserFile(String email, String groupName) {
        return userFileDAO.getUserFile(email, groupName);
    }

    @Override
    public int getUserFileNum(String email, String groupName){
        return userFileDAO.getUserFileNum(email, groupName);
    }
    
    @Override
    public boolean publishFile(int userFileId, String name, String author, String publisher, String type) {
        try {
            UserFile uf = userFileDAO.findById(userFileId);
            FileInputStream in = new FileInputStream(uf.getUrl());
            String url =  config.fileHome + File.separator + userFileId + ".pdf";
            System.out.println(url);
            File dir = new File(config.fileHome);
            if(!dir.exists()){
                dir.mkdirs();
            }
            File file = new File(url);
            if (!file.exists()) {
                file.createNewFile();
            }
            FileOutputStream out = new FileOutputStream(file);
            int c;
            byte buffer[] = new byte[1024];
            while ((c = in.read(buffer)) != -1) {
                for (int i = 0; i < c; i++) {
                    out.write(buffer[i]);
                }
            }
            in.close();
            out.close();
            PublicFile pf = new PublicFile();
            pf.setName(uf.getName());
            pf.setAuthor(author);
            pf.setFullname(name);
            pf.setPublisher(publisher);
            pf.setType(type);
            pf.setUrl(url);
            pf.setPass(false);
            publicFileDAO.makePersistent(pf);
            return true;
        } catch (FileNotFoundException ex) {
            Logger.getLogger(UserServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } catch (IOException ex) {
            Logger.getLogger(UserServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    @Override
    public List<PublicFile> getPublicFilelist(String name, int first, int pageSize) {
        return publicFileDAO.getPublicFiles(name, first, pageSize);
    }

    @Override
    public int getPubliFileNum(String name) {
        return publicFileDAO.getPublicFileNum(name);
    }
    
}
