package com.projedata.atividade.controller;

import com.projedata.atividade.dto.ProductSupplyDTO;
import com.projedata.atividade.dto.RawMaterialSupplyDTO;
import com.projedata.atividade.model.Product;
import com.projedata.atividade.model.RawMaterial;
import com.projedata.atividade.model.Supply;
import com.projedata.atividade.repository.ProductRepository;
import com.projedata.atividade.repository.RawMaterialRepository;

import jakarta.transaction.Transactional;

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

    // @PostMapping
    // public Product create(@RequestBody Product product) {
    //     return repository.save(product);
    // }

    @Transactional
    @PostMapping
    public void create(@RequestBody ProductSupplyDTO productSupplyDTO) {
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
    }

    @GetMapping
    public List<Product> list() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public Product find(@PathVariable int id) {
        return repository.findById(id).orElse(null);
    }

    @PutMapping("/{id}")
    public Product update(@PathVariable int id, @RequestBody Product updatedProduct) {
        Product product = repository.findById(id)
            .orElseThrow(() -> new RuntimeException("Produto não encontrado"));

        product.setName(updatedProduct.getName());
        product.setValue(updatedProduct.getValue());

        return repository.save(product);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id) {
        repository.deleteById(id);
    }
}
