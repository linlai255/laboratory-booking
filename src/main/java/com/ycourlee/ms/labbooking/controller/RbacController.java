package com.ycourlee.ms.labbooking.controller;

import com.ycourlee.ms.labbooking.model.bo.request.*;
import com.ycourlee.ms.labbooking.service.RbacService;
import com.ycourlee.root.core.domain.context.ApiResponse;
import io.swagger.annotations.ApiOperation;
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

    @ApiOperation("用户绑定角色")
    @PostMapping("/user/bind")
    public ApiResponse<Integer> userBind(@Validated @RequestBody UserBindRoleRequest request) {
        return ApiResponse.success(rbacService.userBindRole(request));
    }

    @ApiOperation("保存角色")
    @PostMapping("/role/{name:[\\w]+}")
    public ApiResponse<Integer> roleSave(@PathVariable String name) {
        return ApiResponse.success(rbacService.roleSave(name));
    }

    @ApiOperation("更新角色")
    @PutMapping("/role")
    public ApiResponse<Boolean> roleUpdate(@Validated @RequestBody RoleUpdateRequest request) {
        rbacService.roleUpdate(request);
        return ApiResponse.success(true);
    }

    @ApiOperation("角色绑定资源")
    @PostMapping("/role/bind")
    public ApiResponse<Integer> roleBind(@Validated @RequestBody RoleBindResRequest request) {
        return ApiResponse.success(rbacService.roleBindResource(request));
    }

    @ApiOperation("删除角色")
    @DeleteMapping("/role/{id:[0-9]+}")
    public ApiResponse<Boolean> roleDeleteId(@PathVariable Integer id) {
        rbacService.roleRemove(id);
        return ApiResponse.success(true);
    }

    @ApiOperation("保存菜单")
    @PostMapping("/menu")
    public ApiResponse<Integer> menuSave(@Validated @RequestBody MenuResCreateRequest request) {
        return ApiResponse.success(rbacService.menuSave(request));
    }

    @ApiOperation("更新菜单")
    @PutMapping("/menu")
    public ApiResponse<Boolean> menuUpdate(@Validated @RequestBody MenuResUpdateRequest request) {
        rbacService.menuUpdate(request);
        return ApiResponse.success(true);
    }

    @ApiOperation("菜单绑定资源")
    @PostMapping("/menu/bind")
    public ApiResponse<Integer> menuBindRes(@Validated @RequestBody MenuBindResRequest request) {
        return ApiResponse.success(rbacService.menuBindRes(request));
    }

    @ApiOperation("保存API")
    @PostMapping("/api")
    public ApiResponse<Integer> apiSave(@Validated @RequestBody ApiResCreateRequest request) {
        return ApiResponse.success(rbacService.apiSave(request));
    }

    @ApiOperation("更新API")
    @PutMapping("/api")
    public ApiResponse<Boolean> apiUpdate(@Validated @RequestBody ApiResUpdateRequest request) {
        rbacService.apiUpdate(request);
        return ApiResponse.success(true);
    }

    @ApiOperation("删除资源")
    @DeleteMapping("/resource/delete/{id:[0-9]+}")
    public ApiResponse<Boolean> resourceDelete(@PathVariable Integer id) {
        rbacService.resourceRemove(id);
        return ApiResponse.success(true);
    }
}
