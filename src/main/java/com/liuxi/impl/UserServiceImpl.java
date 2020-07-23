package com.liuxi.impl;

import com.liuxi.entity.User;
import com.liuxi.mapper.UserMapper;
import com.liuxi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public int deleteByPrimaryKey(Integer id) throws Exception {
        return userMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(User record) throws Exception {
        return userMapper.insert(record);
    }

    @Override
    public int insertSelective(User record) throws Exception {
        return userMapper.insertSelective(record);
    }

    @Override
    public User selectByPrimaryKey(Integer id) throws Exception {
        return userMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(User record) throws Exception {
        return userMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(User record) throws Exception {
        return userMapper.updateByPrimaryKey(record);
    }

    @Override
    public User selectByName(String uname) throws Exception {
        return userMapper.selectByName(uname);
    }
}
