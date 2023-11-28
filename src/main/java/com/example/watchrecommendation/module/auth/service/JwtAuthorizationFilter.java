package com.example.watchrecommendation.module.auth.service;

import com.example.watchrecommendation.module.user.service.UserDetailsServiceImpl;
import com.example.watchrecommendation.module.utils.exceptions.UnauthorizedException;
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
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException, UnauthorizedException {
        String rawToken = request.getHeader("Authorization");
        if (rawToken != null) {
            String token = rawToken.replace("Bearer ", "");
            UsernamePasswordAuthenticationToken authenticationToken = getAuthentication(token, request.getRequestURI());
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            request.setAttribute("id", jwtService.getId(token));
        }
        filterChain.doFilter(request, response);

    }

    private UsernamePasswordAuthenticationToken getAuthentication(String token, String uri) throws UnauthorizedException {
        Boolean isRefresh = uri.equals("/api/refresh");
        UserDetails userDetails = userService.loadUserByUsername(jwtService.getEmail(token));
        jwtService.isTokenValid(token, userService.findById(jwtService.getId(token)), isRefresh);
        return new UsernamePasswordAuthenticationToken(userDetails.getUsername(), null, userDetails.getAuthorities());


    }
}
