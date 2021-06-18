package com.ycourlee.ms.labbooking.auth;

import com.alibaba.fastjson.JSON;
import com.ycourlee.ms.labbooking.config.properties.LabSwitchProperties;
import com.ycourlee.ms.labbooking.exception.AuthenticationException;
import com.ycourlee.ms.labbooking.exception.error.Errors;
import com.ycourlee.ms.labbooking.manager.spec.JwtIssuer;
import com.ycourlee.ms.labbooking.manager.spec.Redis;
import com.ycourlee.ms.labbooking.util.KeyPool;
import com.ycourlee.root.core.context.CmReturn;
import com.ycourlee.root.core.domain.context.ApiResponse;
import com.ycourlee.root.util.CollectionUtil;
import com.ycourlee.root.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerExceptionResolver;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * @author yongjiang
 */
@Configuration
public class AuthenticationFilter extends LabAuth implements Filter {

    private static final Logger log = LoggerFactory.getLogger(AuthenticationFilter.class);

    @Autowired
    @Qualifier("authenticationProperties")
    private LabSwitchProperties      authenticationProperties;
    @Autowired
    private JwtIssuer                jwtIssuer;
    @Autowired
    private Redis                    redis;
    @Autowired
    private HandlerExceptionResolver handlerExceptionResolver;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {}

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        // 全局
        if (disabled()) {
            chain.doFilter(request, response);
            return;
        }
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        if (greenLight(httpRequest.getRequestURI())) {
            chain.doFilter(request, response);
            return;
        }

        // 认证
        if (!authenticationProperties.isEnabled()) {
            chain.doFilter(request, response);
            return;
        }
        if (authenticationGreenLight(httpRequest.getRequestURI())) {
            chain.doFilter(request, response);
            return;
        }

        String token = httpRequest.getHeader(properties.getTokenKey());
        // 如果没有响应错误 才会传递filter chain，否则返回
        if (tokenDiscuss(token, httpRequest, httpResponse)) {
            chain.doFilter(request, response);
        }
    }

    private boolean tokenDiscuss(String token, HttpServletRequest httpRequest, HttpServletResponse httpResponse) {
        if (StringUtil.isEmpty(token)) {
            responseException(httpRequest, httpResponse, Errors.TOKEN_NOT_FOUND);
            return false;
        }
        if (jwtIssuer.unknown(token)) {
            responseException(httpRequest, httpResponse, Errors.UNAVAILABLE_TOKEN);
            return false;
        }
        if (StringUtil.isEmpty(redis.get(KeyPool.tokenMapUid(token)))) {
            responseException(httpRequest, httpResponse, Errors.TOKEN_EXPIRED);
            return false;
        }
        return true;
    }

    @Override
    public void destroy() {
        log.trace("{}: 88", this.getClass().getName());
    }

    private void responseException(HttpServletRequest httpRequest, HttpServletResponse httpResponse, CmReturn error) {
        handlerExceptionResolver.resolveException(httpRequest, httpResponse, null, new AuthenticationException(error));
    }

    private boolean authenticationGreenLight(String uri) {
        // 默认都不放行
        if (CollectionUtil.isEmpty(authenticationProperties.getPathWhitelist())) {
            return false;
        }
        for (String pathWhitelist : authenticationProperties.getPathWhitelist()) {
            if (PATH_MATCHER.match(pathWhitelist, uri)) {
                return true;
            }
        }
        return false;
    }
}
