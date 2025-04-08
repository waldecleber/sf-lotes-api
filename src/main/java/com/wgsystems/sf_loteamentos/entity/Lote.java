package com.wgsystems.sf_loteamentos.entity;

import jakarta.persistence.*;

import com.wgsystems.sf_loteamentos.enums.StatusEnum;
import lombok.*;

import java.io.Serializable;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class Lote implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "loteamento_id", nullable = false)
    private Loteamento loteamento;

    private Double valor;

    private String numero;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "cliente_id", nullable = false)
    private Cliente cliente;

    @OneToOne(mappedBy = "lote")
    private Financiamento financiamento;

    private StatusEnum status;

}
