package com.ycourlee.ms.labbooking.controller;

import com.ycourlee.ms.labbooking.model.bo.request.LabCreateRequest;
import com.ycourlee.ms.labbooking.service.LaboratoryService;
import com.ycourlee.root.core.domain.context.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @author yongjiang
 */
@RestController
@RequestMapping("/v1/lab")
public class LaboratoryController {

    @Autowired
    private LaboratoryService laboratoryService;

    @PostMapping("/create")
    public ApiResponse<Object> create(@Validated @RequestBody LabCreateRequest request) {
        request.fillCurrentUser();
        return ApiResponse.success(laboratoryService.createLab(request));
    }

    @PostMapping("/delete/{id:[0-9]+}")
    public ApiResponse<Object> delete(@PathVariable Integer id) {
        return ApiResponse.success(laboratoryService.deleteLab(id));
    }
}
