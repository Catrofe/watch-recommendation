package com.example.watchrecommendation.module.recommendation.service;

import com.example.watchrecommendation.module.recommendation.dto.RecommendationDto;
import com.example.watchrecommendation.module.recommendation.dto.RegisterNewRecommendation;
import com.example.watchrecommendation.module.recommendation.entity.Recommendation;
import com.example.watchrecommendation.module.recommendation.entity.TypeRecommendation;
import com.example.watchrecommendation.module.recommendation.repository.RecommendationRepository;
import com.example.watchrecommendation.module.streaming.dto.StreamingDto;
import com.example.watchrecommendation.module.streaming.entity.Streaming;
import com.example.watchrecommendation.module.streaming.service.StreamingService;
import com.example.watchrecommendation.module.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.record.RecordModule;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RecommendationService {

    private final RecommendationRepository repository;

    private final StreamingService streamingService;

    private final UserService userService;

    private final ModelMapper modelMapper = new ModelMapper().registerModule(new RecordModule());

    public RecommendationDto create(RegisterNewRecommendation body, Long id) {
        Recommendation newRecommendation = convertToEntity(body);
        newRecommendation.setUser(userService.getUserEntityById(id));
        newRecommendation.setStreaming(streamingService.getStreamingEntityById(body.streamingId()));
        newRecommendation.setType(TypeRecommendation.getTypeRecommendation(body.type()));

        return convertToDto(repository.save(newRecommendation));
    }

    public List<RecommendationDto> getAllRecommendation() {
        List<RecommendationDto> requests = new ArrayList<>();
        for (Recommendation recommendation : repository.findAll()) {
            requests.add(convertToDto(recommendation));
        }
        return requests;
    }

    public Recommendation convertToEntity(RegisterNewRecommendation recommendation) {
        return modelMapper.map(recommendation, Recommendation.class);
    }

    public RecommendationDto convertToDto(Recommendation recommendation) {
        RecommendationDto recommendationDto = modelMapper.map(recommendation, RecommendationDto.class);
        recommendationDto.setStreamingName(recommendation.getStreaming().getName());
        recommendationDto.setStreamingUrl(recommendation.getStreaming().getUrl());
        recommendationDto.setUserName(recommendation.getUser().getName());
        return recommendationDto;
    }



}
