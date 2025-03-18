package com.wgsystems.sf_loteamentos.service;

import com.wgsystems.sf_loteamentos.dto.ClienteDTO;
import com.wgsystems.sf_loteamentos.dto.FinanciamentoDTO;
import com.wgsystems.sf_loteamentos.entity.*;
import com.wgsystems.sf_loteamentos.enums.StatusParcelaEnum;
import com.wgsystems.sf_loteamentos.exceptions.ClienteDuplicadoException;
import com.wgsystems.sf_loteamentos.exceptions.ClienteNaoEncontrado;
import com.wgsystems.sf_loteamentos.repository.*;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.util.Objects.nonNull;

@Service
public class FinanciamentoService {

    private final FinanciamentoRepository financiamentoRepository;

    private final LoteRepository loteRepository;

    private final ClienteRepository clienteRepository;

    private final LoteamentoRepository loteamentoRepository;

    private final ParcelaRepository parcelaRepository;

    private final ModelMapper mapper;

    public FinanciamentoService(FinanciamentoRepository financiamentoRepository, LoteRepository loteRepository, ClienteRepository clienteRepository, LoteamentoRepository loteamentoRepository, ParcelaRepository parcelaRepository, ModelMapper mapper) {
        this.financiamentoRepository = financiamentoRepository;
        this.loteRepository = loteRepository;
        this.clienteRepository = clienteRepository;
        this.loteamentoRepository = loteamentoRepository;
        this.parcelaRepository = parcelaRepository;
        this.mapper = mapper;
    }

    public FinanciamentoDTO salvar(FinanciamentoDTO financiamentoDTO) {

        Cliente cliente = clienteRepository.findByCpf(financiamentoDTO.getCliente().getCpf())
                .orElseThrow(() -> new ClienteNaoEncontrado("Cliente não encontrado"));

        Lote lote = mapper.map(financiamentoDTO.getLote(), Lote.class);
        Loteamento loteamento = loteamentoRepository.getById(lote.getLoteamento().getId());

        Optional<Lote> optional = loteRepository.buscarPorLote(financiamentoDTO.getLote().getNumero(), loteamento, cliente);
        if (optional.isPresent()) {
            throw new ClienteDuplicadoException("Este CPF já possui um financiamento para este Lote.");
        }

        Financiamento financiamento = mapper.map(financiamentoDTO, Financiamento.class);
        financiamento.setFaltaPagar(financiamentoDTO.getValorTotal());
        financiamento.setTotalPago(0.0);
        financiamento.setParcelasPagas(0);
        financiamento.setParcelasRestantes(financiamentoDTO.getQtdeParcelas());
        financiamento.setCliente(cliente);
        financiamento.setLote(lote);

        lote.setFinanciamento(financiamento);
        lote.setCliente(cliente);
        lote.setLoteamento(loteamento);

        financiamentoRepository.save(financiamento);

        for(int i = 0; i < financiamento.getQtdeParcelas(); i++) {
            Parcela parcela = Parcela.builder()
                    .numeroParcela(i + 1 + "")
                    .financiamento(financiamento)
                    .cliente(cliente)
                    .lote(lote)
                    .dataVencimento(financiamento.getDataCompra().plusMonths(i + 1))
                    .status(StatusParcelaEnum.PENDENTE)
                    .valor(financiamento.getValorParcela())
                    .build();
            parcelaRepository.save(parcela);
        }

        return financiamentoDTO;
    }

    public List<FinanciamentoDTO> buscarFinanciamentoPorCpf(String cpf) {
        Cliente cliente =  clienteRepository.findByCpf(cpf)
                .orElseThrow(() -> new ClienteNaoEncontrado("Cliente não encontrado"));

        return financiamentoRepository.listarFinanciamentoPorCliente(cliente.getId()).stream()
                .map(c -> mapper.map(c, FinanciamentoDTO.class))
                .collect(Collectors.toList());
    }

    public FinanciamentoDTO buscarFinanciamentoPorId(Long id) {
        return financiamentoRepository.findById(id)
                .map(c -> mapper.map(c, FinanciamentoDTO.class))
                .orElseThrow(() -> new ClienteNaoEncontrado("Financiamento não encontrado"));
    }
}
