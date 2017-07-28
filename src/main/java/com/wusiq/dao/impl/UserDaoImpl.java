package com.wusiq.dao.impl;

import com.wusiq.dao.UserDao;
import com.wusiq.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;

/**
 * Dao实现类
 */
@Repository
public class UserDaoImpl implements UserDao{
    private static String ADD_ROW_SQL = "insert into t_user(userName,userAge)values(?,?)";
    private static String DEL_ROW_SQL = "delete from t_user where id = ?";
    private static String UPD_ROW_SQL = "update t_user set userName=?,userAge=? where id = ?";
    private static String SEL_ROW_SQL = "select * from t_user where id = ?";
    private static String SEL_ALL_SQL = "select * from t_user";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    /*添加*/
    @Override
    public boolean addRow(UserEntity userEntity) {
        Object[] params = new Object[]{userEntity.getUserNmae(), userEntity.getUserAge()};
        int[] types = new int[]{Types.VARCHAR,Types.INTEGER};
        int i = jdbcTemplate.update(ADD_ROW_SQL,params,types);
        return i==1?true:false;
    }

    /*删除*/
    @Override
    public boolean deleteRow(int id) {
        Object[] params = new Object[] {id};
        int[] types = new int[]{Types.INTEGER};
        int i = jdbcTemplate.update(DEL_ROW_SQL,params,types);
        return i==1?true:false;
    }

   /*改*/
    @Override
    public boolean updateRow(UserEntity userEntity) {
        Object[] params = new Object[]{userEntity.getUserNmae(), userEntity.getUserAge(),userEntity.getId()};
        int[] types = new int[]{Types.VARCHAR,Types.INTEGER,Types.INTEGER};
        int i = jdbcTemplate.update(UPD_ROW_SQL,params,types);
        return i==1?true:false;
    }

    /*查*/
    @Override
    public UserEntity queryRow(int id) {
        Object[] params = new Object[] {id};
        int[] types = new int[] {Types.INTEGER};
        List items = jdbcTemplate.query(SEL_ROW_SQL,params,types,new UserMapper());
        if(items.isEmpty()){
            return null;
        }
        return (UserEntity)items.get(0);
    }

    /*查List*/
    @Override
    public List<UserEntity> queryRowList() {
        return jdbcTemplate.query(SEL_ALL_SQL,new UserMapper());
    }

    protected class UserMapper implements RowMapper {
        public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
            UserEntity userEntity = new UserEntity();
            userEntity.setId(rs.getInt("id"));
            userEntity.setUserNmae(rs.getString("userName"));
            userEntity.setUserAge(rs.getInt("userAge"));
            return userEntity;
        }
    }

}


