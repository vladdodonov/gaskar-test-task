package com.test.demo.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.UUID;

@Data
public class BaseDto {
    @Schema(description = "ID")
    private UUID id;
}
