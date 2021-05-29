package com.ycourlee.ms.labbooking.auth;

import com.ycourlee.ms.labbooking.config.properties.LabSwitchProperties;
import com.ycourlee.ms.labbooking.exception.AuthenticationException;
import com.ycourlee.ms.labbooking.exception.error.Errors;
import com.ycourlee.ms.labbooking.manager.spec.JwtIssuer;
import com.ycourlee.ms.labbooking.manager.spec.Redis;
import com.ycourlee.ms.labbooking.util.KeyPool;
import com.ycourlee.root.core.context.CmReturn;
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

        if (!authenticationProperties.isEnabled()) {
            chain.doFilter(request, response);
            return;
        }
        String token = httpRequest.getHeader(properties.getTokenKey());
        tokenDiscuss(token, httpRequest, httpResponse);
    }

    private void tokenDiscuss(String token, HttpServletRequest httpRequest, HttpServletResponse httpResponse) {
        if (StringUtil.isEmpty(token)) {
            responseException(httpRequest, httpResponse, Errors.TOKEN_NOT_FOUND);
            return;
        }
        if (jwtIssuer.unknown(token)) {
            responseException(httpRequest, httpResponse, Errors.UNAVAILABLE_TOKEN);
            return;
        }
        if (StringUtil.isEmpty(redis.get(KeyPool.token(token)))) {
            responseException(httpRequest, httpResponse, Errors.TOKEN_EXPIRED);
        }
    }

    @Override
    public void destroy() {
        log.trace("{}: 88", this.getClass().getName());
    }

    private void responseException(HttpServletRequest httpRequest, HttpServletResponse httpResponse, CmReturn error) {
        handlerExceptionResolver.resolveException(httpRequest, httpResponse, null, new AuthenticationException(error));
    }
}
