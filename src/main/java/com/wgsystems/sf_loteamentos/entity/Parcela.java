package com.wgsystems.sf_loteamentos.entity;

import com.wgsystems.sf_loteamentos.enums.StatusParcelaEnum;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString(exclude = {"cliente", "financiamento"})
public class Parcela implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String numeroParcela;

    @ManyToOne(fetch = FetchType.LAZY, optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "lote_id", nullable = false)
    private Lote lote;

    @ManyToOne(fetch = FetchType.LAZY, optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "cliente_id", nullable = false)
    private Cliente cliente;

    private LocalDate dataPagamento;

    private LocalDate dataVencimento;

    private Double valor;

    private StatusParcelaEnum status;

    @ManyToOne
    @JoinColumn(name = "financiamento_id", nullable = false)
    private Financiamento financiamento;

}
