package com.app.dao;

import com.app.entity.TUser;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ITUserDao {
    int deleteByPrimaryKey(Integer userId);

    int insert(TUser record);

    int insertSelective(TUser record);

    TUser selectByPrimaryKey(Integer userId);

    int updateByPrimaryKeySelective(TUser record);

    int updateByPrimaryKey(TUser record);

    @Select("select * from T_USER")
    List<TUser> selectAllUser();

    @Select("select password from T_USER where user_name = #{username}")
    String selectPasswordByUsername(String username);

    @Select("select count(*) from T_USER where user_name = #{username}")
    Integer queryUsernameExist(String username);

    @Insert("insert into T_USER (user_name, password) values (#{username,jdbcType=VARCHAR},#{password,jdbcType=VARCHAR})")
    Integer insertNewUser(String username,String password);
}