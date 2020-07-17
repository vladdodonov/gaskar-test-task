package com.test.demo.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class ReportDto extends BaseDto {
    @Schema(description = "Тема доклада")
    private String name;
}
