package com.penglang.config.service;

import com.penglang.config.bean.UserEntity;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;

import java.util.Map;

public interface UserService {
    UserEntity findByName(String username);

    String findMaxId();

    @Cacheable(key = "#mobile",value="myuser")
    UserEntity findByMobile(String mobile);

    UserEntity findByEmail(String email);

    int insertUser(Map map);

    @CacheEvict(key = "#mobile",value="myuser")
    int deleteUser(String mobile);

    @Cacheable(key = "#map.get('mobile')+#map.get('pwd')",value="myuser")
    UserEntity findByMoblieAndPwd(Map map);
}
