package com.ycourlee.ms.labbooking.mapper;

import com.ycourlee.ms.labbooking.model.entity.AdminEntity;

/**
 * @author yongjiang
 */
public interface AdminMapper {

    int deleteByPrimaryKey(Integer id);

    int insert(AdminEntity record);

    int insertSelective(AdminEntity record);

    AdminEntity selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(AdminEntity record);

    int updateByPrimaryKey(AdminEntity record);
}