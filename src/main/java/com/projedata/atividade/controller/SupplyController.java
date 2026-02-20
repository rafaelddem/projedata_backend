package com.projedata.atividade.controller;

import com.projedata.atividade.dto.SupplyDTO;
import com.projedata.atividade.service.SupplyService;

import java.util.List;

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
    public ResponseEntity<List<SupplyDTO>> listProductsBySupply() {
        return ResponseEntity.ok(service.listProductsBySupply());
    }
}
