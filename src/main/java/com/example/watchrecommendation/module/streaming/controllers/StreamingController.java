package com.example.watchrecommendation.module.streaming.controllers;

import com.example.watchrecommendation.module.streaming.dto.SaveStreaming;
import com.example.watchrecommendation.module.streaming.dto.StreamingDto;
import com.example.watchrecommendation.module.streaming.service.StreamingService;
import com.example.watchrecommendation.module.utils.exceptions.BadRequestException;
import com.example.watchrecommendation.module.utils.exceptions.ConflictException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/streaming")
public class StreamingController {

    private final StreamingService service;

    @PostMapping
    public ResponseEntity<StreamingDto> save(@RequestBody @Valid SaveStreaming streamingDto, HttpServletRequest request) throws ConflictException {
        Long id = (Long) request.getAttribute("id");
        StreamingDto streaming = service.insert(streamingDto, id);
        return new ResponseEntity<>(streaming, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<StreamingDto>> getAllStreaming(){
        List<StreamingDto> streaming = service.getAllStreaming();
        return new ResponseEntity<>(streaming, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<StreamingDto> getStreamingById(@PathVariable Long id){
        StreamingDto streaming = service.getStreamingById(id);
        return new ResponseEntity<>(streaming, HttpStatus.OK);
    }

    @GetMapping("/filter")
    public ResponseEntity<List<StreamingDto>> getStreamingByFilter(@RequestParam(required = false) String name){
        List<StreamingDto> streaming = service.getStreamingByFilter(name);
        return new ResponseEntity<>(streaming, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStreamingById(@PathVariable Long id, HttpServletRequest request) throws BadRequestException {
        Long id_user = (Long) request.getAttribute("id");
        service.deleteStreamingById(id, id_user);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/{id}")
    public ResponseEntity<StreamingDto> updateStreamingById(@PathVariable Long id, @RequestBody @Valid SaveStreaming streamingDto, HttpServletRequest request) throws BadRequestException {
        Long id_user = (Long) request.getAttribute("id");
        StreamingDto streaming = service.updateStreamingById(id, streamingDto, id_user);
        return new ResponseEntity<>(streaming, HttpStatus.OK);
    }


}
