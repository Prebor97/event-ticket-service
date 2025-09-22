package com.ticket.app.event_ticket_service.security;

import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtUtils jwtUtils;
    private static final Logger log = LoggerFactory.getLogger(JwtAuthenticationFilter.class);

    public JwtAuthenticationFilter(JwtUtils jwtUtils) {
        this.jwtUtils = jwtUtils;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {

        String header = request.getHeader("Authorization");

        if (header == null || !header.startsWith("Bearer ")) {
            log.warn("No bearer token found in the auth header");
            filterChain.doFilter(request, response);
            return;
        }

        String token = header.substring(7);
        log.info("JWT token: {}", token);
        try {
            Claims claims = jwtUtils.validateTokenAndGetClaims(token);


            if (jwtUtils.isTokenExpired(claims)) {
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                return;
            }

            String email = claims.getSubject();
            log.info("extracted email :{}", email);
            log.info("all claims:{}", claims);



            String role = claims.get("role", String.class);
            GrantedAuthority authority = new SimpleGrantedAuthority(role);
            String userId = jwtUtils.extractUserId(token);
            System.out.println(token);
            UsernamePasswordAuthenticationToken auth =
                    new UsernamePasswordAuthenticationToken(email, null, List.of(authority));

            Map<String, Object> userDetailsMap = new HashMap<>();
            userDetailsMap.put("userId", claims.get("userId"));
            userDetailsMap.put("firstName", claims.get("firstName"));
            userDetailsMap.put("email", claims.getSubject());
            userDetailsMap.put("role", claims.get("role"));



            auth.setDetails(userDetailsMap);
            SecurityContextHolder.getContext().setAuthentication(auth);
            log.info("Authenticated user: {} with role: {}", email, role);
            log.info("Authorities in SecurityContext: {}", auth.getAuthorities());

            // log.debug("Authenticated user: {} with role: {}", email, role);

        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            log.error("JWT validation failed: {}", e.getMessage());
            return;
        }

        filterChain.doFilter(request, response);
    }


}
