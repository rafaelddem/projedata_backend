package com.projedata.atividade.controller;

import com.projedata.atividade.dto.ProductSupplyDTO;
import com.projedata.atividade.dto.RawMaterialSupplyDTO;
import com.projedata.atividade.model.Product;
import com.projedata.atividade.model.RawMaterial;
import com.projedata.atividade.model.Supply;
import com.projedata.atividade.repository.ProductRepository;
import com.projedata.atividade.repository.RawMaterialRepository;

import jakarta.transaction.Transactional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductRepository repository;
    private final RawMaterialRepository rawMaterialRepository;

    public ProductController(
        ProductRepository repository,
        RawMaterialRepository rawMaterialRepository
    ) {
        this.repository = repository;
        this.rawMaterialRepository = rawMaterialRepository;
    }

    @Transactional
    @PostMapping
    public ResponseEntity<Product> create(@RequestBody ProductSupplyDTO productSupplyDTO) {
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
        repository.save(product);

        return ResponseEntity.ok(product);
    }

    @GetMapping
    public ResponseEntity<List<Product>> list() {
        return ResponseEntity.ok(repository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> find(@PathVariable int id) {
        Product product = repository.findById(id).orElse(null);

        return (product != null)
            ? ResponseEntity.ok(product)
            : ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> update(@PathVariable int id, @RequestBody ProductSupplyDTO productSupplyDTO) {
        Product product = repository.findById(id)
            .orElseThrow(() -> new RuntimeException("Produto não encontrado"));

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
        repository.save(product);

        return ResponseEntity.ok(product);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Product> delete(@PathVariable int id) {
        repository.deleteById(id);

        return ResponseEntity.noContent().build();
    }
}
