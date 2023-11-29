package com.example.watchrecommendation.module.recommendation.entity;

import com.example.watchrecommendation.module.interaction.entity.Like;
import com.example.watchrecommendation.module.streaming.entity.Streaming;
import com.example.watchrecommendation.module.user.entity.User;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Collection;
import java.util.List;

@Entity
@Data
public class Recommendation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String description;
    private String url;
    private String genre;
    private TypeRecommendation type;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "recommendation")
    private List<Like> like;

    @ManyToOne
    private User user;

    @ManyToOne
    private Streaming streaming;
}
