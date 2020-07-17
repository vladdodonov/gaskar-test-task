package com.test.demo.controller;

import com.test.demo.dto.ErrorDto;
import com.test.demo.dto.SpeakerDto;
import com.test.demo.service.SpeakerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

import static com.test.demo.util.PathConstant.API_V1;
import static com.test.demo.util.PathConstant.SPEAKER;

@RestController
@RequestMapping(API_V1 + SPEAKER)
public class SpeakerController {
    private final ModelMapper modelMapper;
    private final SpeakerService speakerService;

    @Autowired
    public SpeakerController(ModelMapper modelMapper, SpeakerService speakerService) {
        this.modelMapper = modelMapper;
        this.speakerService = speakerService;
    }

    @PostMapping
    @Operation(
            summary = "Метод для создания спикера",
            responses = {
                    @ApiResponse(responseCode = "200",
                            description = "Возвращается созданный объект спикера",
                            content = {
                                    @Content(
                                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                                            schema = @Schema(implementation = SpeakerDto.class)
                                    )
                            }),
                    @ApiResponse(responseCode = "500",
                            description = "В случае ошибки при сохранении",
                            content = {
                                    @Content(
                                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                                            schema = @Schema(implementation = ErrorDto.class)
                                    )
                            })
            }
    )
    public ResponseEntity<SpeakerDto> create(@RequestBody SpeakerDto speakerDto) {
        return ResponseEntity.status(HttpStatus.OK).body(modelMapper.map(speakerService.save(speakerDto), SpeakerDto.class));
    }

    @PutMapping("/{id}")
    @Operation(
            summary = "Метод для обновления спикера",
            responses = {
                    @ApiResponse(responseCode = "200",
                            description = "Возвращается обновленный объект профессора",
                            content = {
                                    @Content(
                                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                                            schema = @Schema(implementation = SpeakerDto.class)
                                    )
                            }),
                    @ApiResponse(responseCode = "500",
                            description = "В случае ошибки при сохранении",
                            content = {
                                    @Content(
                                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                                            schema = @Schema(implementation = ErrorDto.class)
                                    )
                            })
            }
    )
    public ResponseEntity<SpeakerDto> update(@PathVariable(name = "id") UUID id, @RequestBody SpeakerDto speakerDto) {
        return ResponseEntity.status(HttpStatus.OK).body(modelMapper.map(speakerService.update(id, speakerDto), SpeakerDto.class));
    }

    @GetMapping("{id}")
    @Operation(
            summary = "Метод для поиска спикера по id",
            responses = {
                    @ApiResponse(responseCode = "200",
                            description = "Возвращается найденный объект спикера",
                            content = {
                                    @Content(
                                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                                            schema = @Schema(implementation = SpeakerDto.class)
                                    )
                            }),
                    @ApiResponse(responseCode = "500",
                            description = "В случае ошибки",
                            content = {
                                    @Content(
                                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                                            schema = @Schema(implementation = ErrorDto.class)
                                    )
                            })
            }
    )
    public ResponseEntity<SpeakerDto> findById(@PathVariable(name = "id") UUID id) {
        return ResponseEntity.status(HttpStatus.OK).body(modelMapper.map(speakerService.findFullById(id), SpeakerDto.class));
    }

    @GetMapping
    @Operation(
            summary = "Метод для поиска всех спикеров",
            responses = {
                    @ApiResponse(responseCode = "200",
                            description = "Возвращаются спикеры",
                            content = {
                                    @Content(
                                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                                            schema = @Schema(implementation = SpeakerDto.class)
                                    )
                            }),
                    @ApiResponse(responseCode = "500",
                            description = "В случае ошибки",
                            content = {
                                    @Content(
                                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                                            schema = @Schema(implementation = ErrorDto.class)
                                    )
                            })
            }
    )
    public ResponseEntity<List<SpeakerDto>> findAll() {
        return ResponseEntity.status(HttpStatus.OK).body(speakerService.findAll());
    }

    @DeleteMapping("/{id}")
    @Operation(
            summary = "Метод для удаления спикера по идентификатору",
            responses = {
                    @ApiResponse(responseCode = "204",
                            description = "Возвращается статус NO_CONTENT"),
                    @ApiResponse(responseCode = "404",
                            description = "Если не найден спикер с указанным ID",
                            content = {
                                    @Content(
                                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                                            schema = @Schema(implementation = ErrorDto.class)
                                    )
                            })
            }
    )
    public ResponseEntity<HttpStatus> deleteById(@PathVariable(name = "id") UUID id) {
        speakerService.deleteById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
