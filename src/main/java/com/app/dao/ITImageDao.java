package com.app.dao;

import com.app.entity.TImage;
import org.springframework.stereotype.Repository;

@Repository
public interface ITImageDao {
    int deleteByPrimaryKey(Integer imgId);

    int insert(TImage record);

    int insertSelective(TImage record);

    TImage selectByPrimaryKey(Integer imgId);

    int updateByPrimaryKeySelective(TImage record);

    int updateByPrimaryKey(TImage record);
}