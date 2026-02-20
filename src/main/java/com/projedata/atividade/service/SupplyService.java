package com.projedata.atividade.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.projedata.atividade.dto.SupplyDTO;
import com.projedata.atividade.repository.SupplyRepository;

@Service
public class SupplyService {

    private final SupplyRepository repository;

    public SupplyService(SupplyRepository repository) {
        this.repository = repository;
    }

    public List<SupplyDTO> listProductsBySupply() {
        List<Object[]> results = repository.listProductsBySupply();
        return results.stream()
            .map(r -> new SupplyDTO(
                ((Number) r[0]).intValue(),
                (String) r[1],
                r[2] == null ? 0 : ((Number) r[2]).intValue(),
                r[3] == null ? 0 : ((Number) r[3]).intValue()
            ))
            .toList();
    }
}
