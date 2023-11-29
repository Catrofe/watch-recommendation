package com.example.watchrecommendation.module.interaction.controller;

import com.example.watchrecommendation.module.interaction.dto.NewLikeSave;
import com.example.watchrecommendation.module.interaction.service.InteractionService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/interaction")
public class InteractionController {

    private final InteractionService service;

    @PostMapping("/like")
    public ResponseEntity<Void> like(@RequestBody NewLikeSave newLikeSave, HttpServletRequest request) {
        Long id = (Long) request.getAttribute("id");
        service.like(newLikeSave, id);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/like")
    public ResponseEntity<Void> updateLike(@RequestBody NewLikeSave newLikeSave, HttpServletRequest request) {
        Long id = (Long) request.getAttribute("id");
        service.updateLike(newLikeSave, id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/like/{recommendationId}")
    public ResponseEntity<Void> deleteLike(@PathVariable Long recommendationId, HttpServletRequest request) {
        Long userId = (Long) request.getAttribute("id");
        service.deleteLike(recommendationId, userId);
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
