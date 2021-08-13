package com.xinrui.framework.model;

import java.io.Serializable;

/**
 * t_substation_terminal_gather
 * @author 
 */
public class SubstationTerminalGather implements Serializable {
    private Integer id;

    /**
     * 基础信息ID
     */
    private Integer basicId;

    /**
     * 厂站终端规格(1-机架式；2-挂表式)
     */
    private Integer stationScale;

    /**
     * 厂站终端条码
     */
    private String stationCode;

    /**
     * 厂站终端地址码
     */
    private String stationAddrCode;

    /**
     * 厂站终端情况
     */
    private String stationDescription;

    /**
     * 厂站终端生产日期
     */
    private String stationDate;

    /**
     * 通信方式
     */
    private Integer socketType;

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

    public Integer getStationScale() {
        return stationScale;
    }

    public void setStationScale(Integer stationScale) {
        this.stationScale = stationScale;
    }

    public String getStationCode() {
        return stationCode;
    }

    public void setStationCode(String stationCode) {
        this.stationCode = stationCode;
    }

    public String getStationAddrCode() {
        return stationAddrCode;
    }

    public void setStationAddrCode(String stationAddrCode) {
        this.stationAddrCode = stationAddrCode;
    }

    public String getStationDescription() {
        return stationDescription;
    }

    public void setStationDescription(String stationDescription) {
        this.stationDescription = stationDescription;
    }

    public String getStationDate() {
        return stationDate;
    }

    public void setStationDate(String stationDate) {
        this.stationDate = stationDate;
    }

    public Integer getSocketType() {
        return socketType;
    }

    public void setSocketType(Integer socketType) {
        this.socketType = socketType;
    }
}