package com.ycourlee.ms.labbooking.controller;

import com.ycourlee.ms.labbooking.model.bo.request.*;
import com.ycourlee.ms.labbooking.service.RbacService;
import com.ycourlee.root.core.domain.context.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @author yongjiang
 */
@RestController
@RequestMapping("/v1/rbac")
public class RbacController {

    @Autowired
    private RbacService rbacService;

    @PostMapping("/user/bind")
    public ApiResponse<Integer> userBind(@Validated @RequestBody UserBindRoleRequest request) {
        return ApiResponse.success(rbacService.userBindRole(request));
    }

    @PostMapping("/role/create/{name:[\\w]+}")
    public ApiResponse<Integer> roleCreate(@PathVariable String name) {
        return ApiResponse.success(rbacService.roleCreate(name));
    }

    @PostMapping("/role/update")
    public ApiResponse<Boolean> roleUpdate(@Validated @RequestBody RoleUpdateRequest request) {
        rbacService.roleUpdate(request);
        return ApiResponse.success(true);
    }

    @PostMapping("/role/bind")
    public ApiResponse<Integer> roleBind(@Validated @RequestBody RoleBindResRequest request) {
        return ApiResponse.success(rbacService.roleBindResource(request));
    }

    @PostMapping("/role/delete/{id:[0-9]+}")
    public ApiResponse<Boolean> roleDeleteId(@PathVariable Integer id) {
        rbacService.roleRemove(id);
        return ApiResponse.success(true);
    }

    @PostMapping("/menu/create")
    public ApiResponse<Integer> menuCreate(@Validated @RequestBody MenuResCreateRequest request) {
        return ApiResponse.success(rbacService.menuCreate(request));
    }

    @PostMapping("/menu/update")
    public ApiResponse<Boolean> menuUpdate(@Validated @RequestBody MenuResUpdateRequest request) {
        rbacService.menuUpdate(request);
        return ApiResponse.success(true);
    }

    @PostMapping("/menu/bind")
    public ApiResponse<Integer> menuBindRes(@Validated @RequestBody MenuBindResRequest request) {
        return ApiResponse.success(rbacService.menuBindRes(request));
    }

    @PostMapping("/api/create")
    public ApiResponse<Integer> apiCreate(@Validated @RequestBody ApiResCreateRequest request) {
        if (request.getContainPathVar() == null) {
            request.setContainPathVar(0);
        }
        return ApiResponse.success(rbacService.apiCreate(request));
    }

    @PostMapping("/api/update")
    public ApiResponse<Boolean> apiUpdate(@Validated @RequestBody ApiResUpdateRequest request) {
        rbacService.apiUpdate(request);
        return ApiResponse.success(true);
    }

    @PostMapping("/resource/delete/{id:[0-9]+}")
    public ApiResponse<Boolean> resourceDelete(@PathVariable Integer id) {
        rbacService.resourceRemove(id);
        return ApiResponse.success(true);
    }
}
