package com.ycourlee.ms.labbooking.service;

import com.ycourlee.ms.labbooking.model.bo.request.*;

/**
 * @author yongjiang
 */
public interface RbacService {

    Integer userBindRole(UserBindRoleRequest request);

    Integer roleBindResource(RoleBindResRequest request);

    Integer menuBindRes(MenuBindResRequest request);

    Integer menuSave(MenuResCreateRequest request);

    Integer apiSave(ApiResCreateRequest request);

    void menuUpdate(MenuResUpdateRequest request);

    void apiUpdate(ApiResUpdateRequest request);

    Integer roleSave(String name);

    void roleUpdate(RoleUpdateRequest request);

    void roleRemove(Integer id);

    void resourceRemove(Integer id);
}
