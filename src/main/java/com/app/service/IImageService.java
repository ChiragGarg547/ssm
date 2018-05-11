package com.app.service;

import com.app.entity.TImage;
import org.apache.ibatis.annotations.Update;

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

    Integer updateImg(TImage img);

    String queryImgUrl(Integer imgId);

    TImage selectImgByImgId(Integer imgId);

    TImage selectRefImgByImgId(Integer imgId);

}
