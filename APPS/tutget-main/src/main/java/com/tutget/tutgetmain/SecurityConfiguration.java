package com.tutget.tutgetmain;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.SecurityWebFiltersOrder;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.web.csrf.*;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.security.web.server.csrf.CookieServerCsrfTokenRepository;
import org.springframework.security.web.server.csrf.ServerCsrfTokenRequestAttributeHandler;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

import java.io.IOException;
import java.util.function.Supplier;

@Configuration
//@EnableWebSecurity
@EnableWebFluxSecurity
public class SecurityConfiguration {

private static final Logger logger = LoggerFactory.getLogger(SecurityConfiguration.class);

    @Bean
    SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) {
        http
                .csrf((csrf) -> csrf
                        .csrfTokenRepository(CookieServerCsrfTokenRepository.withHttpOnlyFalse())
                        .csrfTokenRequestHandler(new ServerCsrfTokenRequestAttributeHandler())
                )
                .addFilterAfter(new JwtAuthenticationFilter(), SecurityWebFiltersOrder.AUTHENTICATION);

        return http.build();
    }

//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//        http
////          .csrf()
////          .disable()
////        http.csrf(csrf -> csrf.csrfTokenRepository((CookieCsrfTokenRepository.withHttpOnlyFalse())))
////          .authorizeHttpRequests()
////          .anyRequest()
////          .permitAll()
//
//          .csrf((csrf) -> csrf
//            .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
//            .csrfTokenRequestHandler(new SpaCsrfTokenRequestHandler())
//          )
//          .addFilterAfter(new CsrfCookieFilter(), BasicAuthenticationFilter.class);
//
//        return http.build();
//    }
}

final class SpaCsrfTokenRequestHandler extends CsrfTokenRequestAttributeHandler {
    private final CsrfTokenRequestHandler delegate = new XorCsrfTokenRequestAttributeHandler();

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, Supplier<CsrfToken> csrfToken) {
        /*
         * Always use XorCsrfTokenRequestAttributeHandler to provide BREACH protection of
         * the CsrfToken when it is rendered in the response body.
         */
        this.delegate.handle(request, response, csrfToken);
    }

    @Override
    public String resolveCsrfTokenValue(HttpServletRequest request, CsrfToken csrfToken) {
        /*
         * If the request contains a request header, use CsrfTokenRequestAttributeHandler
         * to resolve the CsrfToken. This applies when a single-page application includes
         * the header value automatically, which was obtained via a cookie containing the
         * raw CsrfToken.
         */
        if (StringUtils.hasText(request.getHeader(csrfToken.getHeaderName()))) {
            return super.resolveCsrfTokenValue(request, csrfToken);
        }
        /*
         * In all other cases (e.g. if the request contains a request parameter), use
         * XorCsrfTokenRequestAttributeHandler to resolve the CsrfToken. This applies
         * when a server-side rendered form includes the _csrf request parameter as a
         * hidden input.
         */
        return this.delegate.resolveCsrfTokenValue(request, csrfToken);
    }
}

//final class CsrfCookieFilter extends OncePerRequestFilter {
//
//    @Override
//    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
//      throws ServletException, IOException {
//        CsrfToken csrfToken = (CsrfToken) request.getAttribute("_csrf");
//        // Render the token value to a cookie by causing the deferred token to be loaded
//        csrfToken.getToken();
//
//        // Cors before filter
//        response.setHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));
//        response.setHeader("Access-Control-Allow-Credentials", "true");
//        response.setHeader("Access-Control-Allow-Methods", "*");
//        response.setHeader("Access-Control-Allow-Headers", "*");
//        response.setHeader("Access-Control-Max-Age", "3600");
//
//        filterChain.doFilter(request, response);
//    }
//}

final class JwtAuthenticationFilter implements WebFilter {
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
        return chain.filter(exchange);
    }
}