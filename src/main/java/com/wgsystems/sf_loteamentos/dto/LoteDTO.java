package com.wgsystems.sf_loteamentos.dto;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LoteDTO {

    private LoteamentoDTO loteamento;

    private Double valor;

    private String numero;

    private ClienteDTO cliente;
}
