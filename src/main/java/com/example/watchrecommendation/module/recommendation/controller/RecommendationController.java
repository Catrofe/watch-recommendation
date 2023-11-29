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
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping
    public ResponseEntity<List<RecommendationDto>> getAllRecommendation() {
        return new ResponseEntity<>(service.getAllRecommendation(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RecommendationDto> getRecommendationById(@PathVariable Long id) {
        return new ResponseEntity<>(service.getRecommendationById(id), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<RecommendationDto> update(@Valid @RequestBody RegisterNewRecommendation recommendation, HttpServletRequest request) {
        Long id = (Long) request.getAttribute("id");
        return new ResponseEntity<>(service.update(recommendation, id), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id, HttpServletRequest request) {
        Long userId = (Long) request.getAttribute("id");
        service.delete(id, userId);
        return new ResponseEntity<>("Recommendation deleted successfully", HttpStatus.OK);
    }

}
