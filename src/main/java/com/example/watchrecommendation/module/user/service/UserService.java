package com.example.watchrecommendation.module.user.service;

import com.example.watchrecommendation.module.user.dto.UserDto;
import com.example.watchrecommendation.module.user.dto.UserEditDTO;
import com.example.watchrecommendation.module.user.dto.UserRegister;
import com.example.watchrecommendation.module.user.entity.User;
import com.example.watchrecommendation.module.user.repository.UserRepository;
import com.example.watchrecommendation.module.utils.exceptions.ConflictException;
import com.example.watchrecommendation.module.utils.exceptions.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.record.RecordModule;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    private final ModelMapper modelMapper = new ModelMapper().registerModule(new RecordModule());

    private final PasswordEncoder encoder;

    public UserDto insert(UserRegister userRegister) {
        validNewUser(userRegister);
        User user = covertToEntity(userRegister);
        user.setPassword(encryptPassword(user.getPassword()));
        return convertToDto(userRepository.save(user));
    }

    public UserDto findById(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new NotFoundException("User not yet registered"));
        return convertToDto(user);
    }

    public UserDto update(Long id, UserEditDTO userEdit) {
        User user = userRepository.findById(id).orElseThrow(() -> new NotFoundException("User not yet registered"));
        if (userEdit.name() != null){
            user.setName(userEdit.name());
        }
        if (userEdit.email() != null){
            user.setEmail(userEdit.email());
        }
        if (userEdit.phone() != null){
            user.setPhone(userEdit.phone());
        }
        if (userEdit.password() != null){
            if (!passwordMatch(userEdit.password(), user.getPassword())){
                user.setPassword(encryptPassword(userEdit.password()));
            }
        }
        return convertToDto(userRepository.save(user));
    }


    private void validNewUser(UserRegister userRegister){
        User user = userRepository.UserAlreadyExists(userRegister.cpf(), userRegister.email(), userRegister.phone());
        if (user != null){
            throw new ConflictException("Some of the data entered is already registered in our database.");
        }
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

    private User saveNewAssignatureToken(User user){
        user.setTokenAssignature(returnNewTokenAssignature(user));
        user.setRefreshTokenAssignature(returnNewTokenAssignature(user));
        return userRepository.save(user);
    }

    public UserDto login(String login, String password) {
        User user = userRepository.findByLogin(login);
        if (user == null || !passwordMatch(password, user.getPassword())) {
            throw new NotFoundException("User not yet registered");
        }
        return convertToDto(saveNewAssignatureToken(user));
    }

    public UserDto refreshToken(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new NotFoundException("User not yet registered"));
        return convertToDto(saveNewAssignatureToken(user));
    }

    public User getUserEntityById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new NotFoundException("User not yet registered"));
    }

    private String returnNewTokenAssignature(User user){
        UUID uuid = UUID.randomUUID();
        return uuid.toString().replace("-", "");
    }

}
