package com.app.dao;

import com.app.entity.PermitInfo;
import com.app.entity.TPermit;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

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


//    private String userName;
//
//    private Integer pId;
//
//    private String imgCode;
//
//    private String remark;

    @Select("SELECT U.USER_NAME,P.P_ID,IMG_TYPE,REMARK FROM T_USER U,T_PERMIT P,T_IMAGE IMG,T_USER AU WHERE P.REQ_USER_ID = U.INT_ID AND P.REQ_IMG_ID = IMG.IMG_ID AND IMG.USER_ID = AU.INT_ID AND AU.USER_NAME = #{username} AND IFNULL(P.IS_PERMIT,'0') = '0'")
    List<PermitInfo> gerAllPermitData(@Param("username") String username);

    @Update("UPDATE T_PERMIT SET IS_PERMIT = '1' WHERE P_ID = #{pId}")
    Integer authAccess(@Param("pId") Integer pId);
}