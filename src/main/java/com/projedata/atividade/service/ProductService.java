package com.projedata.atividade.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.projedata.atividade.dto.ProductSupplyDTO;
import com.projedata.atividade.dto.RawMaterialSupplyDTO;
import com.projedata.atividade.model.Product;
import com.projedata.atividade.model.RawMaterial;
import com.projedata.atividade.model.Supply;
import com.projedata.atividade.repository.ProductRepository;
import com.projedata.atividade.repository.RawMaterialRepository;

import jakarta.transaction.Transactional;

@Service
public class ProductService {

    private final ProductRepository repository;
    private final RawMaterialRepository rawMaterialRepository;

    public ProductService(ProductRepository repository,
                          RawMaterialRepository rawMaterialRepository) {
        this.repository = repository;
        this.rawMaterialRepository = rawMaterialRepository;
    }

    @Transactional
    public Product create(ProductSupplyDTO productSupplyDTO) {
        Product product = new Product();
        product.setName(productSupplyDTO.getName());
        product.setValue(productSupplyDTO.getValue());

        List<Supply> supplies = new ArrayList<>();

        for (RawMaterialSupplyDTO rawMaterialSupplyDTO : productSupplyDTO.getRawMaterialSupplies()) {
            RawMaterial rawMaterial = rawMaterialRepository.findById(rawMaterialSupplyDTO.getRawMaterialId())
                .orElseThrow(() -> new RuntimeException("Matéria-prima não encontrada"));

            Supply supply = new Supply();
            supply.setProduct(product);
            supply.setRawMaterial(rawMaterial);
            supply.setQuantity(rawMaterialSupplyDTO.getQuantity());

            supplies.add(supply);
        }

        product.setSupplies(supplies);
        return repository.save(product);
    }

    public List<Product> list() {
        return repository.findAll();
    }

    public Product find(int id) {
        return repository.findById(id).orElse(null);
    }

    @Transactional
    public Product update(int id, ProductSupplyDTO productSupplyDTO) {
        Product product = repository.findById(id)
            .orElseThrow(() -> new RuntimeException("Produto não encontrado"));

        product.setName(productSupplyDTO.getName());
        product.setValue(productSupplyDTO.getValue());

        product.getSupplies().clear();
        repository.flush();

        for (RawMaterialSupplyDTO rawMaterialSupplyDTO : productSupplyDTO.getRawMaterialSupplies()) {
            RawMaterial rawMaterial = rawMaterialRepository.findById(rawMaterialSupplyDTO.getRawMaterialId())
                .orElseThrow(() -> new RuntimeException("Matéria-prima não encontrada"));

            Supply supply = new Supply();
            supply.setProduct(product);
            supply.setRawMaterial(rawMaterial);
            supply.setQuantity(rawMaterialSupplyDTO.getQuantity());

            product.getSupplies().add(supply);
        }

        return repository.save(product);
    }

    public void delete(int id) {
        repository.deleteById(id);
    }
}
