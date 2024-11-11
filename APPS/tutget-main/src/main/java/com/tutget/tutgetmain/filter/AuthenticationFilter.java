package com.tutget.tutgetmain.filter;

import com.tutget.tutgetmain.exception.AuthCookieNotFoundException;
import com.tutget.tutgetmain.exception.PermissionsException;
import com.tutget.tutgetmain.model.profile.Profile;
import com.tutget.tutgetmain.service.ProfileService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwt;
import io.jsonwebtoken.Jwts;

import jakarta.servlet.*;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.crypto.spec.SecretKeySpec;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Intercepts all API calls to verify JWT
 */
@Component
public class AuthenticationFilter extends OncePerRequestFilter {

  private String jjwtKey;
  private final ProfileService profileService;

  public AuthenticationFilter(ProfileService profileService) {
    this.profileService = profileService;
  }

  public void setJjwtKey(String jjwtKey) {
    this.jjwtKey = jjwtKey;
  }

  //  @Override
//  public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
//    // Pre-processing: Code to execute before the request is handled
//    System.out.println("jjwt key: " + jjwtKey);
//    if (servletRequest instanceof HttpServletRequest) {
//      HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
//
//      Cookie cookie = Arrays.stream(httpRequest.getCookies())
//        .filter(c -> c.getName().equals("lookieLookieAtMyCookie"))
//        .findFirst()
//        .orElseThrow(UnacceptableException::new);
//
//      Jwt<?, Claims> jwt = Jwts.parser()
//        // SHA256(CAKES) = 256 bits
//        //"aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"
//        // ?_?
//        .verifyWith(new SecretKeySpec(jjwtKey.getBytes(StandardCharsets.UTF_8), "HmacSHA256"))
//        .build()
//        .parseSignedClaims(cookie.getValue());
//
//      SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(jwt.getPayload().getSubject(), ""));
//    }
//
//    // Pass the request along the filter chain
//    filterChain.doFilter(servletRequest, servletResponse);
//
//    // Post-processing: Code to execute after the request is handled
//    System.out.println("Response sent: " + servletResponse.toString());
//    SecurityContextHolder.clearContext();
//  }

  @Override
  protected void doFilterInternal(HttpServletRequest httpRequest, HttpServletResponse servletResponse, FilterChain filterChain) throws ServletException, IOException {
    // Pre-processing: Code to execute before the request (API call) is handled
    System.out.println("Request servlet: " + httpRequest.getMethod() + httpRequest.getRequestURI());

    Pattern otherUserPattern = Pattern.compile("users/userId/.+$");
    Matcher otherUserMatcher = otherUserPattern.matcher(httpRequest.getRequestURI());

    if (
      httpRequest.getRequestURI().contains("login") ||
      httpRequest.getRequestURI().contains("logout") ||
      httpRequest.getRequestURI().contains("search") ||
      httpRequest.getRequestURI().contains("api/ad") ||
      otherUserMatcher.find() ||
      (httpRequest.getMethod().contains("GET") && (httpRequest.getRequestURI().contains("listings") || httpRequest.getRequestURI().contains("qna")))
    ) {
      System.out.println("Jwt validation not required");

    } else {
      System.out.println("Verifying with jjwt key: " + jjwtKey);

      Cookie[] cookies = httpRequest.getCookies();

      if (cookies == null) {
        servletResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED, "AuthCookieNotFound");
        servletResponse.getWriter().flush();
        return;
      }

      Cookie cookie = Arrays.stream(httpRequest.getCookies())
        .filter(c -> c.getName().equals("authCookie"))
        .findFirst()
//        .orElseThrow(AuthCookieNotFoundException::new);
        .orElse(null);

      // Check if cookie is missing
      if (cookie == null) {
//        servletResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        servletResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED, "AuthCookieNotFound");
//        servletResponse.getWriter().write("{\"error\":\"AuthCookieNotFound\", \"message\":\"User is not authorized to access this resource.\"}");
        servletResponse.getWriter().flush();
        return;
      }

      System.out.println("Cookie: " + cookie.getName() + " value: " + cookie.getValue());

      Jwt<?, Claims> jwt;

      try {
        jwt = Jwts.parser()
          // SHA256(CAKES) = 256 bits
          //"aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"
          .verifyWith(new SecretKeySpec(jjwtKey.getBytes(StandardCharsets.UTF_8), "HmacSHA256"))
          .build()
          .parseSignedClaims(cookie.getValue());
      } catch (Exception e) {
        // Jwt expired etc.
        System.out.println("Jwt error: " + e);
        servletResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED, "AuthCookieNotFound");
        servletResponse.getWriter().flush();
        return;
      }

      System.out.println("ID: " + jwt.getPayload().get("id", String.class));
      // Set the authentication in the context
      SecurityContextHolder.getContext().setAuthentication(
        new UsernamePasswordAuthenticationToken(
          jwt.getPayload().getSubject(), jwt.getPayload().get("id", String.class)
        )
      );

      if (httpRequest.getMethod().contains("POST") && httpRequest.getRequestURI().contains("listings")) {
        Profile profile = profileService.getProfile(SecurityContextHolder.getContext().getAuthentication().getCredentials().toString());

        if (profile == null || !profile.userType().equalsIgnoreCase("S")) {
          throw new PermissionsException();
        }
      }


    }

    // Pass the request along the filter chain (Do original API call processing logic)
    filterChain.doFilter(httpRequest, servletResponse);

    // Post-processing: Code to execute after the request is handled
    System.out.println("Response sent: " + servletResponse);

    // Clear context
    SecurityContextHolder.clearContext();
  }
}
