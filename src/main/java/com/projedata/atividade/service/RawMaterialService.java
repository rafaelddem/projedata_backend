package com.projedata.atividade.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.projedata.atividade.model.RawMaterial;
import com.projedata.atividade.repository.RawMaterialRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class RawMaterialService {

    private final RawMaterialRepository repository;

    public RawMaterialService(RawMaterialRepository repository) {
        this.repository = repository;
    }

    public RawMaterial create(RawMaterial rawMaterial) {
        return repository.save(rawMaterial);
    }

    public List<RawMaterial> list() {
        return repository.findAll();
    }

    public RawMaterial find(int id) {
        return repository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Matéria-prima não encontrada"));
    }

    public RawMaterial update(int id, RawMaterial updatedRawMaterial) {
        RawMaterial RawMaterial = repository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Matéria-prima não encontrada"));

        if (updatedRawMaterial.getName() != null) 
            RawMaterial.setName(updatedRawMaterial.getName());

        if (updatedRawMaterial.getQuantity() != 0) 
            RawMaterial.setQuantity(updatedRawMaterial.getQuantity());

        return repository.save(RawMaterial);
    }

    public void delete(int id) {
        repository.deleteById(id);
    }
}
