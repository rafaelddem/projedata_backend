package com.projedata.atividade.dto.product;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.projedata.atividade.dto.supply.SupplyCreateDTO;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public class ProductUpdateDTO {

    @Size(min = 3, max = 50, message = "O nome deve ter entre 3 e 50 caracteres")
    private String name;

    @Positive(message = "O valor deve ser maior que 0")
    private Integer value;

    @Valid
    @JsonProperty("raw_materials")
    private List<SupplyCreateDTO> supplies = new ArrayList<>();

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getValue() {
        return this.value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public List<SupplyCreateDTO> getRawMaterialSupplies() {
        return this.supplies;
    }

    public void setRawMaterialSupplies(List<SupplyCreateDTO> supplies) {
        this.supplies = supplies;
    }
}
