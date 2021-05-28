package com.ycourlee.ms.labbooking.manager;

import com.ycourlee.ms.labbooking.config.properties.DefaultRoleProperties;
import com.ycourlee.ms.labbooking.enums.EAccountType;
import com.ycourlee.ms.labbooking.enums.EDigit;
import com.ycourlee.ms.labbooking.enums.EResourceType;
import com.ycourlee.ms.labbooking.exception.error.Errors;
import com.ycourlee.ms.labbooking.mapper.*;
import com.ycourlee.ms.labbooking.model.bo.request.ApiResCreateRequest;
import com.ycourlee.ms.labbooking.model.bo.request.ApiResUpdateRequest;
import com.ycourlee.ms.labbooking.model.bo.request.MenuResCreateRequest;
import com.ycourlee.ms.labbooking.model.bo.request.MenuResUpdateRequest;
import com.ycourlee.ms.labbooking.model.entity.*;
import com.ycourlee.ms.labbooking.util.BizAssert;
import com.ycourlee.root.util.CollectionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.*;

/**
 * @author yongjiang
 */
@Component
public class RbacManager {

    @Autowired
    private AdminMapper           adminMapper;
    @Autowired
    private TeacherMapper         teacherMapper;
    @Autowired
    private UserMapper            userMapper;
    @Autowired
    private RoleMapper            roleMapper;
    @Autowired
    private ResourceMapper        resourceMapper;
    @Autowired
    private UserRoleMapper        userRoleMapper;
    @Autowired
    private RoleResourceMapper    roleResourceMapper;
    @Resource(name = "adminDefaultRole")
    private DefaultRoleProperties defaultAdminRoleId;
    @Resource(name = "teacherDefaultRole")
    private DefaultRoleProperties defaultTeacherRoleId;

    @Transactional(rollbackFor = Exception.class)
    public void saveUser(Integer type, String phone, String password) {
        int primaryId;
        if (EAccountType.TEACHER.getCode() == type) {
            primaryId = createTeacher(phone);
        } else if (EAccountType.ADMINISTRATOR.getCode() == type) {
            primaryId = createAdmin(phone);
        } else {
            throw new IllegalArgumentException();
        }
        UserEntity user = new UserEntity();
        user.setPhone(phone);
        user.setPassword(password);
        user.setRefId(primaryId);
        user.setType(type.byteValue());
        userMapper.insertSelective(user);

        if (EAccountType.TEACHER.getCode() == type) {
            BizAssert.that(CollectionUtil.isNotEmpty(defaultAdminRoleId.getDefaultRoleId()), Errors.INTERNAL_CONFIGURATION_ERROR);
            bindRolesToUser(user.getId(), defaultAdminRoleId.getDefaultRoleId());
        } else {
            BizAssert.that(CollectionUtil.isNotEmpty(defaultTeacherRoleId.getDefaultRoleId()), Errors.INTERNAL_CONFIGURATION_ERROR);
            bindRolesToUser(user.getId(), defaultTeacherRoleId.getDefaultRoleId());
        }
    }

    public Integer saveRole(String name) {
        RoleEntity record = new RoleEntity();
        record.setName(name);
        roleMapper.insertSelective(record);
        return record.getId();
    }

    public List<RoleEntity> listRole(Integer userId) {


        return null;
    }

    public void updateRoleName(int id, String name) {
        RoleEntity record = new RoleEntity();
        record.setId(id);
        record.setName(name);
        roleMapper.updateByPrimaryKeySelective(record);
    }

    @Transactional(rollbackFor = Exception.class)
    public void removeRoleAbout(Integer id) {
        removeRole(id);
        removeUserRole(id);
        removeRoleResource(id);
    }

    public int bindRolesToUser(int userId, Collection<Integer> roleIds) {
        if (roleIds.isEmpty()) {
            return 0;
        }
        List<Integer> alreadyBindRoleIdList = userRoleMapper.listRoleIdByUserId(userId);
        BizAssert.impossible(alreadyBindRoleIdList.removeAll(roleIds), Errors.HAVE_ALREADY_BOUND_ROLE);

        List<UserRoleEntity> userRoleEntityList = new ArrayList<>(roleIds.size());
        for (Integer roleId : roleIds) {
            userRoleEntityList.add(buildBaseUserRoleEntity(userId, roleId));
        }
        if (userRoleEntityList.size() == 1) {
            return userRoleMapper.insertSelective(userRoleEntityList.get(0));
        }
        return userRoleMapper.batchInsertFcl(userRoleEntityList);
    }

    public int bindResToRole(int roleId, Collection<Integer> resIds) {
        if (resIds.isEmpty()) {
            return 0;
        }
        List<Integer> alreadyBindResIdList = roleResourceMapper.listResIdByRoleId(roleId);
        BizAssert.impossible(alreadyBindResIdList.removeAll(resIds), Errors.HAVE_ALREADY_BOUND_RESOURCE);

        List<RoleResourceEntity> roleResourceEntityList = new ArrayList<>(resIds.size());
        for (Integer resId : resIds) {
            RoleResourceEntity roleResourceEntity = new RoleResourceEntity();
            roleResourceEntity.setRoleId(roleId);
            roleResourceEntity.setResourceId(resId);
            roleResourceEntityList.add(roleResourceEntity);
        }
        if (roleResourceEntityList.size() == 1) {
            return roleResourceMapper.insertSelective(roleResourceEntityList.get(0));
        }
        return roleResourceMapper.batchInsertFcl(roleResourceEntityList);
    }

    public Integer saveMenu(MenuResCreateRequest request) {
        ResourceEntity record = new ResourceEntity();
        record.setType(EResourceType.MENU.getCode());
        record.setName(request.getName());
        record.setParentId(request.getParentId());
        record.setMemo(request.getMemo());
        resourceMapper.insertSelective(record);
        return record.getId();
    }

    public Integer saveApi(ApiResCreateRequest request) {
        ResourceEntity record = new ResourceEntity();
        record.setType(EResourceType.API.getCode());
        record.setPath(request.getPath());
        record.setContainPathVar(request.getContainPathVar().equals(EDigit.ONE.getCode()));
        record.setName(request.getName());
        record.setMemo(request.getMemo());
        resourceMapper.insertSelective(record);
        return record.getId();
    }

    public void updateMenu(MenuResUpdateRequest request) {
        ResourceEntity record = new ResourceEntity();
        record.setId(request.getId());
        record.setParentId(request.getParentId());
        record.setName(request.getName());
        record.setRoute(request.getRoute());
        record.setMemo(request.getMemo());
        resourceMapper.updateByPrimaryKeySelective(record);
    }

    public void updateApi(ApiResUpdateRequest request) {
        ResourceEntity record = new ResourceEntity();
        record.setId(request.getId());
        record.setType(EResourceType.API.getCode());
        record.setPath(request.getPath());
        record.setContainPathVar(request.getContainPathVar().equals(EDigit.ONE.getCode()));
        record.setName(request.getName());
        record.setMemo(request.getMemo());
        resourceMapper.updateByPrimaryKeySelective(record);
    }

    public int menuBindRes(int resId, Set<Integer> subResIdSet) {
        BizAssert.that(resId > 0, Errors.RESOURCE_ID_INVALID);
        if (CollectionUtil.isEmpty(subResIdSet)) {
            return 0;
        }
        checkResExistAndNoBindWithTheResBeforeNow(resId, subResIdSet);
        return resourceMapper.batchUpdateParentIdByIdCollection(resId, subResIdSet);
    }

    private void checkResExistAndNoBindWithTheResBeforeNow(int resId, Set<Integer> subResIdSet) {
        ResourceEntity resourceEntity = resourceMapper.selectByPrimaryKey(resId);
        BizAssert.that(resourceEntity != null, Errors.RESOURCE_NOT_EXISTS);
        BizAssert.that(subResIdSet.size() == countResource(subResIdSet), Errors.RESOURCE_NEED_BIND_NOT_EXISTS);
        List<Integer> alreadyBindResIdList = resourceMapper.listResIdByParentResId(resId);
        BizAssert.impossible(alreadyBindResIdList.removeAll(subResIdSet), Errors.HAVE_ALREADY_BOUND_RESOURCE);
    }

    public int countResource(Set<Integer> subResIdSet) {
        return resourceMapper.countByIdCollection(subResIdSet);
    }

    public void removeResourceAbout(int id) {
        resourceMapper.removeFromResourceAndParentResource(id);
    }

    /*----------BELOW---------- base ----------BELOW----------*/

    public void removeRole(int roleId) {
        roleMapper.deleteByPrimaryKey(roleId);
    }

    public void removeUserRole(int roleId) {
        userRoleMapper.removeByRoleId(roleId);
    }

    public void removeRoleResource(int roleId) {
        roleResourceMapper.removeByRoleId(roleId);
    }

    public UserEntity getUser(int userId) {
        return userMapper.selectByPrimaryKey(userId);
    }

    public List<RoleEntity> listRole(Collection<Integer> roleIdSet) {
        if (CollectionUtil.isEmpty(roleIdSet)) {
            return Collections.emptyList();
        }
        return roleMapper.listByIdCollection(roleIdSet);
    }

    public List<ResourceEntity> listResource(Collection<Integer> resIdSet) {
        if (CollectionUtil.isEmpty(resIdSet)) {
            return Collections.emptyList();
        }
        return resourceMapper.listByIdCollection(resIdSet);
    }

    public RoleEntity getRole(int roleId) {
        return roleMapper.selectByPrimaryKey(roleId);
    }

    public int createAdmin(String phone) {
        TeacherEntity entity = new TeacherEntity();
        entity.setNickname("教师" + phone);
        teacherMapper.insertSelective(entity);
        return entity.getId();
    }

    public int createTeacher(String phone) {
        AdminEntity entity = new AdminEntity();
        entity.setNickname("管理员" + phone);
        adminMapper.insertSelective(entity);
        return entity.getId();
    }

    public int countRole(Collection<Integer> roleIdCollection) {
        return roleMapper.countByIdCollection(roleIdCollection);
    }

    public UserRoleEntity buildBaseUserRoleEntity(Integer userId, Integer roleId) {
        UserRoleEntity userRoleEntity = new UserRoleEntity();
        userRoleEntity.setUserId(userId);
        userRoleEntity.setRoleId(roleId);
        return userRoleEntity;
    }
}
