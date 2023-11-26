package com.example.watchrecommendation.module.auth.service;

import com.example.watchrecommendation.module.auth.dto.LoginDto;
import com.example.watchrecommendation.module.auth.dto.LoginReturnSuccesDto;
import com.example.watchrecommendation.module.user.dto.UserDto;
import com.example.watchrecommendation.module.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserService userService;
    private final JwtService jwtService;

    public LoginReturnSuccesDto login(LoginDto loginDto) {
        UserDto userDto = userService.login(loginDto.login(), loginDto.password());
        return jwtService.createTokens(userDto);
    }
}
