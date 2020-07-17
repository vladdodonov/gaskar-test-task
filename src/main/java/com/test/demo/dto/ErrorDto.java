package com.test.demo.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class ErrorDto {
    @Schema(description = "Сообщение об ошибке")
    private String message;
}
