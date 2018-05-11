package com.app.service.impl;

import com.app.dao.ITImageDao;
import com.app.dao.ITPermitDao;
import com.app.entity.TImage;
import com.app.entity.TPermit;
import com.app.service.IImageService;
import com.app.service.IPermitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * Author: Administrator
 * Date: 2018-2-1 14:58
 * Created with IDEA
 */
@Service
public class PermitServiceImpl implements IPermitService {

    @Autowired
    private ITPermitDao permitDao;

    @Override
    public boolean queryPermitExist(Integer userId, Integer imgId) {
        try {
            if (permitDao.queryPermitExist(userId,imgId) > 0){
                return false;
            }
            return true;
        }catch (Exception e ){
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public int insertPermit(TPermit permit) {
        return permitDao.insertSelective(permit);
    }

    @Override
    public boolean judgePermitByUserId(Integer userId, Integer imgId) {
        if (permitDao.judgePermitByUserId(userId,imgId) > 0){
            return true;
        }
        return false;
    }
}
