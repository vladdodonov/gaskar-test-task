package com.test.demo.repository;

import com.test.demo.domain.Speaker;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface SpeakerRepo extends JpaRepository<Speaker, UUID> {
    @EntityGraph(attributePaths = {"reports"})
    Optional<Speaker> findFullById(UUID id);
}
