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
@Table(name = "xr_menu")
@GenericGenerator(name = "jpa-uuid", strategy = "uuid")
@ApiModel(value = "菜单对象",description = "菜单对象Menu")
public class Menu implements Serializable {
    private static final long serialVersionUID = -5276348770980259529L;
    @Id
    @GeneratedValue(generator = "jpa-uuid")
    @ApiModelProperty("id")
    private String id;
    @Column(name = "menu_name")
    @ApiModelProperty("菜单名")
    private String menuName;
    @Column(name = "menu_code")
    @ApiModelProperty("菜单code")
    private String menuCode;
    @Column(name = "parent_id")
    @ApiModelProperty("父id")
    private String parentId;
    @ApiModelProperty("链接")
    private String url;
    @ApiModelProperty("是否是菜单")
    private Integer isMenu;
    @Column(name = "update_time")
    @ApiModelProperty("修改时间")
    private Date updateTime;
    @Column(name = "create_time")
    @ApiModelProperty("创建时间")
    private Date createTime;
    @ApiModelProperty("排序")
    private Integer sort;
    @ApiModelProperty("状态")
    private Integer state;
}
