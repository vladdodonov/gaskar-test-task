package com.test.demo.repository;

import com.test.demo.domain.Report;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface ReportRepo extends JpaRepository<Report, UUID> {
    Optional<Report> findFullById(UUID id);
}
