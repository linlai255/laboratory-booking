package com.ycourlee.ms.labbooking.service.impl;

import com.github.pagehelper.PageInfo;
import com.ycourlee.ms.labbooking.exception.error.Errors;
import com.ycourlee.ms.labbooking.manager.CourseManager;
import com.ycourlee.ms.labbooking.manager.TeacherManager;
import com.ycourlee.ms.labbooking.model.bo.request.BaseCurrentUserRequest;
import com.ycourlee.ms.labbooking.model.bo.request.CourseSaveRequest;
import com.ycourlee.ms.labbooking.model.bo.request.CourseSearchRequest;
import com.ycourlee.ms.labbooking.model.bo.request.CourseUpdateRequest;
import com.ycourlee.ms.labbooking.model.bo.response.CourseDetailResponse;
import com.ycourlee.ms.labbooking.model.entity.CourseEntity;
import com.ycourlee.ms.labbooking.model.vo.CourseSearchVO;
import com.ycourlee.ms.labbooking.service.CourseService;
import com.ycourlee.ms.labbooking.util.BizAssert;
import com.ycourlee.root.core.dto.PageResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author yongjiang
 */
@Service
public class CourseServiceImpl implements CourseService {

    @Autowired
    private CourseManager  courseManager;
    @Autowired
    private TeacherManager teacherManager;


    @Override
    public Integer save(CourseSaveRequest request) {
        return courseManager.save(courseManager.buildCourseEntity(request));
    }

    @Override
    public void update(CourseUpdateRequest request) {
        courseManager.update(courseManager.buildCourseEntity(request));
    }

    @Override
    public PageResponse<CourseSearchVO> search(CourseSearchRequest request) {
        List<CourseEntity> entityList = courseManager.list(request, true);
        return new PageResponse<>(request.getPage(), request.getPageSize(), new PageInfo<>(entityList).getTotal(), courseManager.buildSearchVoList(entityList));
    }

    @Override
    public CourseDetailResponse get(Integer id) {
        CourseDetailResponse response = courseManager.buildCourseDetailResponse(courseManager.get(id));
        response.setTeacherName(teacherManager.nameOf(response.getTeacherId()));
        return response;
    }

    @Override
    public void delete(Integer id, BaseCurrentUserRequest request) {
        CourseEntity courseEntity = courseManager.get(id);
        BizAssert.impossible(courseEntity == null, Errors.COURSE_NOT_FOUND);
        BizAssert.impossible(courseManager.haveBookingRecord(id));
        courseManager.remove(id, request.getUserId(), request.getUsername());
    }
}
