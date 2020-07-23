package com.liuxi.impl;

import com.liuxi.entity.Permission;
import com.liuxi.mapper.PermissionMapper;
import com.liuxi.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PermissionServiceImpl implements PermissionService {

    @Autowired
    private PermissionMapper permissionMapper;

    @Override
    public int deleteByPrimaryKey(Integer id) throws Exception {
        return permissionMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(Permission record) throws Exception {
        return permissionMapper.insert(record);
    }

    @Override
    public int insertSelective(Permission record) throws Exception {
        return permissionMapper.insertSelective(record);
    }

    @Override
    public Permission selectByPrimaryKey(Integer id) throws Exception {
        return permissionMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(Permission record) throws Exception {
        return permissionMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(Permission record) throws Exception {
        return permissionMapper.updateByPrimaryKey(record);
    }

    @Override
    public List<Permission> selectByUid(Integer uid) throws Exception {
        return permissionMapper.selectByUid(uid);
    }
}
