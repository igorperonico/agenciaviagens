package com.agenciaviagens.controllers;

import com.agenciaviagens.dtos.ViagemRecordDto;
import com.agenciaviagens.models.DestinoModel;
import com.agenciaviagens.models.ViagemModel;
import com.agenciaviagens.repositories.DestinoRepository;
import com.agenciaviagens.repositories.ViagemRepository;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/viagem")
public class ViagemController {

    @Autowired
    ViagemRepository viagemRepository;

    @Autowired
    DestinoRepository destinoRepository;

    @GetMapping
    public ResponseEntity<List<ViagemModel>> getAllViagens() {
        return ResponseEntity.status(HttpStatus.OK).body(viagemRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getViagemById(@PathVariable(value = "id") Long id) {
        Optional<ViagemModel> viagemO = viagemRepository.findById(id);

        if (viagemO.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Viagem nao encontrada");
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(viagemO.get());
        }
    }

    @PostMapping
    public ResponseEntity<Object> addViagem(@RequestBody @Valid ViagemRecordDto viagemRecordDto) {
        Optional<DestinoModel> destinoO = destinoRepository.findById(viagemRecordDto.destinoId());
        if (destinoO.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Destino nao encontrado");
        } else {
            ViagemModel viagemModel = new ViagemModel();
            BeanUtils.copyProperties(viagemRecordDto, viagemModel);
            viagemModel.setDestino(destinoO.get());
            return ResponseEntity.status(HttpStatus.CREATED).body(viagemRepository.save(viagemModel));
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateViagem(@PathVariable(value = "id") Long id,
                                               @RequestBody @Valid ViagemRecordDto viagemRecordDto) {
        Optional<ViagemModel> viagemO = viagemRepository.findById(id);
        if (viagemO.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Viagem nao encontrada");
        } else {
            Optional<DestinoModel> destinoO = destinoRepository.findById(viagemRecordDto.destinoId());
            if (destinoO.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Destino nao encontrado");
            } else {
                ViagemModel viagemModel = viagemO.get();
                BeanUtils.copyProperties(viagemRecordDto, viagemModel);
                viagemModel.setDestino(destinoO.get());
                return ResponseEntity.status(HttpStatus.OK).body(viagemRepository.save(viagemModel));
            }
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteViagem(@PathVariable(value = "id") Long id) {
        Optional<ViagemModel> viagemO = viagemRepository.findById(id);

        if (viagemO.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Viagem nao encontrada");
        } else {
            viagemRepository.deleteById(id);
            return ResponseEntity.status(HttpStatus.OK).body("Viagem excluida com sucesso!");
        }
    }

    @GetMapping("/destino/{destinoId}")
    public ResponseEntity<Object> getViagensByDestinoId(@PathVariable(value = "destinoId") Long destinoId) {
        Optional<DestinoModel> destinoO = destinoRepository.findById(destinoId);

        if (destinoO.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Destino nao encontrado");
        } else {
            List<ViagemModel> viagens = viagemRepository.findByDestinoId(destinoId);
            return ResponseEntity.status(HttpStatus.OK).body(viagens);
        }
    }

    /**
     * @param dataInicio Data de início do intervalo.
     * @param dataTermino Data de término do intervalo.
     * @return Lista de viagens que ocorrem dentro do intervalo de datas especificado.
     */
    @GetMapping("/intervaloDatas")
    public ResponseEntity<List<ViagemModel>> getViagensByDataSaidaBetween(
            @RequestParam(value = "dataInicio") String dataInicio,
            @RequestParam(value = "dataTermino") String dataTermino) {
        List<ViagemModel> viagens = viagemRepository.findAllByDataSaidaBetween(dataInicio, dataTermino);
        return ResponseEntity.status(HttpStatus.OK).body(viagens);
    }

    /**
     * @param valorTotal Custo total mínimo para filtrar as viagens.
     * @return Lista de viagens com custo total maior que o valor especificado.
     */
    @GetMapping("/custoTotalMaiorQue/{valorTotal}")
    public ResponseEntity<List<ViagemModel>> getViagensByCustoTotalGreaterThan(
            @PathVariable(value = "valorTotal") BigDecimal valorTotal) {
        List<ViagemModel> viagens = viagemRepository.findAllByValorTotalGreaterThan(valorTotal);
        return ResponseEntity.status(HttpStatus.OK).body(viagens);
    }

}
