package com.tutget.tutgetmain.filter;

import com.tutget.tutgetmain.exception.AuthCookieNotFoundException;
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

/**
 * Intercepts all API call except for login to verify JWT
 */
@Component
public class AuthenticationFilter extends OncePerRequestFilter {

  private String jjwtKey;

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
    if (httpRequest.getRequestURI().contains("login") || httpRequest.getRequestURI().contains("logout")) {
      System.out.println("Login URI, do not run jwt validation");

    } else {
      System.out.println("Verifying with jjwt key: " + jjwtKey);

      Cookie cookie = Arrays.stream(httpRequest.getCookies())
        .filter(c -> c.getName().equals("authCookie"))
        .findFirst()
        .orElseThrow(AuthCookieNotFoundException::new);

      System.out.println("Cookie: " + cookie.getName() + " value: " + cookie.getValue());

      Jwt<?, Claims> jwt = Jwts.parser()
        // SHA256(CAKES) = 256 bits
        //"aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"
        .verifyWith(new SecretKeySpec(jjwtKey.getBytes(StandardCharsets.UTF_8), "HmacSHA256"))
        .build()
        .parseSignedClaims(cookie.getValue());

      System.out.println("ID: " + jwt.getPayload().get("id", String.class));
      // Set the authentication in the context
      SecurityContextHolder.getContext().setAuthentication(
        new UsernamePasswordAuthenticationToken(
          jwt.getPayload().getSubject(), jwt.getPayload().get("id", String.class)
        )
      );

    }

    // Pass the request along the filter chain (Do original API call processing logic)
    filterChain.doFilter(httpRequest, servletResponse);

    // Post-processing: Code to execute after the request is handled
    System.out.println("Response sent: " + servletResponse);

    // Clear context
    SecurityContextHolder.clearContext();
  }
}
