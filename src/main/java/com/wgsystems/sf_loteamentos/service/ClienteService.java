package com.wgsystems.sf_loteamentos.service;

import com.wgsystems.sf_loteamentos.dto.ClienteDTO;
import com.wgsystems.sf_loteamentos.entity.Cliente;
import com.wgsystems.sf_loteamentos.exceptions.ClienteDuplicadoException;
import com.wgsystems.sf_loteamentos.exceptions.ClienteNaoEncontrado;
import com.wgsystems.sf_loteamentos.repository.ClienteRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ClienteService {

    private final ClienteRepository clienteRepository;

    private final ModelMapper mapper;

    public ClienteService(ClienteRepository clienteRepository, ModelMapper mapper) {
        this.clienteRepository = clienteRepository;
        this.mapper = mapper;
    }

    public ClienteDTO buscarClientes() {
        return ClienteDTO.builder().build();
    }

    public ClienteDTO salvar(ClienteDTO clienteDTO) {
        Optional<Cliente> optional = clienteRepository.findByCpf(clienteDTO.getCpf());
        if (optional.isPresent()) {
            throw new ClienteDuplicadoException("Já existe um Cliente com este CPF");
        }
        Cliente cliente = mapper.map(clienteDTO, Cliente.class);
        clienteRepository.save(cliente);
        return clienteDTO;
    }

    public List<ClienteDTO> listarClientes() {
        return clienteRepository.findAll().stream()
                .map(cliente -> mapper.map(cliente, ClienteDTO.class))
                .collect(Collectors.toList());
    }

    public ClienteDTO buscarClientePorNome(String nome) {
        return clienteRepository.findByNome(nome)
                .map(cliente -> mapper.map(cliente, ClienteDTO.class))
                .orElseThrow(() -> new ClienteNaoEncontrado("Cliente não encontrado"));
    }

    public ClienteDTO buscarClientePorCpf(String cpf) {
        return clienteRepository.findByCpf(cpf)
                .map(cliente -> mapper.map(cliente, ClienteDTO.class))
                .orElseThrow(() -> new ClienteNaoEncontrado("Cliente não encontrado"));
    }

    public ClienteDTO atualizar(Long id, ClienteDTO dto) {
        if (clienteRepository.existsById(id)) {
            Cliente cliente = mapper.map(dto, Cliente.class);
            cliente.setId(id);
            return mapper.map(clienteRepository.save(cliente), ClienteDTO.class);
        } else {
            throw new ClienteNaoEncontrado("Cliente não encontrado");
        }
    }
}
