package com.app.dao;

import com.app.entity.TImage;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
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

    @Select("select * from T_IMAGE where USER_ID = (select INT_ID from T_USER where USER_NAME = #{username}) and REF_ID IS NOT NULL")
    List<TImage> selectImgByUsername(String username);

    @Select("select * from T_IMAGE where USER_ID <> (select INT_ID from T_USER where USER_NAME = #{username}) and REF_ID IS NOT NULL")
    List<TImage> selectOtherImgByUsername(String username);

    @Update("update T_IMAGE SET IMG_CODE = #{code} where IMG_ID = #{imgId}")
    Integer updateImgCodeByImgId(@Param("code") String code, @Param("imgId") Integer imgId);

    @Select("select URL FROM T_IMAGE WHERE IMG_ID = #{imgId}")
    String queryImgUrl(@Param("imgId") Integer imgId);

    @Select("SELECT IMG_ID,IMG_CODE,USER_ID,REMARK,URL,IMG_TYPE,REF_ID FROM T_IMAGE WHERE IMG_ID = (SELECT A.REF_ID FROM T_IMAGE A WHERE A.IMG_ID = #{imgId})")
    TImage selectRefImgByImgId(@Param("imgId") Integer imgId);
}