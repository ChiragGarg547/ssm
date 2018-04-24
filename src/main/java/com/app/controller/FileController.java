package com.app.controller;

import com.app.entity.*;
import com.app.service.IImageService;
import com.app.service.IParamService;
import com.app.service.IUserService;
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
                    if("username".equals(item.getFieldName())){
                        username = item.getString("UTF-8");
                    }
                }else{
                    if(item.getName() != null && !item.getName().equals("")){
                        fileName = item.getName();
                        //获取到当前目录
                        savePath = request.getSession().getServletContext().getRealPath("/");
                        savePath += "img\\";
                        //图片存在目录下
                        FileUtils.copyInputStreamToFile(item.getInputStream(), new File(savePath,item.getName()));
                    }
                }
            }
            //图片字节流获取，加密检测，加密
            Integer userId = userService.selectUserIdByUsername(username);
            TParam userParam = paramService.selectParamByUserId(userId);
            RSAImplement rsa = new RSAImplement(new BigInteger(userParam.getPriKey()),new BigInteger(userParam.getPubKey()));
            FileTool ft = new FileTool();
            //获取上传图片的code
            String code = "";
            String actualFileName = savePath + fileName;//上传的图片url
            byte[] fileBs = FileTool.readImg(actualFileName);
            List<Byte> list = new ArrayList<Byte>();
            for(int i = 0; i < fileBs.length; i++){
                list.add(fileBs[i]);
            }
            //获取摘要
            String digest = sha.process(fileBs);
            byte[] bDigest = digest.getBytes();
            BigInteger bigIntegerDigest = new BigInteger(bDigest);
            //rsa密文
            code = rsa.encrypt(bigIntegerDigest).toString();
            //rsa密文byte数组
            byte[] codeBs = code.getBytes();
            //rsa密文摘要
            code = sha.process(codeBs);
            TImage img = new TImage();
            img.setUrl(actualFileName);
            img.setImgType("ORI");
            img.setUserId(userId);
            img.setImgCode(code);
            imageService.insertImg(img);

            backInfo.setSuccess(true);
            backInfo.setBackInfo("成功");
        }catch (Exception e ){
            e.printStackTrace();
            return backInfo;
        }
        return backInfo;
    }
}
