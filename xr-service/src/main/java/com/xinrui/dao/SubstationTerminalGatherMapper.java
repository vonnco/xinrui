package com.xinrui.dao;

import com.xinrui.framework.model.SubstationTerminalGather;

public interface SubstationTerminalGatherMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SubstationTerminalGather record);

    int insertSelective(SubstationTerminalGather record);

    SubstationTerminalGather selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SubstationTerminalGather record);

    int updateByPrimaryKey(SubstationTerminalGather record);
}