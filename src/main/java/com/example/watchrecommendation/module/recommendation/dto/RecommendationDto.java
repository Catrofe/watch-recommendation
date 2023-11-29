package com.example.watchrecommendation.module.recommendation.dto;

import com.example.watchrecommendation.module.recommendation.entity.TypeRecommendation;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RecommendationDto {

    private Long id;
    private String title;
    private String description;
    private String url;
    private String genre;
    private TypeRecommendation type;
    private Long streamingId;
    private String streamingName;
    private String streamingUrl;
    private Long userId;
    private String userName;
    private Long likeCount;
    private Long dislikeCount;

}
