package com.ycourlee.ms.labbooking.manager;

import com.alibaba.fastjson.JSON;
import com.ycourlee.ms.labbooking.config.properties.LabAppRegistrationProperties;
import com.ycourlee.ms.labbooking.enums.EAccountType;
import com.ycourlee.ms.labbooking.exception.error.Errors;
import com.ycourlee.ms.labbooking.manager.spec.JwtIssuer;
import com.ycourlee.ms.labbooking.manager.spec.Redis;
import com.ycourlee.ms.labbooking.mapper.UserMapper;
import com.ycourlee.ms.labbooking.model.bo.ClaimValueBO;
import com.ycourlee.ms.labbooking.model.bo.RoleBO;
import com.ycourlee.ms.labbooking.model.bo.request.RegisterRequest;
import com.ycourlee.ms.labbooking.model.bo.response.LoginResponse;
import com.ycourlee.ms.labbooking.model.entity.RoleEntity;
import com.ycourlee.ms.labbooking.model.entity.UserEntity;
import com.ycourlee.ms.labbooking.util.BizAssert;
import com.ycourlee.ms.labbooking.util.KeyPool;
import com.ycourlee.root.core.context.BusinessException;
import com.ycourlee.root.util.RandomUtil;
import com.ycourlee.root.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;


/**
 * @author yongjiang
 */
@Component
public class AccountManager {

    @Autowired
    private RbacManager                  rbacManager;
    @Autowired
    private UserMapper                   userMapper;
    @Autowired
    private Redis                        redis;
    @Autowired
    private JwtIssuer                    jwtIssuer;
    @Autowired
    private LabAppRegistrationProperties properties;

    @Deprecated
    public boolean noAliveCodeCurrentPhone(String phone) {
        return StringUtil.isNotEmpty(redis.get(KeyPool.registerCode(phone)));
    }

    public boolean noAliveCodeCurrentAccount(String account) {
        return StringUtil.isNotEmpty(redis.get(KeyPool.registerCodeApplyFrequencyLock(account)));
    }

    public String checkCodeAndReturnKey(String account, String verifyCode) {
        String composeValue = redis.get(KeyPool.registerCode(account));
        String[] unbindType = unbindType(composeValue);
        String type = unbindType[0];
        String trueCode = unbindType[1];

        BizAssert.that(trueCode.equalsIgnoreCase(verifyCode), "验证码错误");
        String registerKey = RandomUtil.nextRandomString16();
        redis.set(KeyPool.registerCode(account), bindType(Integer.parseInt(type), registerKey));
        return registerKey;
    }

    public void adminFilter(Integer type, String phone) {
        if (type.compareTo(EAccountType.ADMINISTRATOR.getCode()) == 0) {
            if (!properties.getAdminWhitelist().contains(phone)) {
                throw new BusinessException(Errors.YOU_ARE_NOT_ADMIN);
            }
        }
    }

    public UserEntity queryUserBy(Integer type, String phone, String email) {
        return userMapper.selectByTypeDclPhoneEmail(type, phone, email);
    }

    public String bindType(int type, String value) {
        return type + StringUtil.SEMINAL_STRING + value;
    }

    public String[] unbindType(String composeValue) {
        return composeValue.split(StringUtil.SEMINAL_STRING);
    }

    public void checkRegisterKey(RegisterRequest request) {
        String composeValue = redis.get(KeyPool.registerCode(request.getAccount()));
        String[] unbindType = unbindType(composeValue);
        BizAssert.that(unbindType[1].equals(request.getRegisterKey()), Errors.PLEASE_NOT_HACK_REGISTRATION);
        request.setType(Integer.parseInt(unbindType[0]));
    }

    public UserEntity verifyAccountAndPasswordByPhone(Integer type, String password, String phone) {
        UserEntity userEntity = userMapper.selectByTypeDclPhoneEmail(type, phone, null);
        BizAssert.that(userEntity != null, Errors.PHONE_NOT_EXISTS);
        BizAssert.that(userEntity.getPassword().equals(password), Errors.PHONE_OR_PASSWORD_ERROR);
        return userEntity;
    }

    public UserEntity verifyAccountAndPasswordByEmail(Integer type, String password, String email) {
        UserEntity userEntity = userMapper.selectByTypeDclPhoneEmail(type, null, email);
        BizAssert.that(userEntity != null, Errors.EMAIL_NOT_EXISTS);
        BizAssert.that(userEntity.getPassword().equals(password), Errors.EMAIL_OR_PASSWORD_ERROR);
        return userEntity;
    }

    public String buildJsonClaimValue(UserEntity userEntity, List<RoleEntity> roleEntityList) {
        return JSON.toJSONString(ClaimValueBO.builder()
                .userId(userEntity.getId())
                .refId(userEntity.getRefId())
                .roles(roleEntityList.stream()
                        .map(entity -> RoleBO.builder()
                                .id(entity.getId())
                                .name(entity.getName()).build())
                        .collect(Collectors.toList()))
                .build());
    }

    public String cacheLoginStatus(String jsonClaimValue, Boolean rememberMe) {
        String token = jwtIssuer.issue(jsonClaimValue);
        if (rememberMe != null && rememberMe) {
            redis.setEx(KeyPool.token(token), jsonClaimValue, KeyPool.days7InSeconds());
            return token;
        }
        redis.setEx(KeyPool.token(token), jsonClaimValue, KeyPool.defaultTokenExpireTime());
        return token;
    }

    public LoginResponse buildLoginResponse(UserEntity userEntity, boolean rememberMe) {
        List<RoleEntity> roleEntityList = rbacManager.listRole(userEntity.getId());
        return LoginResponse.builder()
                .username(userEntity.getUsername())
                .name(rbacManager.getName(userEntity.getType(), userEntity.getRefId()))
                .token(cacheLoginStatus(buildJsonClaimValue(userEntity, roleEntityList), rememberMe))
                .type(userEntity.getType())
                .roles(roleEntityList.stream()
                        .map(RoleEntity::getName)
                        .collect(Collectors.toList()))
                .build();
    }
}
