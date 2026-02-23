package com.projedata.atividade.dto.product;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.projedata.atividade.dto.supply.SupplyCreateDTO;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public class ProductCreateDTO {

    @NotBlank(message = "O nome não pode estar vazio")
    @NotNull(message = "O nome não pode ser nulo")
    @Size(min = 3, max = 50, message = "O nome deve ter entre 3 e 50 caracteres")
    private String name;

    @NotNull(message = "O valor precisa ser informado")
    @Positive(message = "O valor deve ser maior que 0")
    private int value;

    @Valid
    @JsonProperty("raw_materials")
    private List<SupplyCreateDTO> supplies = new ArrayList<>();

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getValue() {
        return this.value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public List<SupplyCreateDTO> getRawMaterialSupplies() {
        return this.supplies;
    }

    public void setRawMaterialSupplies(List<SupplyCreateDTO> supplies) {
        this.supplies = supplies;
    }
}
