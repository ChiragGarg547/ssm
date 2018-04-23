package com.app.service.impl;

import com.app.dao.ITParamDao;
import com.app.entity.TParam;
import com.app.service.IParamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * Author: Administrator
 * Date: 2018-2-1 14:58
 * Created with IDEA
 */
@Service
public class ParamServiceImpl implements IParamService {

    @Autowired
    private ITParamDao paramDao;

    @Override
    public String getPublicKey(String username) {
        return paramDao.getPublicKey(username);
    }

    @Override
    public TParam selectParamByUserId(Integer userId) {
        return paramDao.selectByUserId(userId);
    }

}
