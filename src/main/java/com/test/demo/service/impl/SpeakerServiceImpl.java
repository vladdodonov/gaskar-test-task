package com.test.demo.service.impl;

import com.test.demo.domain.Speaker;
import com.test.demo.dto.SpeakerDto;
import com.test.demo.repository.SpeakerRepo;
import com.test.demo.service.SpeakerService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.UUID;

@Service
public class SpeakerServiceImpl implements SpeakerService {
    private final SpeakerRepo speakerRepo;
    private final ModelMapper modelMapper;

    @Autowired
    public SpeakerServiceImpl(SpeakerRepo speakerRepo, ModelMapper modelMapper) {
        this.speakerRepo = speakerRepo;
        this.modelMapper = modelMapper;
    }

    @Transactional
    @Override
    public Speaker save(SpeakerDto speakerDto) {
        Speaker speaker = modelMapper.map(speakerDto, Speaker.class);
        speaker =  speakerRepo.save(speaker);
        Speaker fromDb = findFullById(speaker.getId());
        fromDb.getReports().forEach(report -> report.setSpeaker(fromDb));
        return speakerRepo.save(fromDb);
    }

    @Transactional
    @Override
    public Speaker update(UUID id, SpeakerDto speakerDto) {
        Speaker fromDb = speakerRepo.findFullById(id).orElseThrow(() -> new EntityNotFoundException(id.toString()));
        modelMapper.map(speakerDto, fromDb);
        speakerRepo.save(fromDb);
        return findFullById(id);
    }

    @Override
    public Speaker findFullById(UUID id) {
        return speakerRepo.findFullById(id).orElseThrow(() -> new EntityNotFoundException(id.toString()));
    }

    @Override
    public List<SpeakerDto> findAll() {
        List<Speaker> page = speakerRepo.findAll();
        return modelMapper.map(page,
                new TypeToken<List<SpeakerDto>>() {
                }.getType());
    }

    @Transactional
    @Override
    public void deleteById(UUID id) {
        Speaker speaker = findFullById(id);
        speaker.getReports().forEach(report -> report.setSpeaker(null));
        speakerRepo.deleteById(id);
    }
}
