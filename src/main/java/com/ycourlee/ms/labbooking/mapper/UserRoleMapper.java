package com.ycourlee.ms.labbooking.mapper;

import com.ycourlee.ms.labbooking.model.entity.UserRoleEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author yongjiang
 */
public interface UserRoleMapper {

    int deleteByPrimaryKey(Integer id);

    int insert(UserRoleEntity record);

    int insertSelective(UserRoleEntity record);

    UserRoleEntity selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UserRoleEntity record);

    int updateByPrimaryKey(UserRoleEntity record);

    /**
     * batch insert fixed column (user_id, role_id).
     *
     * @param userRoleEntityList userRoleEntityList
     * @return effected rows.
     */
    int batchInsertFcl(@Param("entityList") List<UserRoleEntity> userRoleEntityList);

    int removeByRoleId(Integer roleId);

    List<UserRoleEntity> listByUserId(Integer userId);

    List<Integer> listRoleIdByUserId(Integer userId);
}