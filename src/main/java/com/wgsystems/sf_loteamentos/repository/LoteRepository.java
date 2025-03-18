package com.wgsystems.sf_loteamentos.repository;

import com.wgsystems.sf_loteamentos.entity.Cliente;
import com.wgsystems.sf_loteamentos.entity.Lote;
import com.wgsystems.sf_loteamentos.entity.Loteamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LoteRepository extends JpaRepository<Lote, Long> {

    @Query("SELECT lote FROM Lote lote WHERE lote.numero = :numero AND lote.loteamento = :loteamento AND lote.cliente = :cliente")
    Optional<Lote> buscarPorLote(@Param("numero") String numero,
                                 @Param("loteamento") Loteamento loteamento,
                                 @Param("cliente") Cliente cliente);
}
