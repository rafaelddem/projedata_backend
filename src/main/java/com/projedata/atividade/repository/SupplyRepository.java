package com.projedata.atividade.repository;

import com.projedata.atividade.model.Supply;
import com.projedata.atividade.model.SupplyId;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SupplyRepository extends JpaRepository<Supply, SupplyId> {
}
