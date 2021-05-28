package com.ycourlee.ms.labbooking.auth.filter;

import com.ycourlee.ms.labbooking.config.properties.LabAntPathSwitchProperties;
import com.ycourlee.ms.labbooking.exception.AuthenticationException;
import com.ycourlee.root.util.CollectionUtil;
import com.ycourlee.root.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.servlet.HandlerExceptionResolver;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author yongjiang
 */
@Configuration
public class AuthenticationFilter implements Filter {

    private static final Logger log = LoggerFactory.getLogger(AuthenticationFilter.class);

    public String tokenKey;

    @Autowired
    @Qualifier("authenticationProperties")
    public LabAntPathSwitchProperties authenticationProperties;

    @Autowired
    private HandlerExceptionResolver handlerExceptionResolver;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        String customTokenKey = filterConfig.getInitParameter("token-key");
        if (StringUtil.isNotEmpty(customTokenKey)) {
            tokenKey = customTokenKey;
        } else {
            tokenKey = "Authorization";
        }
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        if (ifEnabled(httpRequest, httpResponse, chain)) {
            chain.doFilter(request, response);
            return;
        }
        if (ifGreenLight(httpRequest, httpResponse, chain)) {
            chain.doFilter(request, response);
            return;
        }
        String token = httpRequest.getHeader(tokenKey);

        // todo login jjwt

        if (StringUtil.isEmpty(token)) {
            handlerExceptionResolver.resolveException(httpRequest, httpResponse, null, new AuthenticationException());
        }
    }

    @Override
    public void destroy() {

    }

    private boolean ifGreenLight(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        // 默认都不放行
        if (CollectionUtil.isEmpty(authenticationProperties.getPathWhitelist())) {
            return false;
        }
        AntPathMatcher pathMatcher = new AntPathMatcher();
        for (String pathWhitelist : authenticationProperties.getPathWhitelist()) {
            if (pathMatcher.matchStart(pathWhitelist, request.getRequestURI())) {
                return true;
            }
        }
        return false;
    }

    private boolean ifEnabled(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        return authenticationProperties.isEnabled();
    }
}
