package com.tutget.tutgetmain.config;

import com.tutget.tutgetmain.filter.AuthenticationFilter;
import com.tutget.tutgetmain.service.ProfileService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.*;

@Configuration
public class FilterConfig {

  @Value("${jjwt.key}")
  private String jjwtKey;

  private final ProfileService profileService;

  public FilterConfig(ProfileService profileService) {
    this.profileService = profileService;
  }

  @Bean
  public FilterRegistrationBean<AuthenticationFilter> myRequestFilter() {
    FilterRegistrationBean<AuthenticationFilter> registrationBean = new FilterRegistrationBean<>();

    AuthenticationFilter authFilter = new AuthenticationFilter(profileService);
    authFilter.setJjwtKey(jjwtKey);

    registrationBean.setFilter(authFilter);
    registrationBean.addUrlPatterns("/api/*"); // Apply filter to ALL URLs (To exclude login at AuthFilter)
    return registrationBean;
  }
}
