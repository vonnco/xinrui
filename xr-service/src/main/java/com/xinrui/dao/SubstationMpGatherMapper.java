package com.xinrui.dao;

import com.xinrui.framework.model.SubstationMpGather;

public interface SubstationMpGatherMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SubstationMpGather record);

    int insertSelective(SubstationMpGather record);

    SubstationMpGather selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SubstationMpGather record);

    int updateByPrimaryKey(SubstationMpGather record);
}