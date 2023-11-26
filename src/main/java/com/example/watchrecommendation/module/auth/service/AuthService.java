package com.example.watchrecommendation.module.auth.service;

import com.example.watchrecommendation.module.auth.dto.LoginDto;
import com.example.watchrecommendation.module.auth.dto.LoginReturnSuccesDto;
import com.example.watchrecommendation.module.user.dto.UserDto;
import com.example.watchrecommendation.module.user.repository.UserRepository;
import com.example.watchrecommendation.module.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserService userService;

    public UserDto login(LoginDto loginDto) {
        UserDto userDto = userService.login(loginDto.login(), loginDto.password());
        return userDto; //TODO Implementar geração de token
    }
}
