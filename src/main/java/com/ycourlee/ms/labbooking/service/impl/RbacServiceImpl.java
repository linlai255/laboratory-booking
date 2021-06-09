package com.ycourlee.ms.labbooking.service.impl;

import com.github.pagehelper.PageInfo;
import com.ycourlee.ms.labbooking.exception.error.Errors;
import com.ycourlee.ms.labbooking.manager.AccountCacheManager;
import com.ycourlee.ms.labbooking.manager.RbacManager;
import com.ycourlee.ms.labbooking.model.bo.request.*;
import com.ycourlee.ms.labbooking.model.entity.ResourceEntity;
import com.ycourlee.ms.labbooking.model.entity.RoleEntity;
import com.ycourlee.ms.labbooking.model.entity.UserEntity;
import com.ycourlee.ms.labbooking.model.vo.MenuTreeVO;
import com.ycourlee.ms.labbooking.model.vo.ResourceApiVO;
import com.ycourlee.ms.labbooking.service.RbacService;
import com.ycourlee.ms.labbooking.util.BizAssert;
import com.ycourlee.root.core.dto.PageResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author yongjiang
 */
@Service
public class RbacServiceImpl implements RbacService {

    @Autowired
    private RbacManager         rbacManager;
    @Autowired
    private AccountCacheManager accountCacheManager;

    @Override
    public Integer userBindRole(UserBindRoleRequest request) {
        UserEntity user = rbacManager.getUserNoDel(request.getUserId());
        BizAssert.that(user != null, Errors.USER_NOT_EXISTS);
        BizAssert.that(rbacManager.countRole(request.getRoleIdSet()) == request.getRoleIdSet().size(), Errors.ROLE_NEED_BIND_NOT_EXISTS);
        int rows = rbacManager.bindRolesToUser(user.getId(), request.getRoleIdSet());
        accountCacheManager.expireUserContextCache(request.getUserId());
        return rows;
    }

    @Override
    public Integer roleBindResource(RoleBindResRequest request) {
        RoleEntity role = rbacManager.getRoleNoDel(request.getRoleId());
        BizAssert.that(role != null, Errors.ROLE_NOT_EXISTS);
        List<ResourceEntity> roleVOList = rbacManager.listResource(request.getResIdSet());
        BizAssert.that(roleVOList.size() == request.getResIdSet().size(), Errors.RESOURCE_NEED_BIND_NOT_EXISTS);
        return rbacManager.bindResToRole(role.getId(), request.getResIdSet());
    }

    @Override
    public Integer menuBindRes(MenuBindResRequest request) {
        return rbacManager.menuBindRes(request.getMenuId(), request.getSubResIdSet());
    }

    @Override
    public Integer menuSave(MenuResSaveRequest request) {
        return rbacManager.saveMenu(request);
    }

    @Override
    public List<MenuTreeVO> menuTree() {
        return rbacManager.wholeMenuTree();
    }

    @Override
    public Integer apiSave(ApiResSaveRequest request) {
        if (request.getContainPathVar() == null) {
            request.setContainPathVar(0);
        }
        return rbacManager.saveApi(request);
    }

    @Override
    public void menuUpdate(MenuResUpdateRequest request) {
        rbacManager.updateMenu(request);
    }

    @Override
    public PageResponse<ResourceApiVO> apiSearch(ApiSearchRequest request) {
        List<ResourceEntity> entityList = rbacManager.listResourceApi(request, true);
        return new PageResponse<>(request.getPage(), request.getPageSize(), new PageInfo<>().getTotal(), rbacManager.buildApiSearchResponse(entityList));
    }

    @Override
    public void apiUpdate(ApiResUpdateRequest request) {
        rbacManager.updateApi(request);
    }

    @Override
    public Integer roleSave(String name) {
        return rbacManager.saveRole(name);
    }

    @Override
    public void roleUpdate(RoleUpdateRequest request) {
        rbacManager.updateRoleName(request.getId(), request.getName());
    }

    @Override
    public void roleRemove(Integer id) {
        rbacManager.removeRoleAbout(id);
    }

    @Override
    public void resourceRemove(Integer id) {
        rbacManager.removeResourceAbout(id);
    }
}
