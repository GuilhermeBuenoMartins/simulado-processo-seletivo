package br.exemplo.controle_pedido_api.control.service;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.exemplo.controle_pedido_api.control.dto.ClienteDTO;
import br.exemplo.controle_pedido_api.model.entity.ClienteEntity;
import br.exemplo.controle_pedido_api.model.repository.ClienteRepository;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository repository;

    @Autowired
    private EnderecoService endereco;

    public List<ClienteDTO> findAll() {
        List<ClienteEntity> entities = repository.findAll();
        ModelMapper mapper = new ModelMapper();
        return entities.stream().map(entity -> mapper.map(entity, ClienteDTO.class)).toList();
    }

    public Optional<ClienteDTO> findById(Integer id) {
        Optional<ClienteEntity> optional = repository.findById(id);
        if (optional.isPresent()) {
            ClienteDTO dto = new ModelMapper().map(optional.get(), ClienteDTO.class);
            return Optional.of(dto);
        }
        return Optional.empty();
    }

    public ClienteDTO insert(ClienteDTO dto) {
        dto.setEndereco(endereco.insert(dto.getEndereco()));
        ClienteEntity entity = new ModelMapper().map(dto, ClienteEntity.class);
        entity = repository.save(entity);
        dto.setId(entity.getId());
        return dto;
    }

    public Optional<ClienteDTO> update(ClienteDTO dto) {
        Optional<ClienteEntity> optional = repository.findById(dto.getId());
        if (optional.isPresent()) {
            dto.getEndereco().setId(optional.get().getEndereco().getId());
            endereco.update(dto.getEndereco());
            ClienteEntity entity = new ModelMapper().map(dto, ClienteEntity.class);
            repository.save(entity);
            return Optional.of(dto);
        }
        return Optional.empty();
    }

    public void delete(Integer id) {
        Optional<ClienteEntity> optional = repository.findById(id);
        if (optional.isPresent()) {
            repository.deleteById(id);
            endereco.delete(optional.get().getEndereco().getId());
        }
    }
}
