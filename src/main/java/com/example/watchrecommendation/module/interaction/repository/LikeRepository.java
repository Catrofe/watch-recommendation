package com.example.watchrecommendation.module.interaction.repository;

import com.example.watchrecommendation.module.interaction.entity.Like;
import com.example.watchrecommendation.module.recommendation.entity.Recommendation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface LikeRepository extends JpaRepository<Like, Long> {

    @Query(value = "SELECT * from tb_likes as tb WHERE tb.user_id = :userId AND tb.recommendation_id = :recommendationId", nativeQuery = true)
    Like FindLike(Long userId, Long recommendationId);


}
