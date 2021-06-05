package com.ycourlee.ms.labbooking.mapper;

import com.ycourlee.ms.labbooking.model.entity.LabEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author yongjiang
 */
public interface LabMapper {

    int deleteByPrimaryKey(Integer id);

    int insert(LabEntity record);

    int insertSelective(LabEntity record);

    LabEntity selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(LabEntity record);

    int updateByPrimaryKey(LabEntity record);

    List<LabEntity> listOrderedUpdateTimeByDclName(@Param("name") String name);

    int removeByPrimaryKey(Integer id);
}