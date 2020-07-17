package com.test.demo.service;

import com.test.demo.domain.Report;
import com.test.demo.dto.ReportDto;

import java.util.List;
import java.util.UUID;

public interface ReportService {
    Report save(ReportDto reportDto);

    Report update(UUID id, ReportDto reportDto);

    Report findFullById(UUID id);

    List<ReportDto> findAll();

    void deleteById(UUID id);
}
