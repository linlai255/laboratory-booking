package com.ycourlee.ms.labbooking.mapper;

import com.ycourlee.ms.labbooking.model.entity.RoleResourceEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author yongjiang
 */
public interface RoleResourceMapper {

    int deleteByPrimaryKey(Integer id);

    int insert(RoleResourceEntity record);

    int insertSelective(RoleResourceEntity record);

    RoleResourceEntity selectByPrimaryKey(Integer id);

    RoleResourceEntity selectByPrimaryKeyEvenIfRemoved(Integer id);

    int updateByPrimaryKeySelective(RoleResourceEntity record);

    int updateByPrimaryKey(RoleResourceEntity record);

    /**
     * fcl.role_id, resource_id
     *
     * @param roleResourceEntityEntityList roleResourceEntityList
     * @return effected rows.
     */
    int batchInsertFcl(@Param("roleResourceEntityEntityList") List<RoleResourceEntity> roleResourceEntityEntityList);

    int removeByRoleId(Integer roleId);

    List<RoleResourceEntity> listByRoleId(Integer roleId);

    List<Integer> listResIdByRoleId(Integer roleId);

    List<Integer> listOrderedRoleIdByResId(Integer resId);
}