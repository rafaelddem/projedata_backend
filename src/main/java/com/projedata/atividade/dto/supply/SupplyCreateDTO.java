package com.projedata.atividade.dto.supply;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class SupplyCreateDTO {

    @NotNull(message = "Uma matéria-prima precisa ser informada")
    @Positive(message = "A matéria-prima informada é inválida")
    @JsonProperty("raw_material_id")
    private int rawMaterialId;

    @NotNull(message = "Uma quantidade da matéria-prima precisa ser informada")
    @Positive(message = "A quantidade da matéria-prima deve ser maior que 0")
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
