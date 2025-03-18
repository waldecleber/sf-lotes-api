package com.wgsystems.sf_loteamentos.repository;

import com.wgsystems.sf_loteamentos.entity.Financiamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FinanciamentoRepository extends JpaRepository<Financiamento, Long> {

    @Query("SELECT f FROM Financiamento f WHERE f.cliente.id = :idCliente")
    List<Financiamento> listarFinanciamentoPorCliente(@Param("idCliente") Long idCliente);
}
