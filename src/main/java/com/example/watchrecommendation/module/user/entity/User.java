package com.example.watchrecommendation.module.user.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@Table(name = "tb_users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Column(unique = true)
    private String cpf;
    @Column(unique = true)
    private String email;
    private String password;
    @Column(unique = true)
    private String phone;
}
