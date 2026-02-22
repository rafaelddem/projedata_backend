package com.projedata.atividade.controller;

import java.util.HashMap;
import java.util.Map;

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
    public ResponseEntity<?> create(@RequestBody RawMaterial rawMaterial) {
        try {
            rawMaterial = service.create(rawMaterial);
            return ResponseEntity.status(HttpStatus.CREATED).body(rawMaterial);
        } catch (Exception e) {
            Map<String, Object> error = new HashMap<>();
            error.put("message", "Erro inesperado no servidor. Favor contactar o suporte");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
        }
    }

    @GetMapping
    public ResponseEntity<?> list() {
        try {
            return ResponseEntity.ok(service.list());
        } catch (Exception e) {
            Map<String, Object> error = new HashMap<>();
            error.put("message", "Erro inesperado no servidor. Favor contactar o suporte");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> find(@PathVariable int id) {
        try {
            RawMaterial rawMaterial = service.find(id);

            return (rawMaterial != null)
                ? ResponseEntity.ok(rawMaterial)
                : ResponseEntity.noContent().build();
        } catch (Exception e) {
            Map<String, Object> error = new HashMap<>();
            error.put("message", "Erro inesperado no servidor. Favor contactar o suporte");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable int id, @RequestBody RawMaterial rawMaterial) {
        try {
            return ResponseEntity.ok(service.update(id, rawMaterial));
        } catch (Exception e) {
            Map<String, Object> error = new HashMap<>();
            error.put("message", "Erro inesperado no servidor. Favor contactar o suporte");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable int id) {
        try {
            service.delete(id);

            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            Map<String, Object> error = new HashMap<>();
            error.put("message", "Erro inesperado no servidor. Favor contactar o suporte");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
        }
    }
}
