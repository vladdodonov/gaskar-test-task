package com.test.demo.service.impl;

import com.test.demo.domain.Report;
import com.test.demo.dto.ReportDto;
import com.test.demo.repository.ReportRepo;
import com.test.demo.service.ReportService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.UUID;

@Service
public class ReportServiceImpl implements ReportService {
    private final ReportRepo reportRepo;
    private final ModelMapper modelMapper;

    @Autowired
    public ReportServiceImpl(ReportRepo reportRepo, ModelMapper modelMapper) {
        this.reportRepo = reportRepo;
        this.modelMapper = modelMapper;
    }


    @Transactional
    @Override
    public Report save(ReportDto reportDto) {
        Report report = modelMapper.map(reportDto, Report.class);
        return reportRepo.save(report);
    }

    @Transactional
    @Override
    public Report update(UUID id, ReportDto reportDto) {
        Report fromDb = reportRepo.findFullById(id).orElseThrow(() -> new EntityNotFoundException(id.toString()));
        modelMapper.map(reportDto, fromDb);
        return reportRepo.save(fromDb);
    }

    @Override
    public Report findFullById(UUID id) {
        return reportRepo.findFullById(id).orElseThrow(() -> new EntityNotFoundException(id.toString()));
    }

    @Override
    public List<ReportDto> findAll() {
        List<Report> reports = reportRepo.findAll();
        return modelMapper.map(reports,
                new TypeToken<List<ReportDto>>() {
                }.getType());
    }

    @Transactional
    @Override
    public void deleteById(UUID id) {
        Report fromDb = findFullById(id);
        fromDb.setSpeaker(null);
        reportRepo.deleteById(id);
    }
}
