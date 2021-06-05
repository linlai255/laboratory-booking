package com.ycourlee.ms.labbooking.controller;

import com.ycourlee.ms.labbooking.model.bo.request.BaseCurrentUserRequest;
import com.ycourlee.ms.labbooking.model.bo.request.CourseSaveRequest;
import com.ycourlee.ms.labbooking.model.bo.request.CourseSearchRequest;
import com.ycourlee.ms.labbooking.model.bo.request.CourseUpdateRequest;
import com.ycourlee.ms.labbooking.model.bo.response.CourseDetailResponse;
import com.ycourlee.ms.labbooking.model.vo.CourseSearchVO;
import com.ycourlee.ms.labbooking.service.CourseService;
import com.ycourlee.root.core.domain.context.ApiResponse;
import com.ycourlee.root.core.dto.PageResponse;
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
        request.fillCurrentUser();
        return ApiResponse.success(courseService.save(request));
    }

    @GetMapping("/{id:[0-9]+}")
    public ApiResponse<CourseDetailResponse> get(@PathVariable Integer id) {
        return ApiResponse.success(courseService.get(id));
    }

    @GetMapping
    public ApiResponse<PageResponse<CourseSearchVO>> search(@RequestBody CourseSearchRequest request) {
        return ApiResponse.success(courseService.search(request));
    }

    @PutMapping
    public ApiResponse<Object> update(@Validated @RequestBody CourseUpdateRequest request) {
        courseService.update(request);
        return ApiResponse.success();
    }

    @DeleteMapping("/{id:[0-9]+}")
    public ApiResponse<Object> delete(@PathVariable Integer id) {
        courseService.delete(id, new BaseCurrentUserRequest().fillCurrentUser());
        return ApiResponse.success();
    }
}
