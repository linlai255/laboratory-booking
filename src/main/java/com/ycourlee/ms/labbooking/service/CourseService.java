package com.ycourlee.ms.labbooking.service;

import com.ycourlee.ms.labbooking.model.bo.response.CourseDetailResponse;

/**
 * @author yongjiang
 */
public interface CourseService {

    CourseDetailResponse get(Integer id);
}
