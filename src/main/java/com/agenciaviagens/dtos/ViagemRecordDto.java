package com.agenciaviagens.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record ViagemRecordDto(
        @NotBlank String titulo,
        @NotNull LocalDateTime dataInicio,
        @NotNull LocalDateTime dataTermino,
        @NotNull BigDecimal custoTotal,
        @NotNull Long destinoId) {
}
