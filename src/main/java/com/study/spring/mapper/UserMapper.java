package com.study.spring.mapper;

import com.study.spring.domain.User;
import org.apache.ibatis.annotations.Param;

/**
 * @author wsq
 *         Created by Tp on 2018/12/29.
 */
public interface UserMapper {

    User findByName(@Param("name") String uname);

    User findById(Integer id);
}
