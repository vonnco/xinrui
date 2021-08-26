package com.xinrui.framework.model;

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
@Table(name = "xr_role")
@GenericGenerator(name = "jpa-uuid", strategy = "uuid")
@ApiModel(value="角色对象",description="角色对象Role")
public class Role implements Serializable {
    private static final long serialVersionUID = 9116292721556789062L;
    @Id
    @GeneratedValue(generator = "jpa-uuid")
    @ApiModelProperty("id")
    private String id;
    @Column(name = "role_name")
    @ApiModelProperty("角色名")
    private String roleName;
    @Column(name = "role_code")
    @ApiModelProperty("角色code")
    private String roleCode;
    @ApiModelProperty("描述")
    private String description;
    @Column(name = "update_time")
    @ApiModelProperty("修改时间")
    private Date updateTime;
    @Column(name = "create_time")
    @ApiModelProperty("创建时间")
    private Date createTime;
    @ApiModelProperty("状态")
    private Integer state;
}
