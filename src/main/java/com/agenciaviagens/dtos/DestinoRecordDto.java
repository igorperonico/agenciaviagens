package com.agenciaviagens.dtos;

import jakarta.validation.constraints.NotBlank;

public record DestinoRecordDto(@NotBlank String nome, @NotBlank String pais) {
}
