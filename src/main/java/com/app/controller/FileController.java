package com.app.controller;

import com.app.entity.*;
import com.app.service.IImageService;
import com.app.service.IParamService;
import com.app.service.IUserService;
import com.zzuli.eassy.FileTool;
import com.zzuli.eassy.RSAImplement;
import com.zzuli.eassy.SHAImplement;
import net.coobird.thumbnailator.Thumbnails;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.core.Context;
import java.io.File;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

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

        String url = "",remark = "", img_name = "", uuidName = "";

        String smallImgUrl = "", smallImgPath = "";

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
                    if("imgName".equals(item.getFieldName())){
                        img_name = item.getString("UTF-8");
                    }
                    if("remark".equals(item.getFieldName())){
                        remark = item.getString("UTF-8");
                    }
                }else{
                    if(item.getName() != null && !item.getName().equals("")){
                        fileName = item.getName();
                        //获取到当前目录
                        savePath = request.getSession().getServletContext().getRealPath("/");
                        savePath += "img/";
                        //图片存在目录下
                        //记录的url为自动生成的字符串
                        uuidName  = UUID.randomUUID() + fileName.substring(fileName.lastIndexOf('.'));
                        url = "img/" + uuidName;
                        FileUtils.copyInputStreamToFile(item.getInputStream(), new File(savePath,uuidName));
                        smallImgPath = savePath + "small/";
                        smallImgUrl = "img/small/" + uuidName;
                        File dir = new File(smallImgPath);
                        if(!dir.exists()){
                            dir.mkdir();
                        }
                        Thumbnails.of(savePath+uuidName).size(300,150).toFile(smallImgPath + uuidName);
                    }
                }
            }
            Integer userId = userService.selectUserIdByUsername(username);

            TImage img = new TImage();
            img.setUrl(url);
            img.setImgType(img_name);
            img.setUserId(userId);
            img.setRemark(remark);


            if (imageService.insertImg(img)>0){

                Integer imgId = img.getImgId();
                TImage smallImg = new TImage();
                smallImg.setUrl(smallImgUrl);
                smallImg.setImgType(img_name);
                smallImg.setUserId(userId);
                smallImg.setRemark(remark);
                smallImg.setRefId(imgId);

                imageService.insertImg(smallImg);

                //生成图片的公钥密钥
                RSAImplement rsa = new RSAImplement();
                BigInteger pub = rsa.getN();
                BigInteger pri = rsa.getD();
                //insert param
                TParam param = new TParam();
                param.setImgId(imgId);
                param.setPriKey(pri.toString());
                param.setPubKey(pub.toString());
                if(paramService.insertParam(param) > 0){
                    FileTool ft = new FileTool();
                    String code = "";
                    String actualFileName = savePath + uuidName;//上传的图片url
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
                    img.setImgCode(code);
                    imageService.updateImg(img);
                    backInfo.setSuccess(true);
                    backInfo.setBackInfo("成功");
                }else{
                    backInfo.setSuccess(false);
                    backInfo.setBackInfo("图片保存失败");
                }
            }else{
                backInfo.setSuccess(false);
                backInfo.setBackInfo("图片保存失败");
            }
        }catch (Exception e ){
            e.printStackTrace();
            return backInfo;
        }
        return backInfo;
    }

    @GetMapping(value = "/getImgByUsername")
    @ResponseBody
    public List<TImage> getImgByUsername(@RequestParam(value = "username") String username){

        return imageService.selectImgByUsername(username);

    }

    @GetMapping(value = "/getOtherImgByUsername")
    @ResponseBody
    public List<TImage> getOtherImgByUsername(@RequestParam(value = "username") String username){

        return imageService.selectOtherImgByUsername(username);

    }

    @GetMapping(value = "/downLoadImg")
    @ResponseBody
    public TImage downLoadImg(@RequestParam(value = "imgId") Integer imgId, @RequestParam(value = "pubKey") String pubKey){
        TImage img = imageService.selectRefImgByImgId(imgId);
        //校验
        FileTool ft = new FileTool();
        String code = "",savePath = "";
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        savePath = request.getSession().getServletContext().getRealPath("/");
        //获取图片url
        String uuidName = img.getUrl();
        String actualFileName = savePath + uuidName;//上传的图片url
        byte[] fileBs = FileTool.readImg(actualFileName);
        List<Byte> list = new ArrayList<Byte>();
        for(int i = 0; i < fileBs.length; i++){
            list.add(fileBs[i]);
        }
        SHAImplement sha = new SHAImplement();
        //获取摘要
        String digest = sha.process(fileBs);
        byte[] bDigest = digest.getBytes();
        //未加密数据
        BigInteger bigIntegerDigest = new BigInteger(bDigest);

        //解密图片code
        TParam param = paramService.selectParamByImgId(img.getImgId());
        RSAImplement rsa = new RSAImplement(new BigInteger(param.getPriKey()),new BigInteger(pubKey));
        BigInteger encodeBI = rsa.encode(new BigInteger(img.getImgCode()));

        if(bigIntegerDigest.equals(encodeBI)){
                return img;
        }

        return null;
    }
}
