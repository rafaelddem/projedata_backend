package com.projedata.atividade.dto;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ProductSupplyDTO {

    private String name;
    private int value;

    @JsonProperty("raw_materials")
    private List<RawMaterialSupplyDTO> rawMaterialSupplies = new ArrayList<>();

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

    public List<RawMaterialSupplyDTO> getRawMaterialSupplies() {
        return this.rawMaterialSupplies;
    }

    public void setRawMaterialSupplies(List<RawMaterialSupplyDTO> rawMaterialSupplies) {
        this.rawMaterialSupplies = rawMaterialSupplies;
    }
}
