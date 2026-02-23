package com.projedata.atividade.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.projedata.atividade.dto.raw_material.RawMaterialCreateDTO;
import com.projedata.atividade.model.RawMaterial;
import com.projedata.atividade.repository.RawMaterialRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class RawMaterialService {

    private final RawMaterialRepository repository;

    public RawMaterialService(RawMaterialRepository repository) {
        this.repository = repository;
    }

    public RawMaterial create(RawMaterialCreateDTO rawMaterialDTO) {
        RawMaterial rawMaterial = new RawMaterial();
        rawMaterial.setName(rawMaterialDTO.getName());
        if (rawMaterialDTO.getQuantity() != null) rawMaterial.setQuantity(rawMaterialDTO.getQuantity());
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
        RawMaterial rawMaterial = repository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Matéria-prima não encontrada."));

        if (!rawMaterial.getSupplies().isEmpty()) {
            throw new IllegalStateException("Matérias-primas utilizadas por algum Produto não podem ser excluídas.");
        }

        repository.deleteById(id);
    }
}
