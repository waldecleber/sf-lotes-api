package com.wgsystems.sf_loteamentos.dto;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FinanciamentoDTO {

    private Long id;

    private LoteDTO lote;

    private ClienteDTO cliente;

    private LocalDate dataCompra;

    private Double valorTotal;

    private Integer qtdeParcelas;

    private Integer parcelasPagas;

    private Integer parcelasRestantes;

    private Integer totalPago;

    private Integer faltaPagar;

    private Double valorParcela;
}
