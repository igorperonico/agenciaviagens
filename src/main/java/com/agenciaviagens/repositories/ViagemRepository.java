package com.agenciaviagens.repositories;

import com.agenciaviagens.models.ViagemModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface ViagemRepository extends JpaRepository<ViagemModel, Long> {
    List<ViagemModel> findByDestinoId(Long destinoId);
    List<ViagemModel> findAllByDataSaidaBetween(String dataSaida1, String dataSaida2);
    List<ViagemModel> findAllByValorTotalGreaterThan(BigDecimal valorTotal);

}
