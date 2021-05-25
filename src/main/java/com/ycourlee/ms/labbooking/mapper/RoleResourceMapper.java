package com.ycourlee.ms.labbooking.mapper;

import com.ycourlee.ms.labbooking.model.entity.RoleResourceEntity;

/**
 * @author yongjiang
 */
public interface RoleResourceMapper {

    int deleteByPrimaryKey(Integer id);

    int insert(RoleResourceEntity record);

    int insertSelective(RoleResourceEntity record);

    RoleResourceEntity selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(RoleResourceEntity record);

    int updateByPrimaryKey(RoleResourceEntity record);
}