package com.app.service;

import com.app.entity.TImage;

import java.util.List;

/**
 * <p>
 * Author: Administrator
 * Date: 2018-4-23 14:57
 * Created with IDEA
 */
public interface IImageService {

    Integer insertImg(TImage img);

    List<TImage> selectImgByUsername(String username);

    List<TImage> selectOtherImgByUsername(String username);

}
