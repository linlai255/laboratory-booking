package com.ycourlee.ms.labbooking.mapper;

import com.ycourlee.ms.labbooking.model.entity.RoleEntity;
import org.apache.ibatis.annotations.Param;

import java.util.Collection;
import java.util.List;

/**
 * @author yongjiang
 */
public interface RoleMapper {

    int deleteByPrimaryKey(Integer id);

    int insert(RoleEntity record);

    int insertSelective(RoleEntity record);

    RoleEntity selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(RoleEntity record);

    int updateByPrimaryKey(RoleEntity record);

    List<RoleEntity> listByIdCollection(@Param("idCollection") Collection<Integer> idCollection);

    int removeByPrimaryKey(Integer id);

    int countByIdCollection(@Param("idCollection") Collection<Integer> idCollection);
}