package com.example.watchrecommendation.module.recommendation.controller;

import com.example.watchrecommendation.module.recommendation.dto.RecommendationDto;
import com.example.watchrecommendation.module.recommendation.dto.RegisterNewRecommendation;
import com.example.watchrecommendation.module.recommendation.entity.Recommendation;
import com.example.watchrecommendation.module.recommendation.service.RecommendationService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/recommendation")
@RequiredArgsConstructor
public class RecommendationController {

    private final RecommendationService service;

    @PostMapping
    public ResponseEntity<RecommendationDto> create(@Valid @RequestBody RegisterNewRecommendation recommendation, HttpServletRequest request) {
        Long id = (Long) request.getAttribute("id");
        return new ResponseEntity<>(service.create(recommendation, id), HttpStatus.CREATED);
    }
}
