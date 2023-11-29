package com.example.watchrecommendation.module.interaction.service;

import com.example.watchrecommendation.module.interaction.dto.NewLikeSave;
import com.example.watchrecommendation.module.interaction.entity.Like;
import com.example.watchrecommendation.module.interaction.repository.LikeRepository;
import com.example.watchrecommendation.module.recommendation.service.RecommendationService;
import com.example.watchrecommendation.module.user.service.UserService;
import com.example.watchrecommendation.module.utils.exceptions.BadRequestException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class InteractionService {

    private final LikeRepository likeRepository;
    private final RecommendationService recommendationService;
    private final UserService userService;


    public void like(NewLikeSave newLikeSave, Long id) {
        Like like = new Like();
        like.setIsLike(newLikeSave.like());
        like.setRecommendation((recommendationService.getRecommendationEntityById(newLikeSave.recommendationId())));
        like.setUser(userService.getUserEntityById(id));
        likeRepository.save(like);
    }

    public void updateLike(NewLikeSave newLikeSave, Long userId) {
        Like like = likeRepository.FindLike(userId, newLikeSave.recommendationId());
        like.setIsLike(newLikeSave.like());
        likeRepository.save(like);
    }

    public void deleteLike(Long likeId, Long userId) {
        Like like = likeRepository.findById(likeId).orElseThrow();
        if (!userId.equals(like.getUser().getId())) {
            throw new BadRequestException("You are not allowed to delete this like");
        }
        likeRepository.delete(like);
    }
}
