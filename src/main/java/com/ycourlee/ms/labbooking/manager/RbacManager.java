package com.ycourlee.ms.labbooking.manager;

import com.github.pagehelper.PageHelper;
import com.ycourlee.ms.labbooking.config.properties.LabDefaultRoleProperties;
import com.ycourlee.ms.labbooking.enums.EAccountType;
import com.ycourlee.ms.labbooking.enums.EContainPathVar;
import com.ycourlee.ms.labbooking.enums.EResourceType;
import com.ycourlee.ms.labbooking.exception.error.Errors;
import com.ycourlee.ms.labbooking.mapper.*;
import com.ycourlee.ms.labbooking.model.bo.AdminBO;
import com.ycourlee.ms.labbooking.model.bo.TeacherBO;
import com.ycourlee.ms.labbooking.model.bo.request.*;
import com.ycourlee.ms.labbooking.model.entity.*;
import com.ycourlee.ms.labbooking.model.vo.CodeNameVO;
import com.ycourlee.ms.labbooking.model.vo.ResourceApiVO;
import com.ycourlee.ms.labbooking.model.vo.MenuTreeVO;
import com.ycourlee.ms.labbooking.util.BizAssert;
import com.ycourlee.ms.labbooking.util.KeyPool;
import com.ycourlee.root.core.context.BusinessException;
import com.ycourlee.root.util.CollectionUtil;
import com.ycourlee.root.util.RandomUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author yongjiang
 */
@Component
public class RbacManager {

    @Autowired
    private AdminMapper              adminMapper;
    @Autowired
    private TeacherMapper            teacherMapper;
    @Autowired
    private UserMapper               userMapper;
    @Autowired
    private RoleMapper               roleMapper;
    @Autowired
    private ResourceMapper           resourceMapper;
    @Autowired
    private UserRoleMapper           userRoleMapper;
    @Autowired
    private RoleResourceMapper       roleResourceMapper;
    @Resource(name = "adminDefaultRole")
    private LabDefaultRoleProperties defaultAdminRoleId;
    @Resource(name = "teacherDefaultRole")
    private LabDefaultRoleProperties defaultTeacherRoleId;

    @Transactional(rollbackFor = Exception.class)
    public void saveUser(int type, String phone, String email, String password) {
        int primaryId;
        if (EAccountType.TEACHER.getCode() == type) {
            primaryId = createTeacher();
        } else if (EAccountType.ADMINISTRATOR.getCode() == type) {
            primaryId = createAdmin();
        } else {
            throw new IllegalArgumentException();
        }
        UserEntity user = new UserEntity();
        user.setPhone(phone);
        user.setEmail(email);
        user.setPassword(password);
        user.setRefId(primaryId);
        user.setType(type);
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

    public List<RoleEntity> listRole(int userId) {
        List<UserRoleEntity> userRoleEntityList = userRoleMapper.listByUserId(userId);
        List<Integer> roleIdList = userRoleEntityList.stream().map(UserRoleEntity::getRoleId).collect(Collectors.toList());
        return roleMapper.listByIdCollection(roleIdList);
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

    public Integer saveMenu(MenuResSaveRequest request) {
        ResourceEntity record = new ResourceEntity();
        record.setType(EResourceType.MENU.getCode());
        record.setName(request.getName());
        record.setParentId(request.getParentId());
        record.setMemo(request.getMemo());
        resourceMapper.insertSelective(record);
        return record.getId();
    }

    public Integer saveApi(ApiResSaveRequest request) {
        ResourceEntity record = new ResourceEntity();
        record.setType(EResourceType.API.getCode());
        record.setPath(request.getPath());
        record.setContainPathVar(request.getContainPathVar());
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
        record.setName(request.getName());
        record.setPath(request.getPath());
        record.setParentId(request.getParentId());
        record.setContainPathVar(request.getContainPathVar());
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

    public UserEntity getUserNoDel(Integer userId) {
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

    public List<ResourceEntity> listResourceApi(ApiSearchRequest request, boolean needPaging) {
        if (needPaging) {
            PageHelper.startPage(request.getPage(), request.getPageSize());
        }
        return resourceMapper.listApiByDclNamePathContainPathVar(request.getName(), request.getPath(), request.getContainPathVar());
    }

    public List<ResourceEntity> listResource(@Nullable Integer type, @Nullable Integer parentId) {
        return resourceMapper.listOrderedSortByDclTypeParentId(type, parentId);
    }

    public RoleEntity getRole(int roleId) {
        return roleMapper.selectByPrimaryKey(roleId);
    }

    public RoleEntity getRoleNoDel(int roleId) {
        return roleMapper.selectByPrimaryKey(roleId);
    }

    public int createTeacher() {
        TeacherEntity entity = new TeacherEntity();
        entity.setNickname("教师" + RandomUtil.RANDOM.nextInt(1000));
        teacherMapper.insertSelective(entity);
        return entity.getId();
    }

    public int createAdmin() {
        AdminEntity entity = new AdminEntity();
        entity.setNickname("管理员" + RandomUtil.RANDOM.nextInt(1000));
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

    public AdminBO getAdminBo(int id) {
        AdminEntity adminEntity = adminMapper.selectByPrimaryKey(id);
        if (adminEntity == null) {
            return null;
        }
        AdminBO adminBO = new AdminBO();
        adminBO.setId(adminEntity.getId());
        adminBO.setName(adminEntity.getName());
        adminBO.setNickname(adminEntity.getNickname());
        return adminBO;
    }

    public TeacherBO getTeacherBo(int id) {
        TeacherEntity teacherEntity = teacherMapper.selectByPrimaryKey(id);
        if (teacherEntity == null) {
            return null;
        }
        TeacherBO teacherBO = new TeacherBO();
        teacherBO.setId(teacherEntity.getId());
        teacherBO.setName(teacherEntity.getName());
        teacherBO.setNickname(teacherEntity.getNickname());
        teacherBO.setDepartment(teacherBO.getDepartment());
        teacherBO.setAcademy(teacherBO.getAcademy());
        return teacherBO;
    }

    public boolean apiCheckErrored(String uri, List<Integer> roleIdList, int userId) {
        ResourceEntity resourceEntity = resourceMapper.selectByApiPath(uri);
        List<Integer> haveAccessAbilityRoleIdList = roleResourceMapper.listOrderedRoleIdByResId(resourceEntity.getId());
        for (Integer roleId : roleIdList) {
            if (Collections.binarySearch(haveAccessAbilityRoleIdList, roleId) >= 0) {
                return true;
            }
        }
        return false;
    }

    public String getName(Integer type, int refId) {
        if (EAccountType.TEACHER.getCode().equals(type)) {
            TeacherEntity teacherEntity = teacherMapper.selectByPrimaryKey(refId);
            BizAssert.impossible(teacherEntity == null, Errors.TEACHER_NOT_FOUND);
            return teacherEntity.getName();
        } else if (EAccountType.ADMINISTRATOR.getCode().equals(type)) {
            AdminEntity adminEntity = adminMapper.selectByPrimaryKey(refId);
            BizAssert.impossible(adminEntity == null, Errors.ADMIN_NOT_FOUND);
            return adminEntity.getName();
        } else {
            throw new BusinessException(Errors.DATA_ERROR);
        }
    }

    /**
     * optimize 缓存用户拥有的菜单、API到{@linkplain KeyPool#token(java.lang.String) token}
     *
     * @param userEntity     user
     * @param roleEntityList role list
     * @return token value
     */
    public String buildTokenValue(UserEntity userEntity, List<RoleEntity> roleEntityList) {
        return null;
    }

    public List<MenuTreeVO> wholeMenuTree() {
        return menuPieceRecursion(0);
    }

    public List<MenuTreeVO> menuPieceRecursion(Integer parentId) {
        List<MenuTreeVO> menuTreeVoList = getMenuByParentId(parentId);
        if (CollectionUtil.isEmpty(menuTreeVoList)) {
            return menuTreeVoList;
        }
        for (MenuTreeVO menuTreeVO : menuTreeVoList) {
            menuTreeVO.setChildren(menuPieceRecursion(menuTreeVO.getId()));
        }
        return menuTreeVoList;
    }

    public List<MenuTreeVO> getMenuByParentId(Integer parentId) {
        List<ResourceEntity> firstLevelMenu = listResource(EResourceType.MENU.getCode(), parentId);
        return buildMenuTree(firstLevelMenu);
    }

    private List<MenuTreeVO> buildMenuTree(List<ResourceEntity> menuList) {
        if (CollectionUtil.isEmpty(menuList)) {
            return Collections.emptyList();
        }
        List<MenuTreeVO> retList = new ArrayList<>(menuList.size());
        for (ResourceEntity entity : menuList) {
            MenuTreeVO vo = new MenuTreeVO();
            vo.setId(entity.getId());
            vo.setName(entity.getName());
            vo.setRoute(entity.getRoute());
            vo.setMemo(entity.getMemo());
            vo.setParentId(entity.getParentId());
            retList.add(vo);
        }
        return retList;
    }

    public List<ResourceApiVO> buildApiSearchResponse(List<ResourceEntity> entityList) {
        if (CollectionUtil.isEmpty(entityList)) {
            return Collections.emptyList();
        }
        List<ResourceApiVO> retList = new ArrayList<>();
        for (ResourceEntity entity : entityList) {
            ResourceApiVO vo = new ResourceApiVO();
            vo.setId(entity.getId());
            vo.setName(entity.getName());
            vo.setPath(entity.getPath());
            vo.setContainPathVar(CodeNameVO.builder()
                    .code(entity.getContainPathVar())
                    .name(EContainPathVar.getNameByCode(entity.getContainPathVar()))
                    .build());
            vo.setParentId(entity.getParentId());
            vo.setMemo(entity.getMemo());
        }
        return retList;
    }
}
