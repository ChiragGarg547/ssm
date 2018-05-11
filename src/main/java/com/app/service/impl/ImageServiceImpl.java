package com.app.service.impl;

import com.app.dao.ITImageDao;
import com.app.entity.TImage;
import com.app.service.IImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * Author: Administrator
 * Date: 2018-2-1 14:58
 * Created with IDEA
 */
@Service
public class ImageServiceImpl implements IImageService {

    @Autowired
    private ITImageDao imageDao;

    @Override
    public Integer insertImg(TImage img) {
        return imageDao.insertSelective(img);
    }

    @Override
    public List<TImage> selectImgByUsername(String username) {
        return imageDao.selectImgByUsername(username);
    }

    @Override
    public List<TImage> selectOtherImgByUsername(String username) {
        return imageDao.selectOtherImgByUsername(username);
    }

    @Override
    public Integer updateImg(TImage img) {
        return imageDao.updateByPrimaryKeySelective(img);
    }

    @Override
    public String queryImgUrl(Integer imgId) {
        return imageDao.queryImgUrl(imgId);
    }

    @Override
    public TImage selectImgByImgId(Integer imgId) {
        return imageDao.selectByPrimaryKey(imgId);
    }

    @Override
    public TImage selectRefImgByImgId(Integer imgId) {
        return imageDao.selectRefImgByImgId(imgId);
    }

}
