package com.wgsystems.sf_loteamentos.service;

import com.wgsystems.sf_loteamentos.dto.ClienteDTO;
import com.wgsystems.sf_loteamentos.dto.LoteamentoDTO;
import com.wgsystems.sf_loteamentos.entity.Cliente;
import com.wgsystems.sf_loteamentos.entity.Loteamento;
import com.wgsystems.sf_loteamentos.exceptions.ClienteDuplicadoException;
import com.wgsystems.sf_loteamentos.repository.LoteamentoRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class LoteamentoService {

    private final LoteamentoRepository loteamentoRepository;
    private final ModelMapper mapper;

    public LoteamentoService(LoteamentoRepository loteamentoRepository, ModelMapper mapper) {
        this.loteamentoRepository = loteamentoRepository;
        this.mapper = mapper;
    }

    public LoteamentoDTO salvar(LoteamentoDTO dto) {
        Optional<Loteamento> optional = loteamentoRepository.findByNome(dto.getNome());
        if (optional.isPresent()) {
            throw new ClienteDuplicadoException("Já existe um Loteamento com este Nome");
        }
        Loteamento loteamento = mapper.map(dto, Loteamento.class);
        loteamentoRepository.save(loteamento);
        return dto;
    }

    public List<LoteamentoDTO> listarLoteamentos() {
        return loteamentoRepository.findAll().stream()
                .map(loteamento -> mapper.map(loteamento, LoteamentoDTO.class))
                .collect(Collectors.toList());
    }
}
