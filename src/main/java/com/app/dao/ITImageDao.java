package com.app.dao;

import com.app.entity.TImage;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ITImageDao {
    int deleteByPrimaryKey(Integer imgId);

    int insert(TImage record);

    int insertSelective(TImage record);

    TImage selectByPrimaryKey(Integer imgId);

    int updateByPrimaryKeySelective(TImage record);

    int updateByPrimaryKey(TImage record);

    @Select("select * from T_IMAGE where USER_ID = (select INT_ID from T_USER where USER_NAME = #{username})")
    List<TImage> selectImgByUsername(String username);
}