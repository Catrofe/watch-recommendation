package com.example.watchrecommendation.module.streaming.repository;

import com.example.watchrecommendation.module.streaming.entity.Streaming;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface StreamingRepository extends JpaRepository<Streaming, Long> {

    @Query(value = "SELECT * FROM streaming WHERE name = :name OR url = :url", nativeQuery = true)
    Streaming streamingAlreadyExists(String name, String url);

    @Query("SELECT s FROM Streaming s WHERE UPPER(s.name) LIKE UPPER(:name)")
    List<Streaming> findByNameIgnoreCaseContaining(@Param("name") String name);
}
