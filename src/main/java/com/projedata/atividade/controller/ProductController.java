package com.projedata.atividade.controller;

import com.projedata.atividade.dto.ProductSupplyDTO;
import com.projedata.atividade.model.Product;
import com.projedata.atividade.service.ProductService;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService service;

    public ProductController(
        ProductService service
    ) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Product> create(@RequestBody ProductSupplyDTO productSupplyDTO) {
        Product product = service.create(productSupplyDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(product);
    }

    @GetMapping
    public ResponseEntity<List<Product>> list() {
        return ResponseEntity.ok(service.list());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> find(@PathVariable int id) {
        Product product = service.find(id);

        return (product != null)
            ? ResponseEntity.ok(product)
            : ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> update(@PathVariable int id, @RequestBody ProductSupplyDTO productSupplyDTO) {
        return ResponseEntity.ok(service.update(id, productSupplyDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Product> delete(@PathVariable int id) {
        service.delete(id);

        return ResponseEntity.noContent().build();
    }
}
