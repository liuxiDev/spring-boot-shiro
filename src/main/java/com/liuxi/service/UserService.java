package com.liuxi.service;

import com.liuxi.entity.User;

public interface UserService {

    int deleteByPrimaryKey(Integer id) throws Exception;

    int insert(User record) throws Exception;

    int insertSelective(User record) throws Exception;

    User selectByPrimaryKey(Integer id) throws Exception;

    int updateByPrimaryKeySelective(User record) throws Exception;

    int updateByPrimaryKey(User record) throws Exception;

    User selectByName(String uname) throws  Exception;

}
