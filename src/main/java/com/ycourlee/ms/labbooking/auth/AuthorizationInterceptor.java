package com.ycourlee.ms.labbooking.auth;

import com.alibaba.fastjson.JSON;
import com.ycourlee.ms.labbooking.config.properties.LabSwitchProperties;
import com.ycourlee.ms.labbooking.enums.EAccountType;
import com.ycourlee.ms.labbooking.exception.AuthorizationException;
import com.ycourlee.ms.labbooking.exception.error.Errors;
import com.ycourlee.ms.labbooking.manager.RbacManager;
import com.ycourlee.ms.labbooking.manager.spec.Redis;
import com.ycourlee.ms.labbooking.model.bo.AdminBO;
import com.ycourlee.ms.labbooking.model.bo.ClaimValueBO;
import com.ycourlee.ms.labbooking.model.bo.RoleBO;
import com.ycourlee.ms.labbooking.model.bo.TeacherBO;
import com.ycourlee.ms.labbooking.model.entity.UserEntity;
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
    @Qualifier("authorizationProperties")
    private LabSwitchProperties authorizationProperties;
    @Autowired
    private RbacManager         rbacManager;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (disabled() || greenLight(request.getRequestURI())) {
            return true;
        }

        if (!authorizationProperties.isEnabled()) {
            return true;
        }

        String token = request.getHeader(properties.getTokenKey());
        ClaimValueBO claim = JSON.parseObject(redis.get(token), ClaimValueBO.class);
        claim.assertValid();

        UserEntity user = rbacManager.getUser(claim.getUserId());
        AdminBO adminBO = null;
        TeacherBO teacherBO = null;
        if (user.getType().equals(EAccountType.ADMINISTRATOR.getCode())) {
            adminBO = rbacManager.getAdminBo(user.getRefId());
        } else if (user.getType().equals(EAccountType.TEACHER.getCode())) {
            teacherBO = rbacManager.getTeacherBo(user.getRefId());
        } else {
            throw new AuthorizationException(Errors.UNKNOWN_ACCOUNT_TYPE);
        }
        if (adminBO == null && teacherBO == null) {
            throw new AuthorizationException(Errors.UNKNOWN_USER);
        }
        if (rbacManager.apiCheckErrored(request.getRequestURI(), claim.getRoles().stream().map(RoleBO::getId).collect(Collectors.toList()), claim.getUserId())) {
            log.error("user[id: {}] want to access {}, it's illegal.", claim.getUserId(), request.getRequestURI());
            throw new AuthorizationException(Errors.AUTHORIZATION_FAILED);
        }
        if (user.getType().equals(EAccountType.ADMINISTRATOR.getCode())) {
            Context.builder()
                    .userId(claim.getUserId())
                    .phone(user.getPhone())
                    .username(user.getUsername())
                    .name(adminBO.getName())
                    .adminBO(adminBO)
                    .build();
            return true;
        }
        if (user.getType().equals(EAccountType.TEACHER.getCode())) {
            Context.builder()
                    .userId(claim.getUserId())
                    .phone(user.getPhone())
                    .username(user.getUsername())
                    .name(teacherBO.getName())
                    .teacherBO(teacherBO)
                    .build();
            return true;
        }
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        Context.clean();
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        Context.clean();
    }
}
