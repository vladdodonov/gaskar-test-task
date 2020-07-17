package com.test.demo.controller;

import com.test.demo.dto.ErrorDto;
import com.test.demo.dto.ReportDto;
import com.test.demo.service.ReportService;
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
import static com.test.demo.util.PathConstant.REPORT;


@RestController
@RequestMapping(API_V1 + REPORT)
public class ReportController {
    private final ModelMapper modelMapper;
    private final ReportService reportService;

    @Autowired
    public ReportController(ModelMapper modelMapper, ReportService reportService) {
        this.modelMapper = modelMapper;
        this.reportService = reportService;
    }


    @PostMapping
    @Operation(
            summary = "Метод для создания доклада",
            responses = {
                    @ApiResponse(responseCode = "200",
                            description = "Возвращается созданный объект доклада",
                            content = {
                                    @Content(
                                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                                            schema = @Schema(implementation = ReportDto.class)
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
    public ResponseEntity<ReportDto> create(@RequestBody ReportDto reportDto) {
        return ResponseEntity.status(HttpStatus.OK).body(modelMapper.map(reportService.save(reportDto), ReportDto.class));
    }

    @PutMapping("/{id}")
    @Operation(
            summary = "Метод для обновления доклада",
            responses = {
                    @ApiResponse(responseCode = "200",
                            description = "Возвращается обновленный объект доклада",
                            content = {
                                    @Content(
                                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                                            schema = @Schema(implementation = ReportDto.class)
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
    public ResponseEntity<ReportDto> update(@PathVariable(name = "id") UUID id, @RequestBody ReportDto reportDto) {
        return ResponseEntity.status(HttpStatus.OK).body(modelMapper.map(reportService.update(id, reportDto), ReportDto.class));
    }

    @GetMapping("{id}")
    @Operation(
            summary = "Метод для поиска доклада по id",
            responses = {
                    @ApiResponse(responseCode = "200",
                            description = "Возвращается найденный объект доклада",
                            content = {
                                    @Content(
                                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                                            schema = @Schema(implementation = ReportDto.class)
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
    public ResponseEntity<ReportDto> findById(@PathVariable(name = "id") UUID id) {
        return ResponseEntity.status(HttpStatus.OK).body(modelMapper.map(reportService.findFullById(id), ReportDto.class));
    }

    @GetMapping
    @Operation(
            summary = "Метод для поиска всех докладов",
            responses = {
                    @ApiResponse(responseCode = "200",
                            description = "Возвращаются доклады",
                            content = {
                                    @Content(
                                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                                            schema = @Schema(implementation = ReportDto.class)
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
    public ResponseEntity<List<ReportDto>> findAll() {
        return ResponseEntity.status(HttpStatus.OK).body(reportService.findAll());
    }

    @DeleteMapping("/{id}")
    @Operation(
            summary = "Метод для удаления доклада по идентификатору",
            responses = {
                    @ApiResponse(responseCode = "204",
                            description = "Возвращается статус NO_CONTENT"),
                    @ApiResponse(responseCode = "404",
                            description = "Если не найден доклад с указанным ID",
                            content = {
                                    @Content(
                                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                                            schema = @Schema(implementation = ErrorDto.class)
                                    )
                            })
            }
    )
    public ResponseEntity<HttpStatus> deleteById(@PathVariable(name = "id") UUID id) {
        reportService.deleteById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
