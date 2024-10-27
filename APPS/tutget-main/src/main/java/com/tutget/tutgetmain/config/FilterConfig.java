package com.tutget.tutgetmain.config;

import com.tutget.tutgetmain.filter.AuthenticationFilter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.*;

@Configuration
public class FilterConfig {

  @Value("${jjwt.key}")
  private String jjwtKey;

  @Bean
  public FilterRegistrationBean<AuthenticationFilter> myRequestFilter() {
    FilterRegistrationBean<AuthenticationFilter> registrationBean = new FilterRegistrationBean<>();

    AuthenticationFilter authFilter = new AuthenticationFilter();
    authFilter.setJjwtKey(jjwtKey);

    registrationBean.setFilter(authFilter);
    registrationBean.addUrlPatterns("/api/*"); // Apply filter to ALL URLs (To exclude login at AuthFilter)
    return registrationBean;
  }
}
