package com.test.demo.service;

import com.test.demo.domain.Speaker;
import com.test.demo.dto.SpeakerDto;

import java.util.List;
import java.util.UUID;

public interface SpeakerService {
    Speaker save(SpeakerDto professorDto);

    Speaker update(UUID id, SpeakerDto professorDto);

    Speaker findFullById(UUID id);

    List<SpeakerDto> findAll();

    void deleteById(UUID id);
}
