package com.ycourlee.ms.labbooking.auth.filter;

import com.ycourlee.ms.labbooking.exception.AuthenticationException;
import com.ycourlee.root.util.StringUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author yongjiang
 */
@Configuration
public class AuthenticationFilter implements Filter {

    public String tokenKey;

    @Value("${lab.app.authentication.enabled:true}")
    public boolean enabled;

    @Value("${lab.app.authentication.url.whitelist:[]}")
    public List<String> whitelist = new ArrayList<>();

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
        HttpServletRequest httpReq = (HttpServletRequest) request;
        String token = httpReq.getHeader(tokenKey);
        if (StringUtil.isEmpty(token)) {
            throw new AuthenticationException();
        }
    }

    @Override
    public void destroy() {

    }
}
