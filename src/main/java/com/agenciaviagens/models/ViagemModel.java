package com.agenciaviagens.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "viagem")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ViagemModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String titulo;

    private LocalDateTime dataInicio;

    private LocalDateTime dataTermino;

    private BigDecimal custoTotal;

    @ManyToOne
    private DestinoModel destino;

    @AssertTrue(message = "A data de início deve ser anterior à data de término.")
    public boolean isDataValida() {
        return dataInicio == null || dataTermino == null || dataInicio.isBefore(dataTermino);
    }
}