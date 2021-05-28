package com.ycourlee.ms.labbooking.service;

import com.ycourlee.ms.labbooking.model.bo.request.*;

/**
 * @author yongjiang
 */
public interface RbacService {

    Integer userBindRole(UserBindRoleRequest request);

    Integer roleBindResource(RoleBindResRequest request);

    Integer menuBindRes(MenuBindResRequest request);

    Integer menuCreate(MenuResCreateRequest request);

    Integer apiCreate(ApiResCreateRequest request);

    void menuUpdate(MenuResUpdateRequest request);

    void apiUpdate(ApiResUpdateRequest request);

    Integer roleCreate(String name);

    void roleUpdate(RoleUpdateRequest request);

    void roleRemove(Integer id);

    void resourceRemove(Integer id);
}
