package com.app.service;

import com.app.entity.TParam;

/**
 * <p>
 * Author: Administrator
 * Date: 2018-2-1 14:58
 * Created with IDEA
 */
public interface IParamService {

    String getPublicKey(Integer imgId);

    Integer insertParam(TParam param);

    TParam selectParamByImgId(Integer imgId);

}
