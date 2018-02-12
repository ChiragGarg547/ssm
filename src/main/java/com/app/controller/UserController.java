package com.app.controller;

import com.app.entity.TUser;
import com.app.entity.UserInfo;
import com.app.service.IParamService;
import com.app.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * Author: Administrator
 * Date: 2018-2-1 12:01
 * Created with IDEA
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private IUserService userService;
    @Autowired
    private IParamService paramService;

    @GetMapping(value = "/getAllUser")
    @ResponseBody
    public List<TUser> selectAllUser(){
        List<TUser> list = userService.selectAllUser();
        return list;
    }

    @GetMapping(value = "/checkLogin")
    @ResponseBody
    public Boolean checkLogin(@RequestParam(value = "username") String username, @RequestParam(value = "password") String password){
        if(username!=null&&password!=null){
            String realPass = userService.selectPasswordByUsername(username);

            if (realPass == password){
                return true;
            }
        }
        return false;
    }

    @GetMapping(value = "/register")
    @ResponseBody
    public UserInfo register(@RequestParam(value = "username") String username, @RequestParam(value = "password") String password){
        UserInfo back = new UserInfo();
        back.setSuccess(false);
        if(username!=null&& password!=null){
            if(userService.queryUsernameExist(username)){
                if (userService.insertNewUser(username,password) > 0){
                    String pk = paramService.getPrivateKey(username);
                    back.setPrivateKey(pk);
                    back.setSuccess(true);
                    return back;
                }
            }
        }
        return back;
    }
}
