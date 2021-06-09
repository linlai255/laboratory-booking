package com.ycourlee.ms.labbooking.auth;

import com.alibaba.fastjson.JSON;
import com.ycourlee.ms.labbooking.config.properties.LabSwitchProperties;
import com.ycourlee.ms.labbooking.enums.EAccountType;
import com.ycourlee.ms.labbooking.exception.AuthorizationException;
import com.ycourlee.ms.labbooking.exception.error.Errors;
import com.ycourlee.ms.labbooking.manager.AccountCacheManager;
import com.ycourlee.ms.labbooking.manager.RbacManager;
import com.ycourlee.ms.labbooking.manager.spec.JwtIssuer;
import com.ycourlee.ms.labbooking.manager.spec.Redis;
import com.ycourlee.ms.labbooking.model.bo.ClaimValueBO;
import com.ycourlee.ms.labbooking.model.bo.RoleBO;
import com.ycourlee.ms.labbooking.model.bo.UserBO;
import com.ycourlee.ms.labbooking.util.KeyPool;
import com.ycourlee.root.util.CollectionUtil;
import com.ycourlee.root.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.stream.Collectors;

/**
 * @author yongjiang
 */
@Configuration
public class AuthorizationInterceptor extends LabAuth implements HandlerInterceptor {

    private static final Logger log = LoggerFactory.getLogger(AuthorizationInterceptor.class);

    @Autowired
    private Redis               redis;
    @Autowired
    private JwtIssuer           jwtIssuer;
    @Autowired
    @Qualifier("authorizationProperties")
    private LabSwitchProperties authorizationProperties;
    @Autowired
    private RbacManager         rbacManager;
    @Autowired
    private AccountCacheManager accountCacheManager;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 全局
        if (disabled() || greenLight(request.getRequestURI())) {
            return true;
        }
        // 授权
        if (!authorizationProperties.isEnabled() || authenticationGreenLight(request.getRequestURI())) {
            return true;
        }

        String token = request.getHeader(properties.getTokenKey());
        if (StringUtil.isEmpty(token)) {
            throw new AuthorizationException(Errors.TOKEN_NOT_FOUND);
        }

        String userIdString = redis.get(KeyPool.tokenMapUid(token));
        Integer userId;
        ClaimValueBO claimValueBO = null;
        if (StringUtil.isEmpty(userIdString)) {
            claimValueBO = JSON.parseObject(jwtIssuer.parse(token), ClaimValueBO.class);
            userId = claimValueBO.getUserId();
        } else {
            userId = Integer.parseInt(userIdString);
        }
        String userBoJsonString = redis.get(KeyPool.uidMapContextInfo(userId));

        if (StringUtil.isEmpty(userBoJsonString)) {
            if (claimValueBO == null) {
                claimValueBO = JSON.parseObject(jwtIssuer.parse(token), ClaimValueBO.class);
            }
            accountCacheManager.cacheUserContext(rbacManager.getUserNoDel(userId), claimValueBO.getRoles());
            userBoJsonString = redis.get(KeyPool.uidMapContextInfo(userId));
        }

        UserBO userBO = JSON.parseObject(userBoJsonString, UserBO.class);

        if (rbacManager.apiCheckErrored(request.getRequestURI(), request.getMethod(), userBO.getRoleList().stream().map(RoleBO::getId).collect(Collectors.toList()), userId)) {
            log.error("user[id: {}, name: {}, username: {}, nickname: {}] want to access {}, it's illegal.",
                    userId, userBO.getName(), userBO.getUsername(), userBO.getNickname(), request.getRequestURI());
            throw new AuthorizationException(Errors.AUTHORIZATION_FAILED);
        }
        Context.builder()
                .userId(userId)
                .refId(userBO.getRefId())
                .phone(userBO.getPhone())
                .nickname(userBO.getNickname())
                .username(userBO.getUsername())
                .name(userBO.getName())
                .type(EAccountType.ADMINISTRATOR.getCode())
                .build();
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        Context.clean();
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        Context.clean();
    }

    private boolean authenticationGreenLight(String uri) {
        // 默认都不放行
        if (CollectionUtil.isEmpty(authorizationProperties.getPathWhitelist())) {
            return false;
        }
        for (String pathWhitelist : authorizationProperties.getPathWhitelist()) {
            if (PATH_MATCHER.match(pathWhitelist, uri)) {
                return true;
            }
        }
        return false;
    }
}
