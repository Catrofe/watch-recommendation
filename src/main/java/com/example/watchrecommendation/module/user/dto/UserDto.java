package com.example.watchrecommendation.module.user.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserDto {
    Long id;
    String name;
    String cpf;
    String email;
    String phone;
    @JsonIgnore
    String tokenAssignature;
    @JsonIgnore
    String refreshTokenAssignature;

}
