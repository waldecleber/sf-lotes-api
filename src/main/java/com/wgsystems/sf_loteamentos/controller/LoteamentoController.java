package com.wgsystems.sf_loteamentos.controller;

import com.wgsystems.sf_loteamentos.dto.ClienteDTO;
import com.wgsystems.sf_loteamentos.dto.LoteamentoDTO;
import com.wgsystems.sf_loteamentos.service.ClienteService;
import com.wgsystems.sf_loteamentos.service.LoteamentoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/loteamentos")
public class LoteamentoController {


    private final LoteamentoService loteamentoService;

    public LoteamentoController(LoteamentoService loteamentoService) {
        this.loteamentoService = loteamentoService;
    }

    @GetMapping
    public List<LoteamentoDTO> buscarLoteamentos() {
        return loteamentoService.listarLoteamentos();
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<LoteamentoDTO> buscarLoteamentoPorId(@PathVariable String id) {
        LoteamentoDTO cliente = loteamentoService.buscarLoteamentoPorId(id);
        if (Objects.nonNull(cliente)) {
            return ResponseEntity.ok().body(cliente);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public LoteamentoDTO salvar(@RequestBody LoteamentoDTO dto) {
        return loteamentoService.salvar(dto);
    }

//    @PutMapping("/{id}")
//    public ResponseEntity<ClienteDTO> atualizar(@PathVariable Long id, @RequestBody ClienteDTO cliente) {
//        return ResponseEntity.ok(clienteService.atualizar(id, cliente));
//    }
}
