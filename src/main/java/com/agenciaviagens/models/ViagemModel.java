package com.agenciaviagens.models;

import jakarta.persistence.*;
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

}
