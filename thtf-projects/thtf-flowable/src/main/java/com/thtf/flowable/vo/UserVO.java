package com.thtf.flowable.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

@ApiModel(description = "用户信息")
public class UserVO {

    @NotBlank(message = "用户名不能为空")
    @Length(min = 6, max = 20, message = "用户名长度必须在6~20个字符之间")
    @ApiModelProperty(value = "用户名")
    private String username;

    @ApiModelProperty(value = "性别")
    private int sex;

    @ApiModelProperty(value = "生日")
    private Date birthday;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }
}
