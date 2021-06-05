package com.ycourlee.ms.labbooking.manager;

import com.ycourlee.ms.labbooking.mapper.CourseMapper;
import com.ycourlee.ms.labbooking.model.entity.CourseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author yongjiang
 */
@Component
public class CourseManager {

    @Autowired
    private CourseMapper courseMapper;

    public CourseEntity get(Integer id) {
        return courseMapper.selectByPrimaryKey(id);
    }
}
