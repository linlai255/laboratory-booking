package com.ycourlee.ms.labbooking.mapper;

import com.ycourlee.ms.labbooking.model.entity.CourseEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author yongjiang
 */
public interface CourseMapper {

    int deleteByPrimaryKey(Integer id);

    int insert(CourseEntity record);

    int insertSelective(CourseEntity record);

    CourseEntity selectByPrimaryKey(Integer id);

    CourseEntity selectByPrimaryKeyEvenIfRemoved(Integer id);

    int updateByPrimaryKeySelective(CourseEntity record);

    int updateByPrimaryKey(CourseEntity record);

    int removeAndUpdateUserByPrimaryKey(@Param("id") Integer id, @Param("userId") Integer userId, @Param("username") String username);

    List<CourseEntity> listOrderedUpdateTimeByDclNameTeacherId(@Param("name") String name, @Param("teacherId") Integer teacherId);
}