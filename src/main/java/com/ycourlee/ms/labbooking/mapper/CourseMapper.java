package com.ycourlee.ms.labbooking.mapper;

import com.ycourlee.ms.labbooking.model.entity.CourseEntity;

/**
 * @author yongjiang
 */
public interface CourseMapper {

    int deleteByPrimaryKey(Integer id);

    int insert(CourseEntity record);

    int insertSelective(CourseEntity record);

    CourseEntity selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CourseEntity record);

    int updateByPrimaryKey(CourseEntity record);
}