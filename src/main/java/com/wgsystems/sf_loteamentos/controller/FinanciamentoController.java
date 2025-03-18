package com.wgsystems.sf_loteamentos.controller;

import com.wgsystems.sf_loteamentos.dto.ClienteDTO;
import com.wgsystems.sf_loteamentos.dto.FinanciamentoDTO;
import com.wgsystems.sf_loteamentos.service.FinanciamentoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/financiamento")
public class FinanciamentoController {

    private final FinanciamentoService financiamentoService;

    public FinanciamentoController(FinanciamentoService financiamentoService) {
        this.financiamentoService = financiamentoService;
    }

    @GetMapping(value = "/{cpf}")
    public ResponseEntity<List<FinanciamentoDTO> > buscarClientePorCpf(@PathVariable String cpf) {
        List<FinanciamentoDTO> financiamentos = financiamentoService.buscarFinanciamentoPorCpf(cpf);
        if (Objects.nonNull(financiamentos)) {
            return ResponseEntity.ok().body(financiamentos);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping(value = "/id/{id}")
    public ResponseEntity<FinanciamentoDTO> buscarFinanciamentoPorId(@PathVariable Long id) {
        FinanciamentoDTO financiamentos = financiamentoService.buscarFinanciamentoPorId(id);
        if (Objects.nonNull(financiamentos)) {
            return ResponseEntity.ok().body(financiamentos);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public FinanciamentoDTO salvar(@RequestBody FinanciamentoDTO dto) {
        return financiamentoService.salvar(dto);
    }


}
