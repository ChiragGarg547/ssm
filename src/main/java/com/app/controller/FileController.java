package com.app.controller;

import com.app.entity.TUser;
import com.app.entity.UserInfo;
import com.app.service.IParamService;
import com.app.service.IUserService;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.core.Context;
import java.io.File;
import java.util.Iterator;
import java.util.List;

/**
 * <p>
 * Author: Administrator
 * Date: 2018-2-1 12:01
 * Created with IDEA
 */
@RestController
@RequestMapping("/file")
public class FileController {

    @PostMapping("/imgUpload")
    @Consumes("multipart/form-data;charset=utf-8")
    public String imgUpload(@Context HttpServletRequest request, @Context HttpServletResponse response){

        DiskFileItemFactory factory = new DiskFileItemFactory();

        ServletFileUpload fileUpload = new ServletFileUpload(factory);

        try {
            fileUpload.setHeaderEncoding("utf-8");
            List<FileItem> lists = fileUpload.parseRequest(request);
            Iterator itr = lists.iterator();
            while (itr.hasNext()){
                FileItem item = (FileItem) itr.next();
                if(item.isFormField()){
                    System.out.println(item.getFieldName() + item.getString("UTF-8"));
                }else{
                    if(item.getName() != null && !item.getName().equals("")){
                        System.out.println("上传文件的大小:" + item.getSize());
                        System.out.println("上传文件的类型:" + item.getContentType());
                        System.out.println("上传文件的名称:" + item.getName());
                        String realPath = request.getSession().getServletContext().getRealPath("/");
                        System.out.println(realPath);
                        FileUtils.copyInputStreamToFile(item.getInputStream(), new File(realPath,item.getName()));
                    }
                }
            }
        }catch (Exception e ){
            e.printStackTrace();
        }
        return null;
    }
}
