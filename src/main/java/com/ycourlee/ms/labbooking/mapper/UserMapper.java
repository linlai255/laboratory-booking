package com.ycourlee.ms.labbooking.mapper;

import com.ycourlee.ms.labbooking.model.entity.UserEntity;
import org.apache.ibatis.annotations.Param;

/**
 * @author yongjiang
 */
public interface UserMapper {

    int deleteByPrimaryKey(Integer id);

    int insert(UserEntity record);

    int insertSelective(UserEntity record);

    UserEntity selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UserEntity record);

    int updateByPrimaryKey(UserEntity record);

    UserEntity selectByPhoneTypeFcl(@Param("type") Byte type, @Param("phone") String phone);
}