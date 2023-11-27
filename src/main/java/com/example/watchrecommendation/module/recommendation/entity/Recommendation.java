package com.example.watchrecommendation.module.recommendation.entity;

import com.example.watchrecommendation.module.streaming.entity.Streaming;
import com.example.watchrecommendation.module.user.entity.User;
import jakarta.persistence.*;
import lombok.Data;

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

    @ManyToOne
    private User user;

    @ManyToOne
    private Streaming streaming;

}
