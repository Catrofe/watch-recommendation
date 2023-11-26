package com.example.watchrecommendation.module.auth.service;

import com.example.watchrecommendation.module.user.service.UserDetailsServiceImpl;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@RequiredArgsConstructor
@AllArgsConstructor
public class JwtAuthorizationFilter extends OncePerRequestFilter {

    private JwtService jwtService;
    private UserDetailsServiceImpl userService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        request.setAttribute("Authorization", "");
        String token = request.getHeader("Authorization");
        if (token != null) {
            UsernamePasswordAuthenticationToken authenticationToken = getAuthentication(token, request.getRequestURI());
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            request.setAttribute("id", jwtService.getId(token));
        }
        filterChain.doFilter(request, response);

    }

    private UsernamePasswordAuthenticationToken getAuthentication(String token, String uri) {
        token = token.replace("Bearer ", "");
        if (uri.equals("/api/refresh")){
            jwtService.isRefreshTokenValid(token);
            UserDetails userDetails = userService.loadUserByUsername(jwtService.getEmail(token));
            return new UsernamePasswordAuthenticationToken(userDetails.getUsername(), null, userDetails.getAuthorities());
        }
        jwtService.isTokenValid(token);
        UserDetails userDetails = userService.loadUserByUsername(jwtService.getEmail(token));
        return new UsernamePasswordAuthenticationToken(userDetails.getUsername(), null, userDetails.getAuthorities());


    }
}
