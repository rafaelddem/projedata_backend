package com.projedata.atividade.repository;

import com.projedata.atividade.model.Supply;
import com.projedata.atividade.model.SupplyId;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface SupplyRepository extends JpaRepository<Supply, SupplyId> {

    @Query(value = """
        select 
            product.id, 
            product.name, 
            product.value, 
            MIN(raw_material.quantity DIV supply.quantity) as max_production, 
            MIN(raw_material.quantity DIV supply.quantity) * product.value as max_value 
        from product
            left join supply on supply.product_id = product.id
            left join raw_material on raw_material.id = supply.raw_material_id
        group by 
            product.id
        order by 
            max_value desc;
    """, nativeQuery = true)
    List<Object[]> listProductsBySupply();
}
