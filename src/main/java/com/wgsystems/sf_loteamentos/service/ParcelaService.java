package com.wgsystems.sf_loteamentos.service;

import com.wgsystems.sf_loteamentos.dto.ClienteDTO;
import com.wgsystems.sf_loteamentos.dto.ParcelaDTO;
import com.wgsystems.sf_loteamentos.entity.Cliente;
import com.wgsystems.sf_loteamentos.entity.Financiamento;
import com.wgsystems.sf_loteamentos.entity.Parcela;
import com.wgsystems.sf_loteamentos.exceptions.ClienteNaoEncontrado;
import com.wgsystems.sf_loteamentos.repository.ClienteRepository;
import com.wgsystems.sf_loteamentos.repository.FinanciamentoRepository;
import com.wgsystems.sf_loteamentos.repository.ParcelaRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ParcelaService {

    private final ParcelaRepository parcelaRepository;

    private final FinanciamentoRepository financiamentoRepository;

    private final ClienteRepository clienteRepository;

    private final ModelMapper mapper;

    public ParcelaService(ParcelaRepository parcelaRepository, FinanciamentoRepository financiamentoRepository, ClienteRepository clienteRepository, ModelMapper mapper) {
        this.parcelaRepository = parcelaRepository;
        this.financiamentoRepository = financiamentoRepository;
        this.clienteRepository = clienteRepository;
        this.mapper = mapper;
    }

    public List<ParcelaDTO> listarParcelasPorClienteAndFinanciamento(String cpf, Long idFinanciamento) {
        Cliente cliente =  clienteRepository.findByCpf(cpf)
                .orElseThrow(() -> new ClienteNaoEncontrado("Cliente não encontrado"));

        return parcelaRepository.listarParcelasPorClienteAndFinanciamento(cliente.getId(), idFinanciamento)
                .stream()
                .map(parcela -> mapper.map(parcela, ParcelaDTO.class))
                .collect(Collectors.toList());
    }

    public ParcelaDTO atualizar(Long id, ParcelaDTO parcelaDTO) {
        if (parcelaRepository.existsById(id)) {
            Parcela parcela = mapper.map(parcelaDTO, Parcela.class);
            return mapper.map(parcelaRepository.save(parcela), ParcelaDTO.class);
        } else {
            throw new ClienteNaoEncontrado("Cliente não encontrado");
        }
    }
}
