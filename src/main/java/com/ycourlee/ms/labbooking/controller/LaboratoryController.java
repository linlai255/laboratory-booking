package com.ycourlee.ms.labbooking.controller;

import com.ycourlee.ms.labbooking.model.bo.request.LabSaveRequest;
import com.ycourlee.ms.labbooking.model.bo.request.LabSearchRequest;
import com.ycourlee.ms.labbooking.model.bo.request.LabUpdateRequest;
import com.ycourlee.ms.labbooking.model.bo.response.LabDetailResponse;
import com.ycourlee.ms.labbooking.model.vo.LabSearchVO;
import com.ycourlee.ms.labbooking.service.LaboratoryService;
import com.ycourlee.root.core.domain.context.ApiResponse;
import com.ycourlee.root.core.dto.PageResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @author yongjiang
 */
@Api
@RestController
@RequestMapping("/v1/lab")
public class LaboratoryController {

    @Autowired
    private LaboratoryService laboratoryService;

    @ApiOperation("搜索")
    @GetMapping
    public ApiResponse<PageResponse<LabSearchVO>> search(@RequestBody LabSearchRequest request) {
        return ApiResponse.success(laboratoryService.search(request));
    }

    @ApiOperation("保存")
    @PostMapping
    public ApiResponse<Object> save(@Validated @RequestBody LabSaveRequest request) {
        request.fillCurrentUser();
        return ApiResponse.success(laboratoryService.save(request));
    }

    @ApiOperation("获取某个实验室详情")
    @GetMapping("/{id:[0-9]+}")
    public ApiResponse<LabDetailResponse> get(@PathVariable Integer id) {
        return ApiResponse.success(laboratoryService.get(id));
    }

    @ApiOperation("更新")
    @PutMapping
    public ApiResponse<Object> update(@Validated @RequestBody LabUpdateRequest request) {
        request.fillCurrentUser();
        laboratoryService.update(request);
        return ApiResponse.success();
    }

    @ApiOperation("删除")
    @DeleteMapping("/delete/{id:[0-9]+}")
    public ApiResponse<Object> delete(@PathVariable Integer id) {
        laboratoryService.delete(id);
        return ApiResponse.success();
    }
}
