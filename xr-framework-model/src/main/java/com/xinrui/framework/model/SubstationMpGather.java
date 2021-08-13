package com.xinrui.framework.model;

import java.io.Serializable;

/**
 * t_substation_mp_gather
 * @author 
 */
public class SubstationMpGather implements Serializable {
    private Integer id;

    /**
     * 基础信息ID
     */
    private Integer basicId;

    /**
     * 计量点地址是否正确
     */
    private Integer mpnIsRight;

    /**
     * 电能表条码
     */
    private String meterCode;

    /**
     * 电能表情况描述
     */
    private String meterDescription;

    /**
     * 电表号对应的线路名称
     */
    private String lineName;

    /**
     * 电能表生产日期
     */
    private String meterDate;

    private Integer mpCode;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getBasicId() {
        return basicId;
    }

    public void setBasicId(Integer basicId) {
        this.basicId = basicId;
    }

    public Integer getMpnIsRight() {
        return mpnIsRight;
    }

    public void setMpnIsRight(Integer mpnIsRight) {
        this.mpnIsRight = mpnIsRight;
    }

    public String getMeterCode() {
        return meterCode;
    }

    public void setMeterCode(String meterCode) {
        this.meterCode = meterCode;
    }

    public String getMeterDescription() {
        return meterDescription;
    }

    public void setMeterDescription(String meterDescription) {
        this.meterDescription = meterDescription;
    }

    public String getLineName() {
        return lineName;
    }

    public void setLineName(String lineName) {
        this.lineName = lineName;
    }

    public String getMeterDate() {
        return meterDate;
    }

    public void setMeterDate(String meterDate) {
        this.meterDate = meterDate;
    }

    public Integer getMpCode() {
        return mpCode;
    }

    public void setMpCode(Integer mpCode) {
        this.mpCode = mpCode;
    }
}