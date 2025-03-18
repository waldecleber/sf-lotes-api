package com.wgsystems.sf_loteamentos.dto;

import com.wgsystems.sf_loteamentos.enums.StatusParcelaEnum;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ParcelaDTO {

    private Long id;

    private String numeroParcela;

//    private LoteDTO lote;

    private ClienteDTO cliente;

    private LocalDate dataPagamento;

    private LocalDate dataVencimento;

    private Double valor;

    private StatusParcelaEnum status;

    private FinanciamentoDTO financiamento;

}
