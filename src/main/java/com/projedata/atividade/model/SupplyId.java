package com.projedata.atividade.model;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Embeddable;

@Embeddable
public class SupplyId implements Serializable {
    private Integer productId;
    private Integer rawMaterialId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SupplyId)) return false;
        SupplyId that = (SupplyId) o;
        return Objects.equals(productId, that.productId) &&
               Objects.equals(rawMaterialId, that.rawMaterialId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productId, rawMaterialId);
    }
}
