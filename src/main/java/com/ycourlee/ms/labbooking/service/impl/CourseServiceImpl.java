package com.ycourlee.ms.labbooking.service.impl;

import com.ycourlee.ms.labbooking.manager.CourseManager;
import com.ycourlee.ms.labbooking.model.bo.response.CourseDetailResponse;
import com.ycourlee.ms.labbooking.model.entity.CourseEntity;
import com.ycourlee.ms.labbooking.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author yongjiang
 */
@Service
public class CourseServiceImpl implements CourseService {

    @Autowired
    private CourseManager courseManager;

    @Override
    public CourseDetailResponse get(Integer id) {
        return buildCourseDetailResponse(courseManager.get(id));
    }

    private CourseDetailResponse buildCourseDetailResponse(CourseEntity entity) {
        return null;
    }
}
