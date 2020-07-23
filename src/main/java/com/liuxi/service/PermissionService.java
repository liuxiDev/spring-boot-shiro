package com.liuxi.service;

import com.liuxi.entity.Permission;

import java.util.List;

public interface PermissionService {

    int deleteByPrimaryKey(Integer id) throws  Exception;

    int insert(Permission record) throws  Exception;

    int insertSelective(Permission record) throws  Exception;

    Permission selectByPrimaryKey(Integer id) throws  Exception;

    int updateByPrimaryKeySelective(Permission record) throws  Exception;

    int updateByPrimaryKey(Permission record) throws  Exception;

    List<Permission> selectByUid(Integer uid) throws  Exception;
}
