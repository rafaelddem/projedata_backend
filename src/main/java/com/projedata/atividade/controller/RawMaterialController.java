package com.projedata.atividade.controller;

import com.projedata.atividade.model.RawMaterial;
import com.projedata.atividade.repository.RawMaterialRepository;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/raw_materials")
public class RawMaterialController {

    private final RawMaterialRepository repository;

    public RawMaterialController(RawMaterialRepository repository) {
        this.repository = repository;
    }

    @PostMapping
    public RawMaterial create(@RequestBody RawMaterial RawMaterial) {
        return repository.save(RawMaterial);
    }

    @GetMapping
    public List<RawMaterial> list() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public RawMaterial find(@PathVariable int id) {
        return repository.findById(id).orElse(null);
    }

    @PutMapping("/{id}")
    public RawMaterial update(@PathVariable int id, @RequestBody RawMaterial updatedRawMaterial) {
        RawMaterial RawMaterial = repository.findById(id)
            .orElseThrow(() -> new RuntimeException("Matéria-prima não encontrada"));

        RawMaterial.setName(updatedRawMaterial.getName());
        RawMaterial.setQuantity(updatedRawMaterial.getQuantity());

        return repository.save(RawMaterial);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id) {
        repository.deleteById(id);
    }
}
