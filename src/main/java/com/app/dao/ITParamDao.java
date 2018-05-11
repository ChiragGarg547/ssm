package com.app.dao;

import com.app.entity.TParam;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

@Repository
public interface ITParamDao {
    int deleteByPrimaryKey(Integer paramId);

    int insert(TParam record);

    int insertSelective(TParam record);

    TParam selectByPrimaryKey(Integer paramId);

    int updateByPrimaryKeySelective(TParam record);

    int updateByPrimaryKey(TParam record);

    @Select("select pub_key from T_PARAM where img_id = (SELECT A.REF_ID FROM T_IMAGE A WHERE A.IMG_ID = #{imgId})")
    String getPublicKey(@Param("imgId") Integer imgId);

    TParam selectByImgId(Integer imgId);

}