package com.ycourlee.ms.labbooking.manager;

import com.ycourlee.ms.labbooking.config.properties.DefaultRoleProperties;
import com.ycourlee.ms.labbooking.enums.EAccountType;
import com.ycourlee.ms.labbooking.exception.error.Errors;
import com.ycourlee.ms.labbooking.mapper.*;
import com.ycourlee.ms.labbooking.model.entity.AdminEntity;
import com.ycourlee.ms.labbooking.model.entity.TeacherEntity;
import com.ycourlee.ms.labbooking.model.entity.UserEntity;
import com.ycourlee.ms.labbooking.model.entity.UserRoleEntity;
import com.ycourlee.ms.labbooking.model.vo.RoleVO;
import com.ycourlee.ms.labbooking.util.BizAssert;
import com.ycourlee.root.util.CollectionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

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
    public void createUser(Integer type, String phone, String password) {
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
            bindRolesToUser(user.getId(), defaultAdminRoleId.getDefaultRoleId());
        } else {
            bindRolesToUser(user.getId(), defaultTeacherRoleId.getDefaultRoleId());
        }
    }

    /*----------BELOW---------- private methods ----------BELOW----------*/

    private void bindRolesToUser(int userId, List<Integer> roleIds) {
        BizAssert.that(CollectionUtil.isNotEmpty(roleIds), Errors.INTERNAL_CONFIGURATION_ERROR);
        List<UserRoleEntity> userRoleEntityList = new ArrayList<>(roleIds.size());
        for (Integer roleId : roleIds) {
            UserRoleEntity userRoleEntity = new UserRoleEntity();
            userRoleEntity.setUserId(userId);
            userRoleEntity.setRoleId(roleId);
            userRoleEntityList.add(userRoleEntity);
        }
        if (userRoleEntityList.size() == 1) {
            userRoleMapper.insertSelective(userRoleEntityList.get(0));
        }
        userRoleMapper.batchInsertFcl(userRoleEntityList);
    }

    private int createAdmin(String phone) {
        TeacherEntity entity = new TeacherEntity();
        entity.setNickname("教师" + phone);
        teacherMapper.insertSelective(entity);
        return entity.getId();
    }

    private int createTeacher(String phone) {
        AdminEntity entity = new AdminEntity();
        entity.setNickname("管理员" + phone);
        adminMapper.insertSelective(entity);
        return entity.getId();
    }

    public List<RoleVO> roleInfo(Integer userId) {
        return null;
    }
}
