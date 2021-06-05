package com.ycourlee.ms.labbooking.controller;

import com.ycourlee.ms.labbooking.model.bo.request.CourseSaveRequest;
import com.ycourlee.ms.labbooking.model.bo.request.CourseUpdateRequest;
import com.ycourlee.ms.labbooking.model.bo.response.CourseDetailResponse;
import com.ycourlee.ms.labbooking.service.CourseService;
import com.ycourlee.root.core.domain.context.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @author yongjiang
 */
@RestController
@RequestMapping("/v1/course")
public class CourseController {

    @Autowired
    private CourseService courseService;

    @PostMapping
    public ApiResponse<Object> save(@Validated @RequestBody CourseSaveRequest request) {

        return null;
    }

    @GetMapping("/{id:[0-9]+}")
    public ApiResponse<CourseDetailResponse> get(@PathVariable Integer id) {
        return ApiResponse.success(courseService.get(id));
    }

    @PutMapping
    public ApiResponse<Object> update(@Validated @RequestBody CourseUpdateRequest request) {
        return null;
    }
}
