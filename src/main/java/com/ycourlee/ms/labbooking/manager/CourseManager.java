package com.ycourlee.ms.labbooking.manager;

import com.github.pagehelper.PageHelper;
import com.ycourlee.ms.labbooking.mapper.BookingRecordMapper;
import com.ycourlee.ms.labbooking.mapper.CourseMapper;
import com.ycourlee.ms.labbooking.model.bo.request.CourseSaveRequest;
import com.ycourlee.ms.labbooking.model.bo.request.CourseSearchRequest;
import com.ycourlee.ms.labbooking.model.bo.request.CourseUpdateRequest;
import com.ycourlee.ms.labbooking.model.bo.response.CourseDetailResponse;
import com.ycourlee.ms.labbooking.model.entity.CourseEntity;
import com.ycourlee.ms.labbooking.model.vo.CourseSearchVO;
import com.ycourlee.ms.labbooking.util.BizAssert;
import com.ycourlee.root.util.CollectionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author yongjiang
 */
@Component
public class CourseManager {

    @Autowired
    private CourseMapper        courseMapper;
    @Autowired
    private BookingRecordMapper bookingRecordMapper;

    public Integer save(CourseEntity entity) {
        return courseMapper.updateByPrimaryKeySelective(entity);
    }

    public void update(CourseEntity entity) {
        BizAssert.impossible(entity.getId() == null);
        courseMapper.updateByPrimaryKeySelective(entity);
    }

    public CourseEntity get(Integer id) {
        return courseMapper.selectByPrimaryKey(id);
    }

    public void remove(Integer id, Integer userId, String username) {
        BizAssert.impossible(id == null);
        courseMapper.removeAndUpdateUserByPrimaryKey(id, userId, username);
    }

    public boolean haveBookingRecord(Integer id) {
        return bookingRecordMapper.countByCourseId(id) > 0;
    }

    public CourseDetailResponse buildCourseDetailResponse(CourseEntity entity) {
        CourseDetailResponse response = new CourseDetailResponse();
        response.setId(entity.getId());
        response.setName(entity.getName());
        response.setTeacherId(entity.getTeacherId());
        response.setStudentAmount(entity.getStudentAmount());
        response.setClassHours(entity.getClassHours());
        response.setMemo(entity.getMemo());
        response.setUpdateTime(entity.getUpdateTime());
        response.setUpdateUserId(entity.getUpdateUserId());
        response.setUpdateUsername(entity.getUpdateUsername());
        return response;
    }

    public CourseEntity buildCourseEntity(CourseSaveRequest request) {
        CourseEntity entity = new CourseEntity();
        entity.setName(request.getName());
        entity.setTeacherId(request.getTeacherId());
        entity.setStudentAmount(request.getStudentAmount());
        entity.setClassHours(request.getClassHours());
        entity.setMemo(request.getMemo());
        entity.setCreateUserId(request.getUserId());
        entity.setCreateUsername(request.getUsername());
        entity.setUpdateUserId(request.getUserId());
        entity.setUpdateUsername(request.getUsername());
        return entity;
    }

    public CourseEntity buildCourseEntity(CourseUpdateRequest request) {
        CourseEntity entity = new CourseEntity();
        entity.setId(request.getId());
        entity.setName(request.getName());
        entity.setTeacherId(request.getTeacherId());
        entity.setStudentAmount(request.getStudentAmount());
        entity.setClassHours(request.getClassHours());
        entity.setMemo(request.getMemo());
        entity.setUpdateUserId(request.getUserId());
        entity.setUpdateUsername(request.getUsername());
        return entity;
    }

    public List<CourseEntity> list(CourseSearchRequest request, boolean needPaging) {
        if (needPaging) {
            PageHelper.startPage(request.getPage(), request.getPageSize());
        }
        return courseMapper.listOrderedUpdateTimeByDclName(request.getName());
    }

    public List<CourseSearchVO> buildSearchVoList(List<CourseEntity> entityList) {
        if (CollectionUtil.isEmpty(entityList)) {
            return Collections.emptyList();
        }
        List<CourseSearchVO> courseVoList = new ArrayList<>(entityList.size());
        for (CourseEntity entity : entityList) {
            CourseSearchVO courseVO = new CourseSearchVO();
            courseVO.setId(entity.getId());
            courseVO.setName(entity.getName());
            courseVO.setStudentAmount(entity.getStudentAmount());
            courseVO.setClassHours(entity.getClassHours());
            courseVO.setMemo(entity.getMemo());
            courseVO.setUpdateTime(entity.getUpdateTime());
            courseVO.setUpdateUserId(entity.getUpdateUserId());
            courseVO.setUpdateUsername(entity.getUpdateUsername());
            courseVoList.add(courseVO);
        }
        return courseVoList;
    }
}
