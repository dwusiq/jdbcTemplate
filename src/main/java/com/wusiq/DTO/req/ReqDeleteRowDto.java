package com.wusiq.DTO.req;

import java.io.Serializable;

/**
 * 删除用户信息入参实体类
 */
public class ReqDeleteRowDto implements Serializable{
    private Integer id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
