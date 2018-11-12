package com.penglang.config.dao;

import com.penglang.config.bean.UserEntity;

import java.util.Map;

public interface UserDao {
    UserEntity findByName(String username);
    String findMaxId();
    UserEntity findByMobile(String mobile);
    UserEntity findByEmail(String email);
    int insertUser(Map map);
    int deleteUser(String mobile);
    UserEntity findByMoblieAndPwd(Map map);
}
