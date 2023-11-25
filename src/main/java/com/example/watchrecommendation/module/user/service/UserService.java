package com.example.watchrecommendation.module.user.service;

import com.example.watchrecommendation.module.user.dto.UserDto;
import com.example.watchrecommendation.module.user.dto.UserRegister;
import com.example.watchrecommendation.module.user.entity.User;
import com.example.watchrecommendation.module.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.record.RecordModule;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    private final ModelMapper modelMapper = new ModelMapper().registerModule(new RecordModule());

    private final PasswordEncoder encoder;

    public UserDto insert(UserRegister userRegister) {
        User user = covertToEntity(userRegister);
        user.setPassword(encryptPassword(user.getPassword()));
        return convertToDto(userRepository.save(user));
    }

    private String encryptPassword(String password){
        return encoder.encode(password);
    }

    private boolean passwordMatch(String password, String encodedPassword){
        return encoder.matches(password, encodedPassword);
    }

    private User covertToEntity(UserRegister userDto){
        return modelMapper.map(userDto, User.class);
    }

    private UserDto convertToDto(User user){
        return modelMapper.map(user, UserDto.class);
    }

    public UserDto findById(Long id) {
        User user = userRepository.findById(id).orElseThrow();
        return convertToDto(user);
    }
}
