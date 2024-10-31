package com.tutget.tutgetmain.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

@Component
public class JwtAuthenticationFilter implements WebFilter {

    private final Logger LOGGER = LoggerFactory.getLogger(JwtAuthenticationFilter.class);

    private final String AUTHORIZATION = "Authorization";

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
        String requestToken = exchange.getRequest().getHeaders().getFirst(AUTHORIZATION);

        String token = null;

        if (requestToken != null && requestToken.startsWith("Bearer")) {
            token = requestToken.substring(7);

//            if (jwtTokenHelper.validateToken(token) && SecurityContextHolder.getContext().getAuthentication() == null) {
//
//                UserDetails user = jwtTokenHelper.extractPayloadFromToken(token);
//
//                List<SimpleGrantedAuthority> authorities = user.getAuthorities().stream()
//                        .map((role) -> new SimpleGrantedAuthority("ROLE_" + role)).toList();
//
//                UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
//                        user, null, authorities);
//
//                SecurityContext context = new SecurityContextImpl(authenticationToken);
//
//                UserResponse principal = jwtTokenHelper
//                        .extractPayloadFromToken((UserResponse) context.getAuthentication().getPrincipal());
//
//                // Set the Principal header in the request
//                ServerHttpRequest modifiedRequest = exchange.getRequest().mutate()
//                        .header("userId", principal.getUserId()) // Assuming username is appropriate for Principal
//                        .header("email", principal.getEmail()) // Assuming username is appropriate for Principal
//                        .header("role", principal.getRole()) // Assuming username is appropriate for Principal
//                        .build();
//
//                // Create a new ServerWebExchange with the modified request
//                ServerWebExchange modifiedExchange = exchange.mutate()
//                        .request(modifiedRequest)
//                        .build();
//
//                return chain.filter(modifiedExchange).contextWrite(
//                        ReactiveSecurityContextHolder.withSecurityContext(Mono.just(context)));
//            } else {
//                LOGGER.error("TOKEN IS MALFORMED OR EXPIRED");
//            }
        } else {
            LOGGER.error("TOKEN NOT FOUND");
        }

        return chain.filter(exchange);
    }
}
