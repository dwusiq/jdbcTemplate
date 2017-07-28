package com.wusiq.service;

import com.wusiq.entity.UserEntity;

import java.util.List;

/**
 * 用户管理的服务类
 */
public interface UserService {
    /*增*/
    boolean addRow(UserEntity t);
    /*删*/
    boolean deleteRow(int id);
    /*改*/
    boolean updateRow(UserEntity t);
    /*查*/
    UserEntity queryRow(int id);
    /*查*/
    List<UserEntity> queryRowList();
}
