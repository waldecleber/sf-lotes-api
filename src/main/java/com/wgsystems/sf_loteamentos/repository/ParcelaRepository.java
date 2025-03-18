package com.wgsystems.sf_loteamentos.repository;

import com.wgsystems.sf_loteamentos.entity.Parcela;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ParcelaRepository extends JpaRepository<Parcela, Long> {

    @Query("SELECT p FROM Parcela p WHERE p.cliente.id = :idCliente  AND p.financiamento.id = :idFinanciamento")
    List<Parcela> listarParcelasPorClienteAndFinanciamento(@Param("idCliente") Long idCliente, @Param("idFinanciamento") Long idFinanciamento);
}
