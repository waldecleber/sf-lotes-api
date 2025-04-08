package com.wgsystems.sf_loteamentos.entity;

import jakarta.persistence.*;

import com.wgsystems.sf_loteamentos.enums.StatusEnum;
import lombok.*;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class Financiamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "lote_id", referencedColumnName = "id")
    private Lote lote;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "cliente_id", nullable = false)
    private Cliente cliente;

    private LocalDate dataCompra;

    private Double valorTotal;

    private Integer qtdeParcelas;

    private Integer parcelasPagas;

    private Integer parcelasRestantes;

    private Double totalPago;

    private Double faltaPagar;

    private Double valorParcela;

    @OneToMany(mappedBy = "financiamento")
    private Set<Parcela> parcelas;

    private StatusEnum status;

}
