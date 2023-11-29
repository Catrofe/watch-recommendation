package com.example.watchrecommendation.module.interaction.entity;

import com.example.watchrecommendation.module.recommendation.entity.Recommendation;
import com.example.watchrecommendation.module.user.entity.User;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "tb_likes")
public class Like {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private User user;

    @ManyToOne
    private Recommendation recommendation;

    private Boolean isLike;
}
