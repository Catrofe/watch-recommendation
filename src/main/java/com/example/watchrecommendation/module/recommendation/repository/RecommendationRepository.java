package com.example.watchrecommendation.module.recommendation.repository;

import com.example.watchrecommendation.module.recommendation.entity.Recommendation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecommendationRepository extends JpaRepository<Recommendation, Long> {
}
