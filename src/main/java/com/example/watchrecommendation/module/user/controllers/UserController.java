package com.example.watchrecommendation.module.user.controllers;

import com.example.watchrecommendation.module.user.dto.UserDto;
import com.example.watchrecommendation.module.user.dto.UserEditDTO;
import com.example.watchrecommendation.module.user.dto.UserRegister;
import com.example.watchrecommendation.module.user.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/user")
public class UserController {

    private final UserService service;


    @PostMapping
    public ResponseEntity<UserDto> save(@RequestBody @Valid UserRegister userRegister){
        UserDto user = service.insert(userRegister);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> findById(@PathVariable Long id){
        UserDto user = service.findById(id);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDto> update(@PathVariable Long id, @RequestBody @Valid UserEditDTO userEdit){
        UserDto user = service.update(id, userEdit);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

}
