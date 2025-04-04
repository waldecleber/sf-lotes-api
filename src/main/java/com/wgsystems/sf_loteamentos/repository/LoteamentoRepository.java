package com.wgsystems.sf_loteamentos.repository;

import com.wgsystems.sf_loteamentos.entity.Loteamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LoteamentoRepository extends JpaRepository<Loteamento, Long> {
    Optional<Loteamento> findByNome(String nome);
}


/*
SELECT l.loteamento_id, ltm.nome, COUNT(l.id) as total_lotes FROM lote l
inner join loteamento ltm on ltm.id = l.loteamento_id
GROUP BY l.loteamento_id, ltm.nome
 */