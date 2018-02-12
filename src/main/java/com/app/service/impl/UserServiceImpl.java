package com.app.service.impl;

import com.app.dao.ITParamDao;
import com.app.dao.ITUserDao;
import com.app.entity.TParam;
import com.app.entity.TUser;
import com.app.service.IUserService;
import com.zzuli.eassy.RSAImplement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;
import java.util.List;

/**
 * <p>
 * Author: Administrator
 * Date: 2018-2-1 12:04
 * Created with IDEA
 */
@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private ITUserDao userDao;

    @Autowired
    private ITParamDao paramDao;

    @Override
    public List<TUser> selectAllUser() {
        return userDao.selectAllUser();
    }

    @Override
    public String selectPasswordByUsername(String username) {
        return userDao.selectPasswordByUsername(username);
    }

    @Override
    public boolean queryUsernameExist(String username) {
        if (userDao.queryUsernameExist(username)>0){
            return false;
        }
        return true;
    }

    @Override
    public Integer insertNewUser(String username, String password) {
        TUser user = new TUser();
        user.setUserName(username);
        user.setPassword(password);
        if (userDao.insertSelective(user) > 0){
            RSAImplement rsa = new RSAImplement();
            BigInteger pub = rsa.getN();
            BigInteger pri = rsa.getD();
            //insert param
            TParam param = new TParam();
            param.setUserId(user.getUserId());
            param.setPriKey(pri.toString());
            param.setPubKey(pub.toString());
            return paramDao.insertSelective(param);
        }
        return 0;
    }
}
