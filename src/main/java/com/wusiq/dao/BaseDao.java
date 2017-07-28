package com.wusiq.dao;

import java.util.List;

/**
 * 所有服务的基类
 */
public interface BaseDao<T> {
    /*增*/
    boolean addRow(T t);
    /*删*/
    boolean deleteRow(int id);
    /*改*/
    boolean updateRow(T t);
    /*查*/
    T queryRow(int id);
    /*查*/
    List<T> queryRowList();
}
