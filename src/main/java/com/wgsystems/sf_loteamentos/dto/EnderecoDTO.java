package com.wgsystems.sf_loteamentos.dto;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EnderecoDTO {

    private String logradouro;

    private String estado;

    private String cidade;

    private String cep;
}
