package com.xinrui.framework.model;

import cn.afterturn.easypoi.excel.annotation.Excel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@ToString
@Entity
@Table(name = "xr_user")
@GenericGenerator(name = "jpa-uuid", strategy = "uuid")
@ApiModel(value = "用户对象",description = "用户对象User")
public class User implements Serializable {
    @Id
    @GeneratedValue(generator = "jpa-uuid")
    @ApiModelProperty("id")
    private String id;
    @Excel(name = "用户名")
    @ApiModelProperty("用户名")
    private String username;
    @Excel(name = "密码")
    @ApiModelProperty("密码")
    private String password;
    @Excel(name = "姓名")
    @ApiModelProperty("姓名")
    private String name;
    @Excel(name = "性别")
    @ApiModelProperty("性别")
    private Integer sex;
    @Excel(name = "手机号")
    @ApiModelProperty("手机号")
    private String tel;
    @Excel(name = "邮箱")
    @ApiModelProperty("邮箱")
    private String email;
    @Excel(name = "修改时间")
    @Column(name = "update_time")
    @ApiModelProperty("修改时间")
    private Date updateTime;
    @Excel(name = "创建时间")
    @Column(name = "create_time")
    @ApiModelProperty("创建时间")
    private Date createTime;
    @Excel(name = "状态")
    @ApiModelProperty("状态")
    private Integer state;
    @Transient//标注非数据库字段
    private String roleIds;//角色id
}
