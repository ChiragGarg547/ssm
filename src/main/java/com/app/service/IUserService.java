package com.app.service;

import com.app.entity.TUser;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * Author: Administrator
 * Date: 2018-2-1 12:03
 * Created with IDEA
 */
public interface IUserService {

    List<TUser> selectAllUser();

    String selectPasswordByUsername(String username);

    boolean queryUsernameExist(String username);

    Integer insertNewUser(String username,String password);

    Integer selectUserIdByUsername(String username);
}
