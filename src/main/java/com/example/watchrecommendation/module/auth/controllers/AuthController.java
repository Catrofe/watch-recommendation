package com.example.watchrecommendation.module.auth.controllers;

import com.example.watchrecommendation.module.auth.dto.LoginDto;
import com.example.watchrecommendation.module.auth.dto.LoginReturnSuccesDto;
import com.example.watchrecommendation.module.auth.service.AuthService;
import com.example.watchrecommendation.module.user.dto.UserDto;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/api")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<LoginReturnSuccesDto> login(@Valid @RequestBody LoginDto loginDto) {
        return new ResponseEntity<>(authService.login(loginDto), HttpStatus.OK);
    }

    @PostMapping("/refresh")
    public ResponseEntity<LoginReturnSuccesDto> refresh(@RequestHeader("Authorization") String refreshToken) {
        return new ResponseEntity<>(authService.refresh(refreshToken), HttpStatus.OK);
    }

}
