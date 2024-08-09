package com.agenciaviagens.repositories;

import com.agenciaviagens.models.ViagemModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ViagemRepository extends JpaRepository<ViagemModel, Long> {
}
