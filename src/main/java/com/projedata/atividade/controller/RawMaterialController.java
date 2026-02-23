package com.projedata.atividade.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.projedata.atividade.dto.raw_material.RawMaterialCreateDTO;
import com.projedata.atividade.dto.raw_material.RawMaterialUpdateDTO;
import com.projedata.atividade.model.RawMaterial;
import com.projedata.atividade.service.RawMaterialService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/raw_materials")
public class RawMaterialController {

    private final RawMaterialService service;

    public RawMaterialController(RawMaterialService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<RawMaterial> create(@RequestBody @Valid RawMaterialCreateDTO rawMaterialDTO) {
        RawMaterial rawMaterial = service.create(rawMaterialDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(rawMaterial);
    }

    @GetMapping
    public ResponseEntity<List<RawMaterial>> list() {
        return ResponseEntity.ok(service.list());
    }

    @GetMapping("/{id}")
    public ResponseEntity<RawMaterial> find(@PathVariable int id) {
        RawMaterial rawMaterial = service.find(id);

        return (rawMaterial != null)
            ? ResponseEntity.ok(rawMaterial)
            : ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<RawMaterial> update(@PathVariable int id, @Valid @RequestBody RawMaterialUpdateDTO rawMaterialDTO) {
        return ResponseEntity.ok(service.update(id, rawMaterialDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable int id) {
        service.delete(id);

        return ResponseEntity.noContent().build();
    }
}
