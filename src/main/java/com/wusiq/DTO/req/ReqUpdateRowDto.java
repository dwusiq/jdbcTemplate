package com.wusiq.DTO.req;

import java.io.Serializable;

/**
 * 修改用户信息入参实体类
 */
public class ReqUpdateRowDto implements Serializable{
    private Integer id;
    private String userNmae;
    private Integer userAge;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserNmae() {
        return userNmae;
    }

    public void setUserNmae(String userNmae) {
        this.userNmae = userNmae;
    }

    public Integer getUserAge() {
        return userAge;
    }

    public void setUserAge(Integer userAge) {
        this.userAge = userAge;
    }
}
