package com.mbapps.forum.sardorfullstackforum.filters;

import com.mbapps.forum.sardorfullstackforum.service.jwt.JwtService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Objects;

@Component
public class JwtValidationFilter extends OncePerRequestFilter {
    @Autowired
    JwtService jwtService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            String jwt = getJWT(request);
            System.out.println("request jwt: " + jwt);
            if (Objects.nonNull(jwt)) {
                UsernamePasswordAuthenticationToken auth = (UsernamePasswordAuthenticationToken) jwtService.validateJwt(jwt);
                auth.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(auth);
            }
        } catch (Exception e) {
            System.err.println("Exception will processing the JWT in doFilterInternal: " + e.getMessage());
        }
        filterChain.doFilter(request, response);
    }

    private String getJWT(HttpServletRequest request) {
        String jwt = request.getHeader("Authorization");
        if (Objects.nonNull(jwt) && jwt.startsWith("Bearer ") && jwt.length() > 7) {
            return jwt.substring(7);
        }
        return null;
    }
}
