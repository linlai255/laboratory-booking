package com.ycourlee.ms.labbooking.mapper;

import com.ycourlee.ms.labbooking.model.entity.ResourceEntity;

/**
 * @author yongjiang
 */
public interface ResourceMapper {

    int deleteByPrimaryKey(Integer id);

    int insert(ResourceEntity record);

    int insertSelective(ResourceEntity record);

    ResourceEntity selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ResourceEntity record);

    int updateByPrimaryKey(ResourceEntity record);
}