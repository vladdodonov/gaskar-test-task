package com.test.demo.config;

import com.test.demo.domain.Report;
import com.test.demo.domain.Speaker;
import com.test.demo.dto.ReportDto;
import com.test.demo.dto.SpeakerDto;
import org.modelmapper.Condition;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Configuration
public class ModelMapperConfig {
    @Bean
    public ModelMapper getModelMapper() {
        ModelMapper modelMapper = new ModelMapper();

        Converter<List<ReportDto>, Set<Report>> dtosToReportsConverter = mappingContext -> {
            List<ReportDto> dtos = mappingContext.getSource();
            Set<Report> reports = null;
            if (mappingContext.getSource() != null && !mappingContext.getSource().isEmpty()) {
                reports = new HashSet<>();
                for (ReportDto dto : dtos) {
                    Report report = modelMapper.map(dto, Report.class);
                    reports.add(report);
                }
            }
            if (reports == null || reports.isEmpty()) {
                return Collections.emptySet();
            } else {
                return reports;
            }
        };

        Converter<Set<Report>, List<ReportDto>> reportToDtosConverter = mappingContext -> {
            List<ReportDto> dtos = null;
            Set<Report> reports = mappingContext.getSource();
            if (mappingContext.getSource() != null && !mappingContext.getSource().isEmpty()) {
                dtos = new ArrayList<>();
                for (Report report : reports) {
                    ReportDto dto = modelMapper.map(report, ReportDto.class);
                    dtos.add(dto);
                }
            }
            if (dtos == null || dtos.isEmpty()) {
                return Collections.emptyList();
            } else {
                return dtos;
            }
        };

        Converter<UUID, Speaker> speakerConverter = mappingContext -> {
            if (mappingContext.getSource() != null) {
                Speaker speaker = new Speaker();
                speaker.setId(mappingContext.getSource());
                return speaker;
            } else {
                return null;
            }
        };

        Condition<?, ?> isFieldNull = (Condition<Object, Object>) context -> context.getSource() == null;

        PropertyMap<SpeakerDto, Speaker> dtoToSpeakerMap = new PropertyMap<SpeakerDto, Speaker>() {
            @Override
            protected void configure() {
                using(dtosToReportsConverter).map(source.getReports(), destination.getReports());
                when(isFieldNull).skip(source.getId(), destination.getId());
            }
        };

        PropertyMap<Speaker, SpeakerDto> speakerToSpeakerDtoMap = new PropertyMap<Speaker, SpeakerDto>() {
            @Override
            protected void configure() {
                using(reportToDtosConverter).map(source.getReports(), destination.getReports());
            }
        };

        PropertyMap<ReportDto, Report> dtoToReportMap = new PropertyMap<ReportDto, Report>() {
            @Override
            protected void configure() {
                when(isFieldNull).skip(source.getId(), destination.getId());
                using(speakerConverter).map(source.getSpeakerId()).setSpeaker(null);
            }
        };


        modelMapper.addMappings(dtoToSpeakerMap);
        modelMapper.addMappings(speakerToSpeakerDtoMap);
        modelMapper.addMappings(dtoToReportMap);

        return modelMapper;
    }
}
