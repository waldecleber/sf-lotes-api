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
import java.util.Objects;
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
        if (Objects.isNull(dto.getNome())) {
            throw new ClienteDuplicadoException("Nome não pode estar em branco.");
        }
        if (optional.isPresent()) {
            throw new ClienteDuplicadoException("Já existe um Loteamento com este Nome");
        }
        Loteamento loteamento = mapper.map(dto, Loteamento.class);
        loteamentoRepository.save(loteamento);
        return dto;
    }

    public List<LoteamentoDTO> listarLoteamentos() {
        return loteamentoRepository.findAll().stream()
                .filter(resp -> Objects.nonNull(resp.getNome()))
                .map(loteamento -> mapper.map(loteamento, LoteamentoDTO.class))
                .collect(Collectors.toList());
    }

    public LoteamentoDTO buscarLoteamentoPorId(String id) {
        return loteamentoRepository.findById(Long.valueOf(id))
                .map(loteamento -> mapper.map(loteamento, LoteamentoDTO.class))
                .orElseThrow(() -> new ClienteDuplicadoException("Não foi encontrado loteamento"));
    }
}
