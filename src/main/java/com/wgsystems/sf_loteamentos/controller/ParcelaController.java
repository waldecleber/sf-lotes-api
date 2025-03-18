package com.wgsystems.sf_loteamentos.controller;

import com.wgsystems.sf_loteamentos.dto.ClienteDTO;
import com.wgsystems.sf_loteamentos.dto.ParcelaDTO;
import com.wgsystems.sf_loteamentos.service.ParcelaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/parcela")
public class ParcelaController {


    private final ParcelaService parcelaService;

    public ParcelaController(ParcelaService parcelaService) {
        this.parcelaService = parcelaService;
    }

    @GetMapping(value = "/{cpf}/{idFinanciamento}")
    public ResponseEntity<List<ParcelaDTO>> buscarClienteAndFinanciamento(@PathVariable String cpf, @PathVariable Long idFinanciamento) {
        List<ParcelaDTO> parcelas = parcelaService.listarParcelasPorClienteAndFinanciamento(cpf, idFinanciamento);

        if (Objects.nonNull(parcelas)) {
            return ResponseEntity.ok().body(parcelas);
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<ParcelaDTO> atualizar(@PathVariable Long id, @RequestBody ParcelaDTO parcela) {
        return ResponseEntity.ok(parcelaService.atualizar(id, parcela));
    }

}
