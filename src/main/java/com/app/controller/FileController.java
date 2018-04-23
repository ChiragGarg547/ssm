package com.app.controller;

import com.app.entity.*;
import com.app.service.IImageService;
import com.app.service.IParamService;
import com.app.service.IUserService;
import com.google.common.primitives.Bytes;
import com.zzuli.eassy.ByteHexUtil;
import com.zzuli.eassy.FileTool;
import com.zzuli.eassy.RSAImplement;
import com.zzuli.eassy.SHAImplement;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.core.Context;
import java.io.File;
import java.math.BigInteger;
import java.util.ArrayList;
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

    @Autowired
    private IImageService imageService;

    @Autowired
    private IUserService userService;

    @Autowired
    private IParamService paramService;

    private static String ORI = "ORI";

    private static String DEAL = "DEAL";

    @PostMapping("/imgUpload")
    @Consumes("multipart/form-data;charset=utf-8")
    public ImgBackInfo imgUpload(@Context HttpServletRequest request, @Context HttpServletResponse response){

        DiskFileItemFactory factory = new DiskFileItemFactory();

        ServletFileUpload fileUpload = new ServletFileUpload(factory);

        String username = "", fileName = "", savePath = "";

        ImgBackInfo backInfo = new ImgBackInfo();

        SHAImplement sha = new SHAImplement();

        try {
            fileUpload.setHeaderEncoding("utf-8");
            List<FileItem> lists = fileUpload.parseRequest(request);
            Iterator itr = lists.iterator();
            while (itr.hasNext()){
                FileItem item = (FileItem) itr.next();
                if(item.isFormField()){
                    if(item.getFieldName() == "username"){
                        username = item.getString("UTF-8");
                    }
                }else{
                    if(item.getName() != null && !item.getName().equals("")){
                        fileName = item.getName();
                        System.out.println("上传文件的大小:" + item.getSize());
                        System.out.println("上传文件的类型:" + item.getContentType());
                        System.out.println("上传文件的名称:" + item.getName());
                        //获取到当前目录
                        savePath = request.getSession().getServletContext().getRealPath("/");
                        savePath += "/img";
                        FileUtils.copyInputStreamToFile(item.getInputStream(), new File(savePath,item.getName()));
                    }
                }
            }
            Integer userId = userService.selectUserIdByUsername(username);
            TParam userParam = paramService.selectParamByUserId(userId);
            RSAImplement rsa = new RSAImplement(new BigInteger(userParam.getPriKey()),new BigInteger(userParam.getPubKey()));
            FileTool ft = new FileTool();
            //图片加密另存一份
            String actualFileName = savePath + "/" + fileName;//原图url
            String code = ft.getEncryptionAttr(actualFileName);
            String newFileName = savePath + "/safe/" + fileName;//加密图url
            if(!StringUtils.isEmpty(code)){
                //图片加密
                byte[] fileBs = FileTool.readImg(actualFileName);
                List<Byte> list = new ArrayList<Byte>();
                for(int i = 0; i < fileBs.length; i++){
                    list.add(fileBs[i]);
                }
                //获取摘要
                String digest = sha.process(fileBs);
                byte[] bDigest = digest.getBytes();
                BigInteger bigIntegerDigest = new BigInteger(bDigest);
                code = rsa.encrypt(bigIntegerDigest).toString();
                if(FileTool.generateImg(fileBs,newFileName,fileName.substring(fileName.lastIndexOf('.') + 1))){
                    //设置加密信息
                    ft.setEncryptionAttr(code,newFileName);
                    //记录图片信息
                    //原图
                    TImage img = new TImage();
                    img.setImgType(ORI);
                    img.setUrl(actualFileName);
                    img.setUserId(userId);
                    imageService.insertImg(img);
                    //加密图
                    img.setImgType(DEAL);
                    img.setUrl(newFileName);
                    imageService.insertImg(img);
                    backInfo.setSuccess(true);
                    backInfo.setBackInfo("上传&加密成功");
                }
            }else{
                backInfo.setBackInfo("图片已加密");
                backInfo.setSuccess(false);
            }

        }catch (Exception e ){
            e.printStackTrace();
            return backInfo;
        }
        return backInfo;
    }
}
