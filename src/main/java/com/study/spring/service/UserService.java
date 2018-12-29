package com.study.spring.service;

import com.study.spring.domain.User;

/**
 * @author wsq
 *         Created by Tp on 2018/12/29.
 */
public interface UserService {

    User findByName(String uname);

    User findById(Integer id);
}
