package com.app.dao;

import com.app.entity.TPermit;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository
public interface ITPermitDao {
    int deleteByPrimaryKey(Integer pId);

    int insert(TPermit record);

    int insertSelective(TPermit record);

    TPermit selectByPrimaryKey(Integer pId);

    int updateByPrimaryKeySelective(TPermit record);

    int updateByPrimaryKey(TPermit record);

    @Select("select count(P_ID) from T_PERMIT where REQ_USER_ID = #{userId} and REQ_IMG_ID = #{imgId}")
    int queryPermitExist(@Param("userId") Integer userId, @Param("imgId") Integer imgId);

    @Select("select count(P_ID) from T_PERMIT where req_user_id = #{userId} and req_img_id = #{imgId} and is_permit = '1' ")
    int judgePermitByUserId(@Param("userId") Integer userId, @Param("imgId") Integer imgId);
}