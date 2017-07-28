package com.wusiq.DTO.req;

import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.io.Serializable;

/**
 * 添加用户请求的实体类
 */
public class ReqAddRowDto implements Serializable{
    @NotEmpty(message="userName is empty")
    private String userName;
    @Min(value = 1,message="please userAge>1")
    @Max(value = 200,message="userAge<200")
    private Integer userAge;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getUserAge() {
        return userAge;
    }

    public void setUserAge(Integer userAge) {
        this.userAge = userAge;
    }
}
