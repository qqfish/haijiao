/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.haijiao.presentation.action;

import com.haijiao.SupportService.service.IUserService;
import com.haijiao.global.config;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.imageio.ImageReadParam;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.InterceptorRef;
import org.apache.struts2.convention.annotation.InterceptorRefs;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.stereotype.Controller;

/**
 *
 * @author hp
 */

@Controller
@ParentPackage("haijiao")
@Namespace("/")
@InterceptorRefs({  
    @InterceptorRef("LoginCheckerStack"),
    @InterceptorRef(value="fileUpload",params={
        "allowedTypes","image/bmp,image/png,image/gif,image/jpeg",
        "maximumSize","1025956"
    })
})  
@Action("uploadPic")
@Results({
    @Result(name="input",type="redirect",location="toChangeInfo.action"),
    @Result(name="error",type="redirect",location="index.action"),
    @Result(name="success",type="redirect",location="toChangeInfo.action")
})
public class UploadPicAction extends SessionAction{
    private static final int BUFFER_SIZE = 16 * 1024;
    private File upload;
    private String uploadFileName;
    private String uploadContentType;
    @Resource
    private IUserService userService;
    private Integer x;
    private Integer y;
    private Double rate;
    private Integer w;
    private Integer h;
    
    @Override
    public String execute(){
        try{
            FileInputStream in = new FileInputStream(upload);
            String email = (String) this.getSessionValue("email");
            if(email == null){
                this.sessionPutIn("nextPageMessage", "请先登陆");
                return "error";
            }
            String path = config.imageFolder + "/" + email;
            File folder = new File(ServletActionContext.getServletContext().getRealPath("/") + path);
            if(!folder.exists()){
                folder.mkdirs();
            } else if(!folder.isDirectory()){
                folder.delete();
                folder.mkdirs();
            }
            for(int i = 0; i < folder.listFiles().length; i++){
                folder.listFiles()[i].delete();
            }
            path = path + "/head.jpg";
            File imageFile = new File(ServletActionContext.getServletContext().getRealPath("/") + path);
            if(!imageFile.exists()){
                imageFile.createNewFile();
            }
            FileOutputStream out = new FileOutputStream(imageFile);
            byte[] b = new byte[BUFFER_SIZE];
            int len = 0;
            while((len=in.read(b))>0){  
                out.write(b,0,len);
            }
            out.close();
            cut(ServletActionContext.getServletContext().getRealPath("/") +path,"jpg");
            userService.setPicUrl(email, path);
            this.sessionPutIn("nextPageMessage", "上传完成");
            return SUCCESS;
        }
        catch(FileNotFoundException ex){
            Logger.getLogger(UploadPicAction.class.getName()).log(Level.SEVERE, null, ex);
            this.sessionPutIn("nextPageMessage", "没有找到对应文件");
            return INPUT;
        } catch (IOException ex) {
            Logger.getLogger(UploadPicAction.class.getName()).log(Level.SEVERE, null, ex);
            this.sessionPutIn("nextPageMessage", "上传出错");
            return INPUT;
        }
    }
    
    /**    
    * 对图片裁剪，并把裁剪完对新图片保存 。 
    */    
    public void cut(String srcpath, String lastdir)throws IOException {    
           
       FileInputStream is =   null ;   
       ImageInputStream iis = null ;   
       
        try {      
            // 读取图片文件    
           is =new FileInputStream(srcpath);    
              
           /*   
            * 返回包含所有当前已注册 ImageReader 的 Iterator，这些 ImageReader   
            * 声称能够解码指定格式。 参数：formatName - 包含非正式格式名称 . 
            *（例如 "jpeg" 或 "tiff"）等 。   
        */    
           Iterator < ImageReader > it=ImageIO.getImageReadersByFormatName(lastdir);     
           ImageReader reader = it.next();    
            // 获取图片流     
           iis = ImageIO.createImageInputStream(is);   
                 
           /*    
            * <p>iis:读取源.true:只向前搜索 </p>.将它标记为 ‘只向前搜索’。 
            * 此设置意味着包含在输入源中的图像将只按顺序读取，可能允许 reader 
            * 避免缓存包含与以前已经读取的图像关联的数据的那些输入部分。 
        */    
           reader.setInput(iis, true ) ;   
              
           /*    
            * <p>描述如何对流进行解码的类<p>.用于指定如何在输入时从 Java Image I/O   
            * 框架的上下文中的流转换一幅图像或一组图像。用于特定图像格式的插件 
            * 将从其 ImageReader 实现的 getDefaultReadParam 方法中返回   
            * ImageReadParam 的实例。    
       */    
           ImageReadParam param = reader.getDefaultReadParam();    
               
           /*   
            * 图片裁剪区域。Rectangle 指定了坐标空间中的一个区域，通过 Rectangle 对象 
            * 的左上顶点的坐标（x，y）、宽度和高度可以定义这个区域。   
       */ 
           Rectangle rect =   new Rectangle( (int)(x/rate), (int)(y/rate), (int)(w/rate), (int)(h/rate));
              
                
            // 提供一个 BufferedImage，将其用作解码像素数据的目标。     
           param.setSourceRegion(rect);   

           /*   
            * 使用所提供的 ImageReadParam 读取通过索引 imageIndex 指定的对象，并将 
            * 它作为一个完整的 BufferedImage 返回。 
             */    
           BufferedImage bi=reader.read(0,param);                   
        
            // 保存新图片     
           ImageIO.write(bi,lastdir,new File(srcpath));        
       } finally {
            if (is != null ) {
              is.close() ;
            }
            if (iis != null ) { 
              iis.close();
            }
       }     
   }   

    public File getUpload() {
        return upload;
    }

    public void setUpload(File upload) {
        this.upload = upload;
    }

    public String getUploadFileName() {
        return uploadFileName;
    }

    public void setUploadFileName(String uploadFileName) {
        this.uploadFileName = uploadFileName;
    }

    public String getUploadContentType() {
        return uploadContentType;
    }

    public void setUploadContentType(String uploadContentType) {
        this.uploadContentType = uploadContentType;
    }

    public IUserService getUserService() {
        return userService;
    }

    public void setUserService(IUserService userService) {
        this.userService = userService;
    }

    public Integer getX() {
        return x;
    }

    public void setX(Integer x) {
        this.x = x;
    }

    public Integer getY() {
        return y;
    }

    public void setY(Integer y) {
        this.y = y;
    }

    public Double getRate() {
        return rate;
    }

    public void setRate(Double rate) {
        this.rate = rate;
    }

    public Integer getW() {
        return w;
    }

    public void setW(Integer w) {
        this.w = w;
    }

    public Integer getH() {
        return h;
    }

    public void setH(Integer h) {
        this.h = h;
    }
}
