package com.projedata.atividade.dto.raw_material;

import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public class RawMaterialUpdateDTO {

    @Size(min = 3, max = 50, message = "O nome deve ter entre 3 e 50 caracteres")
    private String name;

    @Positive(message = "A quantidade não pode ser negativa")
    private Integer quantity;

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getQuantity() {
        return this.quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
