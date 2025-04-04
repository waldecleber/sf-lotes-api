package com.wgsystems.sf_loteamentos.controller;

import com.wgsystems.sf_loteamentos.dto.ClienteDTO;
import com.wgsystems.sf_loteamentos.service.ClienteService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/clientes")
public class ClienteController {


    private final ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @GetMapping
    public List<ClienteDTO> buscarClientes() {
        return clienteService.listarClientes();
    }

    @GetMapping(value = "/{cpf}")
    public ResponseEntity<ClienteDTO> buscarClientePorCpf(@PathVariable String cpf) {
        ClienteDTO cliente = clienteService.buscarClientePorCpf(cpf);
        if (Objects.nonNull(cliente)) {
            return ResponseEntity.ok().body(cliente);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ClienteDTO salvar(@RequestBody ClienteDTO dto) {
        return clienteService.salvar(dto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClienteDTO> atualizar(@PathVariable Long id, @RequestBody ClienteDTO cliente) {
        return ResponseEntity.ok(clienteService.atualizar(id, cliente));
    }
}
