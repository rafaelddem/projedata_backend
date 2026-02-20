package com.projedata.atividade.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RawMaterialSupplyDTO {

    @JsonProperty("raw_material_id")
    private int rawMaterialId;
    private int quantity;

    public int getRawMaterialId() {
        return this.rawMaterialId;
    }

    public void setRawMaterialId(int rawMaterialId) {
        this.rawMaterialId = rawMaterialId;
    }

    public int getQuantity() {
        return this.quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
