package com.app.service;

import com.app.entity.TParam;
import com.app.entity.TPermit;
import com.app.entity.TUser;

import java.util.List;

/**
 * <p>
 * Author: Administrator
 * Date: 2018-2-1 12:03
 * Created with IDEA
 */
public interface IPermitService {
    boolean queryPermitExist(Integer userId, Integer imgId);

    int insertPermit(TPermit permit);

    boolean judgePermitByUserId(Integer userId, Integer imgId);

}
