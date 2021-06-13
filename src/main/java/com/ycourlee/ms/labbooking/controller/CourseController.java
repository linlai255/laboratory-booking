package com.ycourlee.ms.labbooking.controller;

import com.ycourlee.ms.labbooking.auth.Context;
import com.ycourlee.ms.labbooking.enums.EAccountType;
import com.ycourlee.ms.labbooking.model.bo.request.BaseCurrentUserRequest;
import com.ycourlee.ms.labbooking.model.bo.request.CourseSaveRequest;
import com.ycourlee.ms.labbooking.model.bo.request.CourseSearchRequest;
import com.ycourlee.ms.labbooking.model.bo.request.CourseUpdateRequest;
import com.ycourlee.ms.labbooking.model.bo.response.CourseDetailResponse;
import com.ycourlee.ms.labbooking.model.vo.CourseSearchVO;
import com.ycourlee.ms.labbooking.service.CourseService;
import com.ycourlee.root.core.domain.context.ApiResponse;
import com.ycourlee.root.core.dto.PageResponse;
import io.swagger.annotations.ApiOperation;
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

    @ApiOperation("保存课程")
    @PostMapping
    public ApiResponse<Object> save(@Validated @RequestBody CourseSaveRequest request) {
        request.fillCurrentUser();
        return ApiResponse.success(courseService.save(request));
    }

    @ApiOperation("获取某课程详细")
    @GetMapping("/{id:[0-9]+}")
    public ApiResponse<CourseDetailResponse> get(@PathVariable Integer id) {
        return ApiResponse.success(courseService.get(id));
    }

    @ApiOperation("搜索")
    @GetMapping
    public ApiResponse<PageResponse<CourseSearchVO>> search(CourseSearchRequest request) {
        if (EAccountType.TEACHER.getCode().equals(Context.getType())) {
            request.setTeacherId(Context.getRefId());
        }
        return ApiResponse.success(courseService.search(request));
    }

    @ApiOperation("更新")
    @PutMapping
    public ApiResponse<Object> update(@Validated @RequestBody CourseUpdateRequest request) {
        courseService.update(request);
        return ApiResponse.success();
    }

    @ApiOperation("删除")
    @DeleteMapping("/{id:[0-9]+}")
    public ApiResponse<Object> delete(@PathVariable Integer id) {
        courseService.delete(id, new BaseCurrentUserRequest().fillCurrentUser());
        return ApiResponse.success();
    }
}
