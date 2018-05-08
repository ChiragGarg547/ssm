package com.app.dao;

import com.app.entity.TParam;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository
public interface ITParamDao {
    int deleteByPrimaryKey(Integer paramId);

    int insert(TParam record);

    int insertSelective(TParam record);

    TParam selectByPrimaryKey(Integer paramId);

    int updateByPrimaryKeySelective(TParam record);

    int updateByPrimaryKey(TParam record);
    @Insert("insert into T_PARAM (user_id,pub_key,pri_key) values(select int_id from T_USER where user_name = #{username},#{pub},#{pri})")
    int insertUserParam(String username, String pub, String pri);

    @Select("select pub_key from T_PARAM where user_id = (select int_id from T_USER where user_name = #{username})")
    String getPublicKey(String username);

    TParam selectByUserId(Integer userId);
}