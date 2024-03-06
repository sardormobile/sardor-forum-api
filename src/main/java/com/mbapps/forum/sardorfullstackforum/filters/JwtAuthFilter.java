package com.mbapps.forum.sardorfullstackforum.filters;//package com.mbapps.forum.sardorfullstackforum.filters;

//import com.mbapps.forum.sardorfullstackforum.service.JwtService;
//import com.mbapps.forum.sardorfullstackforum.service.UserDetailsServiceImp;
//import jakarta.servlet.FilterChain;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import org.antlr.v4.runtime.misc.NotNull;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
//import org.springframework.stereotype.Component;
//import org.springframework.web.filter.OncePerRequestFilter;
//
//import java.io.IOException;
//
////@Component
//public class JwtAuthFilter extends OncePerRequestFilter {
//
//    private final JwtService jwtService;
////    private final UserDetailsServiceImp uerDetails;
//
//    public JwtAuthFilter(JwtService jwtService, UserDetailsServiceImp uerDetails) {
//        this.jwtService = jwtService;
////        this.uerDetails = uerDetails;
//    }
//
//    @Override
//    protected void doFilterInternal(
//            @NotNull HttpServletRequest request,
//            @NotNull HttpServletResponse response,
//            @NotNull FilterChain filterChain)
//            throws ServletException, IOException {
//
//        String authHeader = request.getHeader("Authorization");
//
//        if (authHeader == null || authHeader.startsWith("Bearer ")) {
//            filterChain.doFilter(request, response);
//            return;
//        }
//
//        String token = authHeader.substring(7);
//        String username = jwtService.extractUserName(token);
//
//        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
////            UserDetails userDetails = uerDetails.loadUserByUsername(username);
////            if (jwtService.isValid(token, userDetails)) {
////                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
////                        userDetails, null, userDetails.getAuthorities()
////                );
//
////                authToken.setDetails(
////                        new WebAuthenticationDetailsSource().buildDetails(request)
////                );
////                SecurityContextHolder.getContext().setAuthentication(authToken);
////            }
//        }
//        filterChain.doFilter(request, response);
//    }
//}
