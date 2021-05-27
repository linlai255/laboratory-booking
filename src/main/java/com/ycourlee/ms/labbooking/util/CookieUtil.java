package com.ycourlee.ms.labbooking.util;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * @author yongjiang
 */
public final class CookieUtil {

    private CookieUtil() {}

    /**
     * @param response http response
     * @param name     cookie name
     * @param value    cookie value
     * @param maxAge   cookie timeout in seconds.
     */
    public static void addCookie(HttpServletResponse response, String name, String value, int maxAge) {
        Cookie cookie = new Cookie(name, value);
        cookie.setPath("/");
        if (maxAge > 0) {
            cookie.setMaxAge(maxAge);
        }
        response.addCookie(cookie);
    }

    /**
     * @param request http request.
     * @param name    cookie name
     * @return cookie value.
     */
    public static String getCookieByName(HttpServletRequest request, String name) {
        Map<String, Cookie> cookieMap = CookieUtil.readCookieMap(request);
        if (cookieMap.containsKey(name)) {
            Cookie cookie = cookieMap.get(name);
            return cookie.getValue();
        } else {
            return null;
        }
    }

    /**
     * @param request http request.
     * @return cookie map.
     */
    private static Map<String, Cookie> readCookieMap(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if (cookies == null) {
            return Collections.emptyMap();
        }

        Map<String, Cookie> cookieMap = new HashMap<>(cookies.length);
        for (Cookie cookie : cookies) {
            cookieMap.put(cookie.getName(), cookie);
        }
        return cookieMap;
    }
}