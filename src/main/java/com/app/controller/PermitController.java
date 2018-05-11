package com.app.controller;

import com.app.entity.KeyInfo;
import com.app.entity.TPermit;
import com.app.service.IParamService;
import com.app.service.IPermitService;
import com.app.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * Author: Administrator
 * Date: 2018-2-1 12:01
 * Created with IDEA
 */
@RestController
@RequestMapping("/permit")
public class PermitController {

    @Autowired
    private IPermitService permitService;
    @Autowired
    private IUserService userService;
    @Autowired
    private IParamService paramService;

    @GetMapping(value = "/askForImgPermit")
    @ResponseBody
    public Boolean askForImgPermit(@RequestParam(value = "imgId") Integer imgId, @RequestParam(value = "username") String username){
        Integer userId = userService.selectUserIdByUsername(username);
        if (permitService.queryPermitExist(userId,imgId)){
            //没有申请过
            TPermit permit = new TPermit();
            permit.setReqImgId(imgId);
            permit.setReqUserId(userId);
            if (permitService.insertPermit(permit)>0){
                return true;
            }
        }
        return false;
    }

    @GetMapping(value = "/getImgPubKey")
    @ResponseBody
    public KeyInfo getImgPubKey(@RequestParam(value = "imgId") Integer imgId, @RequestParam(value = "username") String username){
        Integer userId = userService.selectUserIdByUsername(username);
        KeyInfo key = new KeyInfo();
        if(permitService.judgePermitByUserId(userId,imgId)){
            String pubKey = paramService.getPublicKey(imgId);
            key.setPublicKey(pubKey);
            key.setSuccess(true);

        }
        return key;
    }

}
