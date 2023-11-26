package com.example.watchrecommendation.module.user.service;

import com.example.watchrecommendation.module.user.repository.UserRepository;
import com.example.watchrecommendation.module.utils.exceptions.UnauthorizedException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return (UserDetails) userRepository
                .findByEmail(username)
                .orElseThrow(() ->
                        new UnauthorizedException("User not found with email: " + username));
    }
}
