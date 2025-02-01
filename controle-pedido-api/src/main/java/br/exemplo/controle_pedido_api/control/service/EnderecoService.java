package br.exemplo.controle_pedido_api.control.service;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.exemplo.controle_pedido_api.control.dto.EnderecoDTO;
import br.exemplo.controle_pedido_api.model.entity.EnderecoEntity;
import br.exemplo.controle_pedido_api.model.repository.EnderecoRepository;

@Service
public class EnderecoService {

    @Autowired
    private EnderecoRepository repository;

    public EnderecoDTO insert(EnderecoDTO dto) {
        EnderecoEntity entity = new ModelMapper().map(dto, EnderecoEntity.class);
        entity = repository.save(entity);
        dto.setId(entity.getId());
        return dto;
    }

    public Optional<EnderecoDTO> update(EnderecoDTO dto) {
        Optional<EnderecoEntity> optional = repository.findById(dto.getId());
        if (optional.isPresent()) {
            EnderecoEntity entity = new ModelMapper().map(dto, EnderecoEntity.class);
            repository.save(entity);
            return Optional.of(dto);
        }
        return Optional.empty();
    }

    public void delete(Integer id) {
        repository.deleteById(id);
    }
    
}
