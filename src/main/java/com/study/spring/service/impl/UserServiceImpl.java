package com.study.spring.service.impl;

import com.study.spring.domain.User;
import com.study.spring.mapper.UserMapper;
import com.study.spring.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author wsq
 *         Created by Tp on 2018/12/29.
 */
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User findByName(String uname) {
        return userMapper.findByName(uname);
    }

    @Override
    public User findById(Integer id) {

        return userMapper.findById(id);
    }
}
