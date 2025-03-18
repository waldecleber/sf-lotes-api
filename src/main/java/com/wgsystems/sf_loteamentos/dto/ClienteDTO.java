package com.wgsystems.sf_loteamentos.dto;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ClienteDTO {

    private String id;

    private String nome;

    private String cpf;

    private EnderecoDTO endereco;

    private String celular;

}
