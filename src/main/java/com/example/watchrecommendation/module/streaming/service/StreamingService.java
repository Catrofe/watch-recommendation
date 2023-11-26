package com.example.watchrecommendation.module.streaming.service;

import com.example.watchrecommendation.module.streaming.dto.SaveStreaming;
import com.example.watchrecommendation.module.streaming.dto.StreamingDto;
import com.example.watchrecommendation.module.streaming.entity.Streaming;
import com.example.watchrecommendation.module.streaming.repository.StreamingRepository;
import com.example.watchrecommendation.module.user.repository.UserRepository;
import com.example.watchrecommendation.module.utils.exceptions.ConflictException;
import com.example.watchrecommendation.module.utils.exceptions.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.modelmapper.ModelMapper;
import org.modelmapper.record.RecordModule;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StreamingService {

    private final StreamingRepository repository;
    private final UserRepository userRepository;

    private final ModelMapper modelMapper = new ModelMapper().registerModule(new RecordModule());

    public StreamingDto insert(SaveStreaming streamingDto, Long id) throws ConflictException {
        validNewStreaming(streamingDto);
        Streaming streaming = covertToEntity(streamingDto);
        streaming.setUser(userRepository.findById(id).orElseThrow(() -> new NotFoundException("User not yet registered")));
        return convertToDto(repository.save(streaming));
    }

    private void validNewStreaming(SaveStreaming streamingDto) throws ConflictException {
        Streaming streaming = repository.streamingAlreadyExists(streamingDto.name(), streamingDto.url());
        if (streaming != null) {
            throw new ConflictException("Streaming already exists");
        }
    }

    private Streaming covertToEntity(SaveStreaming streaming){
        return modelMapper.map(streaming, Streaming.class);
    }

    private StreamingDto convertToDto(Streaming streaming){
        return modelMapper.map(streaming, StreamingDto.class);
    }

    public List<StreamingDto> getAllStreaming() {
        List<StreamingDto> requests = new ArrayList<>();
        for (Streaming streaming : repository.findAll()) {
            requests.add(convertToDto(streaming));
        }
        return requests;
    }

    public StreamingDto getStreamingById(Long id) {
        Streaming streaming = repository.findById(id).orElseThrow(() -> new NotFoundException("Streaming not found"));
        return convertToDto(streaming);
    }
}
