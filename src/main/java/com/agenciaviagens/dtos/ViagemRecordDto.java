package com.agenciaviagens.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record ViagemRecordDto(
        @NotBlank String titulo,
        @NotNull LocalDateTime dataInicio,
        @NotNull LocalDateTime dataTermino,
        @PositiveOrZero BigDecimal custoTotal,
        @NotNull Long destinoId) {
}
