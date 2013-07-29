/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.haijiao.SupportService.dao;

import com.haijiao.Domain.file.PublicFile;
import java.util.List;

/**
 *
 * @author hp
 */
public interface IPublicFileDAO extends GenericDAO<PublicFile,Integer>{
    public PublicFile getPublicFileById(int id);
    public List<PublicFile> getPublicFiles(String name, int first, int pageSize);
    public int getPublicFileNum(String name);
}
