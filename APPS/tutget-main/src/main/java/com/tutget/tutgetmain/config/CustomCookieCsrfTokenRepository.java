package com.tutget.tutgetmain.config;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.security.web.csrf.CsrfTokenRepository;
import org.springframework.security.web.csrf.DefaultCsrfToken;
import org.springframework.util.StringUtils;
import org.springframework.web.util.WebUtils;

import java.util.UUID;

public class CustomCookieCsrfTokenRepository implements CsrfTokenRepository {
    static final String DEFAULT_CSRF_COOKIE_NAME = "XSRF-TOKEN";
    static final String DEFAULT_CSRF_PARAMETER_NAME = "_csrf";
    static final String DEFAULT_CSRF_HEADER_NAME = "X-XSRF-TOKEN";
    private static final String CSRF_TOKEN_REMOVED_ATTRIBUTE_NAME = CookieCsrfTokenRepository.class.getName().concat(".REMOVED");
    private String parameterName = "_csrf";
    private String headerName = "X-XSRF-TOKEN";
    private String cookieName = "XSRF-TOKEN";
    private boolean cookieHttpOnly = true;
    private String cookiePath;
    private String cookieDomain;
    private Boolean secure;
    private String cookieMaxAge = "-1";

    @Override
    public CsrfToken generateToken(HttpServletRequest request) {
        return new DefaultCsrfToken(this.headerName, this.parameterName, this.createNewToken());
    }

    @Override
    public void saveToken(CsrfToken token, HttpServletRequest request, HttpServletResponse response) {
        String tokenValue = token != null ? token.getToken() : "";

//        Cookie cookie = new Cookie(this.cookieName, tokenValue);
//        cookie.setSecure(this.secure != null ? this.secure : request.isSecure());
//        cookie.setPath(StringUtils.hasLength(this.cookiePath) ? this.cookiePath : this.getRequestContext(request));
//        cookie.setMaxAge(token != null ? this.cookieMaxAge : 0);
//        cookie.setHttpOnly(this.cookieHttpOnly);
//        if (StringUtils.hasLength(this.cookieDomain)) {
//            cookie.setDomain(this.cookieDomain);
//        }
//
//        response.addCookie(cookie);

        String cookieName = this.cookieName + "=" + tokenValue + ";";
        String cookieSecure = (this.secure != null ? this.secure ? "Secure" : "" : request.isSecure() ? "Secure" : "") + ";";
        String cookiePath = (StringUtils.hasLength(this.cookiePath) ? this.cookiePath : this.getRequestContext(request)) + ";";
        String cookieHttpOnly = (this.cookieHttpOnly ? "HttpOnly;" : "");
        String cookieDomain = "";
        if (StringUtils.hasLength(this.cookieDomain)) {
            cookieDomain = this.cookieDomain + ";";
        }

        response.addHeader("Set-Cookie", cookieName + "SameSite=None;" + cookieSecure + "Path=" + cookiePath + cookieHttpOnly + (token != null ? "" : "Max-Age=0;") + (StringUtils.hasLength(cookieDomain) ? "Domain=" + cookieDomain : "" ));

        if (!StringUtils.hasLength(tokenValue)) {
            request.setAttribute(CSRF_TOKEN_REMOVED_ATTRIBUTE_NAME, Boolean.TRUE);
        } else {
            request.removeAttribute(CSRF_TOKEN_REMOVED_ATTRIBUTE_NAME);
        }
    }

    @Override
    public CsrfToken loadToken(HttpServletRequest request) {
        if (Boolean.TRUE.equals(request.getAttribute(CSRF_TOKEN_REMOVED_ATTRIBUTE_NAME))) {
            return null;
        } else {
            Cookie cookie = WebUtils.getCookie(request, this.cookieName);
            if (cookie == null) {
                return null;
            } else {
                String token = cookie.getValue();
                return !StringUtils.hasLength(token) ? null : new DefaultCsrfToken(this.headerName, this.parameterName, token);
            }
        }
    }

    public static CustomCookieCsrfTokenRepository withHttpOnlyFalse() {
        CustomCookieCsrfTokenRepository result = new CustomCookieCsrfTokenRepository();
        result.setCookieHttpOnly(false);
        return result;
    }

    private String getRequestContext(HttpServletRequest request) {
        String contextPath = request.getContextPath();
        return contextPath.length() > 0 ? contextPath : "/";
    }

    private String createNewToken() {
        return UUID.randomUUID().toString();
    }

    public void setSecure(Boolean secure) {
        this.secure = secure;
    }

    public void setCookieHttpOnly(boolean cookieHttpOnly) {
        this.cookieHttpOnly = cookieHttpOnly;
    }
}
