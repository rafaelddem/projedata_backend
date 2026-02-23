package com.projedata.atividade.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.projedata.atividade.dto.product.ProductCreateDTO;
import com.projedata.atividade.dto.product.ProductUpdateDTO;
import com.projedata.atividade.dto.supply.SupplyCreateDTO;
import com.projedata.atividade.model.Product;
import com.projedata.atividade.model.RawMaterial;
import com.projedata.atividade.model.Supply;
import com.projedata.atividade.repository.ProductRepository;
import com.projedata.atividade.repository.RawMaterialRepository;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

@Service
public class ProductService {

    private final ProductRepository repository;
    private final RawMaterialRepository rawMaterialRepository;

    public ProductService(
        ProductRepository repository,
        RawMaterialRepository rawMaterialRepository
    ) {
        this.repository = repository;
        this.rawMaterialRepository = rawMaterialRepository;
    }

    @Transactional
    public Product create(ProductCreateDTO productSupplyDTO) {
        Product product = new Product();
        product.setName(productSupplyDTO.getName());
        product.setValue(productSupplyDTO.getValue());

        List<Supply> supplies = new ArrayList<>();

        for (SupplyCreateDTO supplyCreateDTO : productSupplyDTO.getRawMaterialSupplies()) {
            RawMaterial rawMaterial = rawMaterialRepository.findById(supplyCreateDTO.getRawMaterialId())
                .orElseThrow(() -> new EntityNotFoundException("Matéria-prima não encontrada"));

            Supply supply = new Supply();
            supply.setProduct(product);
            supply.setRawMaterial(rawMaterial);
            supply.setQuantity(supplyCreateDTO.getQuantity());

            supplies.add(supply);
        }

        product.setSupplies(supplies);
        return repository.save(product);
    }

    public List<Product> list() {
        return repository.findAll();
    }

    public Product find(int id) {
        return repository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Produto não encontrado"));
    }

    @Transactional
    public Product update(int id, ProductUpdateDTO productSupplyDTO) {
        Product product = repository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Produto não encontrado"));

        if (productSupplyDTO.getName() != null) 
            product.setName(productSupplyDTO.getName());

        if (productSupplyDTO.getValue() != null) 
            product.setValue(productSupplyDTO.getValue());

        product.getSupplies().clear();
        repository.flush();

        for (SupplyCreateDTO supplyUpdateDTO : productSupplyDTO.getRawMaterialSupplies()) {
            RawMaterial rawMaterial = rawMaterialRepository.findById(supplyUpdateDTO.getRawMaterialId())
                .orElseThrow(() -> new EntityNotFoundException("Matéria-prima não encontrada"));

            Supply supply = new Supply();
            supply.setProduct(product);
            supply.setRawMaterial(rawMaterial);
            supply.setQuantity(supplyUpdateDTO.getQuantity());

            product.getSupplies().add(supply);
        }

        return repository.save(product);
    }

    public void delete(int id) {
        repository.deleteById(id);
    }
}
