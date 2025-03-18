package com.wgsystems.sf_loteamentos.repository;

import com.wgsystems.sf_loteamentos.entity.Cliente;
import com.wgsystems.sf_loteamentos.entity.Loteamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LoteamentoRepository extends JpaRepository<Loteamento, Long> {
    Optional<Loteamento> findByNome(String nome);
}
