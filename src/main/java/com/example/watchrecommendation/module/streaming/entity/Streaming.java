package com.example.watchrecommendation.module.streaming.entity;

import com.example.watchrecommendation.module.user.entity.User;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Streaming {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String name;
    @Column(unique = true)
    private String url;

    @ManyToOne
    private User user;

}
