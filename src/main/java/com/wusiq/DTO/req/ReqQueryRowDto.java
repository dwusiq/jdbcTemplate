package com.wusiq.DTO.req;

import java.io.Serializable;

/**
 * 查询用户信息入参实体类
 */
public class ReqQueryRowDto implements Serializable{
    private Integer id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
