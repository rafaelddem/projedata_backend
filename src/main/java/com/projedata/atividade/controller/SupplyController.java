package com.projedata.atividade.controller;

import com.projedata.atividade.service.SupplyService;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/supply")
public class SupplyController {

    private final SupplyService service;

    public SupplyController(
        SupplyService service
    ) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<?> listProductsBySupply() {
        try {
            return ResponseEntity.ok(service.listProductsBySupply());
        } catch (Exception e) {
            Map<String, Object> error = new HashMap<>();
            error.put("message", "Não foi possível listar os produtos");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
        }
    }
}
