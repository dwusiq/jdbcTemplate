package com.wusiq.DTO.req;

import javax.validation.constraints.Min;
import java.io.Serializable;

/**
 * 删除用户信息入参实体类
 */
public class ReqDeleteRowDto implements Serializable{
    @Min(value = 1,message="please id>1")
    private Integer id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
