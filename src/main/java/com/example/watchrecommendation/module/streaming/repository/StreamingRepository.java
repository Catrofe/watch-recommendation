package com.example.watchrecommendation.module.streaming.repository;

import com.example.watchrecommendation.module.streaming.entity.Streaming;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface StreamingRepository extends JpaRepository<Streaming, Long> {

    @Query(value = "SELECT * FROM streaming WHERE name = :name OR url = :url", nativeQuery = true)
    Streaming streamingAlreadyExists(String name, String url);

}
