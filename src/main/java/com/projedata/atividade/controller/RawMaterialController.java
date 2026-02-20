package com.projedata.atividade.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.projedata.atividade.model.RawMaterial;
import com.projedata.atividade.service.RawMaterialService;

@RestController
@RequestMapping("/raw_materials")
public class RawMaterialController {

    private final RawMaterialService service;

    public RawMaterialController(RawMaterialService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<RawMaterial> create(@RequestBody RawMaterial rawMaterial) {
        rawMaterial = service.create(rawMaterial);
        return ResponseEntity.status(HttpStatus.CREATED).body(rawMaterial);
    }

    @GetMapping
    public ResponseEntity<List<RawMaterial>> list() {
        return ResponseEntity.ok(service.list());
    }

    @GetMapping("/{id}")
    public ResponseEntity<RawMaterial> find(@PathVariable int id) {
        RawMaterial RawMaterial = service.find(id);

        return (RawMaterial != null)
            ? ResponseEntity.ok(RawMaterial)
            : ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<RawMaterial> update(@PathVariable int id, @RequestBody RawMaterial RawMaterial) {
        return ResponseEntity.ok(service.update(id, RawMaterial));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<RawMaterial> delete(@PathVariable int id) {
        service.delete(id);

        return ResponseEntity.noContent().build();
    }
}
