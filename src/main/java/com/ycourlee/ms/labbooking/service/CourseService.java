package com.ycourlee.ms.labbooking.service;

import com.ycourlee.ms.labbooking.model.bo.request.BaseCurrentUserRequest;
import com.ycourlee.ms.labbooking.model.bo.request.CourseSaveRequest;
import com.ycourlee.ms.labbooking.model.bo.request.CourseSearchRequest;
import com.ycourlee.ms.labbooking.model.bo.request.CourseUpdateRequest;
import com.ycourlee.ms.labbooking.model.bo.response.CourseDetailResponse;
import com.ycourlee.ms.labbooking.model.vo.CourseSearchVO;
import com.ycourlee.root.core.dto.PageResponse;

/**
 * @author yongjiang
 */
public interface CourseService {

    Integer save(CourseSaveRequest request);

    void update(CourseUpdateRequest request);

    PageResponse<CourseSearchVO> search(CourseSearchRequest request);

    CourseDetailResponse get(Integer id);

    void delete(Integer id, BaseCurrentUserRequest request);
}
