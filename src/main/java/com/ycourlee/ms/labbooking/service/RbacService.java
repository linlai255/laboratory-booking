package com.ycourlee.ms.labbooking.service;

import com.ycourlee.ms.labbooking.model.bo.request.*;
import com.ycourlee.ms.labbooking.model.vo.MenuTreeVO;
import com.ycourlee.ms.labbooking.model.vo.ResourceApiVO;
import com.ycourlee.root.core.dto.PageResponse;

import java.util.List;

/**
 * @author yongjiang
 */
public interface RbacService {

    Integer userBindRole(UserBindRoleRequest request);

    Integer roleBindResource(RoleBindResRequest request);

    Integer menuBindRes(MenuBindResRequest request);

    Integer menuSave(MenuResSaveRequest request);

    List<MenuTreeVO> menuTree();

    void menuUpdate(MenuResUpdateRequest request);

    PageResponse<ResourceApiVO> apiSearch(ApiSearchRequest request);

    Integer apiSave(ApiResSaveRequest request);

    void apiUpdate(ApiResUpdateRequest request);

    Integer roleSave(String name);

    void roleUpdate(RoleUpdateRequest request);

    void roleRemove(Integer id);

    void resourceRemove(Integer id);
}
