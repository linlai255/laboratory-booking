package com.ycourlee.ms.labbooking.manager;

import com.alibaba.fastjson.JSON;
import com.ycourlee.ms.labbooking.enums.EAccountType;
import com.ycourlee.ms.labbooking.exception.error.Errors;
import com.ycourlee.ms.labbooking.manager.spec.JwtIssuer;
import com.ycourlee.ms.labbooking.manager.spec.Redis;
import com.ycourlee.ms.labbooking.model.bo.AdminBO;
import com.ycourlee.ms.labbooking.model.bo.RoleBO;
import com.ycourlee.ms.labbooking.model.bo.TeacherBO;
import com.ycourlee.ms.labbooking.model.bo.UserBO;
import com.ycourlee.ms.labbooking.model.entity.UserEntity;
import com.ycourlee.ms.labbooking.util.BizAssert;
import com.ycourlee.ms.labbooking.util.KeyPool;
import com.ycourlee.root.core.context.BusinessException;
import com.ycourlee.root.util.CollectionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author yongjiang
 */
@Component
public class AccountCacheManager {

    @Autowired
    private Redis       redis;
    @Autowired
    private JwtIssuer   jwtIssuer;
    @Autowired
    private RbacManager rbacManager;

    public String cacheLoginStatus(UserEntity user, List<RoleBO> roleList, String jsonClaimValue, Boolean rememberMe) {
        String token = jwtIssuer.issue(jsonClaimValue);
        cacheUserContext(user, roleList);
        long cacheTime = (rememberMe != null && rememberMe) ? KeyPool.days7InSeconds() : KeyPool.defaultTokenExpireTime();
        redis.setEx(KeyPool.tokenMapUid(token), user.getId() + "", cacheTime);
        return token;
    }

    public void cacheUserContext(UserEntity user, List<RoleBO> roleList) {
        BizAssert.that(user != null);
        BizAssert.that(CollectionUtil.isNotEmpty(roleList));
        redis.set(KeyPool.uidMapUserType(user.getId()), user.getType() + "");
        redis.set(KeyPool.uidMapContextInfo(user.getId()), buildUserContext(user, roleList));
    }

    public void expireToken(String token) {
        redis.delete(KeyPool.tokenMapUid(token));
    }

    public void expireUserContextCache(Integer userId) {
        redis.delete(KeyPool.uidMapContextInfo(userId));
    }

    private String buildUserContext(UserEntity user, List<RoleBO> roleList) {
        UserBO userBO = new UserBO();
        AdminBO adminBO = null;
        TeacherBO teacherBO = null;
        if (user.getType().equals(EAccountType.ADMINISTRATOR.getCode())) {
            adminBO = rbacManager.getAdminBo(user.getRefId());
        } else if (user.getType().equals(EAccountType.TEACHER.getCode())) {
            teacherBO = rbacManager.getTeacherBo(user.getRefId());
        } else {
            throw new BusinessException(Errors.UNKNOWN_ACCOUNT_TYPE);
        }
        if (adminBO == null && teacherBO == null) {
            throw new BusinessException(Errors.UNKNOWN_USER);
        }
        userBO.setId(user.getId());
        userBO.setRefId(user.getRefId());
        userBO.setUsername(user.getUsername());
        userBO.setPhone(user.getPhone());
        userBO.setEmail(user.getEmail());
        userBO.setWechat(user.getWechat());
        userBO.setType(user.getType());
        userBO.setRoleList(roleList);
        if (adminBO != null) {
            userBO.setName(adminBO.getName());
            userBO.setNickname(adminBO.getNickname());
            return JSON.toJSONString(userBO);
        }
        userBO.setName(teacherBO.getName());
        userBO.setNickname(teacherBO.getNickname());
        userBO.setAcademy(teacherBO.getAcademy());
        userBO.setDepartment(teacherBO.getDepartment());
        return JSON.toJSONString(userBO);
    }

}
