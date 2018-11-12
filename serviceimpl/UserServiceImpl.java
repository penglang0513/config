package com.penglang.config.serviceimpl;

import com.penglang.config.bean.UserEntity;
import com.penglang.config.dao.UserDao;
import com.penglang.config.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;

@Service("userService")
public class UserServiceImpl implements UserService {

    @Resource
    private UserDao userDao;

    @Override
    public UserEntity findByName(String username) {
        return userDao.findByName(username);
    }

    @Override
    public String findMaxId() {
        return userDao.findMaxId();
    }

    @Override
    public UserEntity findByMobile(String mobile) {
        return userDao.findByMobile(mobile);
    }

    @Override
    public UserEntity findByEmail(String email) {
        return userDao.findByEmail(email);
    }

    @Override
    public int insertUser(Map map) {
        return userDao.insertUser(map);
    }

    @Override
    public int deleteUser(String mobile) {
        return userDao.deleteUser(mobile);
    }

    @Override
    public UserEntity findByMoblieAndPwd(Map map) {
        return userDao.findByMoblieAndPwd(map);
    }


}
