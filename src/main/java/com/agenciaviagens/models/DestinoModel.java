package com.agenciaviagens.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "destino")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Data
public class DestinoModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank(message = "O nome do destino é obrigatório.")
    private String nome;

    @NotBlank(message = "O país é obrigatório.")
    private String pais;

    @JsonBackReference
    @OneToMany(mappedBy = "destino")
    private List<ViagemModel> viagens;
}
