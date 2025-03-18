package com.wgsystems.sf_loteamentos.repository;

import com.wgsystems.sf_loteamentos.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {

    Optional<Cliente> findByNome(String nome);

    Optional<Cliente> findByCpf(String cpf);

}
