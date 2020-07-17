package com.test.demo.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public class SpeakerDto extends BaseDto {
    @Schema(description = "Имя")
    private String firstName;
    @Schema(description = "Фамилия")
    private String lastName;
    @Schema(description = "Отчество")
    private String patronymic;
    @Schema(description = "Доклады")
    private List<ReportDto> reports;
}
