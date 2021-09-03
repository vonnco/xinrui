package com.xinrui.framework.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;

@Data
@ToString
@Entity
@Table(name = "xr_operation_log")
@ApiModel(value = "操作日志对象",description = "操作日志对象OperationLog")
public class OperationLog implements Serializable {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @ApiModelProperty("ID")
    private Long id;
    private static final long serialVersionUID = 5473808095309648284L;
    @ApiModelProperty("操作类")
    @Column(name = "operate_class")
    private String operateClass;
    @ApiModelProperty("操作方法")
    @Column(name = "operate_method")
    private String operateMethod;
    @ApiModelProperty("返回值类型")
    @Column(name = "return_class")
    private String returnClass;
    @ApiModelProperty("操作用户")
    @Column(name = "operate_user")
    private String operateUser;
    @ApiModelProperty("操作时间")
    @Column(name = "operate_time")
    private String operateTime;
    @ApiModelProperty("请求参数名及参数值")
    @Column(name = "param_and_value")
    private String paramAndValue;
    @ApiModelProperty("执行方法耗时")
    @Column(name = "cost_time")
    private Long costTime;
    @ApiModelProperty("返回值")
    @Column(name = "return_value")
    private String returnValue;
}
