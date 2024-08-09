package com.agenciaviagens.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

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

    private String nome;

    private String pais;

    @JsonBackReference
    @OneToMany(mappedBy = "destino")
    private List<ViagemModel> viagens;
}
