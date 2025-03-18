package com.wgsystems.sf_loteamentos.dto;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LoteamentoDTO {

    private String id;

    private String nome;

    private EnderecoDTO endereco;

}
