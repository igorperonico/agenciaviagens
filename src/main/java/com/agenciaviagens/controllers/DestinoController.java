package com.agenciaviagens.controllers;

import com.agenciaviagens.dtos.DestinoRecordDto;
import com.agenciaviagens.models.DestinoModel;
import com.agenciaviagens.models.ViagemModel;
import com.agenciaviagens.repositories.DestinoRepository;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/destino")
public class DestinoController {

    @Autowired
    DestinoRepository repository;

    @GetMapping
    public ResponseEntity<List<DestinoModel>> getAllDestinos() {
        return ResponseEntity.status(HttpStatus.OK).body(repository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getDestinoById(@PathVariable(value = "id")Long id) {
        Optional<DestinoModel> destinoO = repository.findById(id);

        if (destinoO.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Destino nao encontrado");
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(destinoO.get());
        }
    }


    @PostMapping
    public ResponseEntity<DestinoModel> addDestino(@RequestBody @Valid DestinoRecordDto destinoRecordDto) {
        DestinoModel destinoModel = new DestinoModel();
        BeanUtils.copyProperties(destinoRecordDto, destinoModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(destinoModel));

    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateDestino(@PathVariable(value = "id") Long id,
                                              @RequestBody @Valid DestinoRecordDto destinoRecordDto) {
        Optional<DestinoModel> destinoO = repository.findById(id);
        if (destinoO.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Destino nao encontrado");
        } else {
            var destinoModel = destinoO.get();
            BeanUtils.copyProperties(destinoRecordDto, destinoModel);
            return ResponseEntity.status(HttpStatus.OK).body(repository.save(destinoModel));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteDestino(@PathVariable(value = "id") Long id) {
        Optional<DestinoModel> livroO = repository.findById(id);

        if (livroO.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Livro nao encontrado");
        } else {
            repository.deleteById(id);
            return ResponseEntity.status(HttpStatus.OK).body("Livro excluido com sucesso!");
        }
    }



}
