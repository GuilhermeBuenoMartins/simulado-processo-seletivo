package br.exemplo.controle_pedido_api.control.service;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.exemplo.controle_pedido_api.control.dto.UsuarioDTO;
import br.exemplo.controle_pedido_api.model.entity.UsuarioEntity;
import br.exemplo.controle_pedido_api.model.repository.UsuarioRepository;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository repository;

    @Autowired
    private PasswordEncoder encoder;

    public List<UsuarioDTO> findAll() {
        List<UsuarioEntity> entities = repository.findAll();
        ModelMapper mapper = new ModelMapper();
        return entities.stream().map(entity -> mapper.map(entity, UsuarioDTO.class)).toList();
    }

    public Optional<UsuarioDTO> findById(Integer id) {
        Optional<UsuarioEntity> optional = repository.findById(id);
        if (optional.isPresent()) {
            UsuarioDTO dto = new ModelMapper().map(optional.get(), UsuarioDTO.class);
            return Optional.of(dto);
        }
        return Optional.empty();
    }

    public Optional<UsuarioDTO> insert(UsuarioDTO dto) {
        if (repository.findByEmail(dto.getEmail()).isPresent()) {
            return Optional.empty();
        }
        dto.setSenha(encoder.encode(dto.getSenha()));
        UsuarioEntity entity = new ModelMapper().map(dto, UsuarioEntity.class);
        entity = repository.save(entity);
        dto.setId(entity.getId());
        return Optional.of(dto);
    }

    public Optional<UsuarioDTO> update(UsuarioDTO dto) {
        Optional<UsuarioEntity> optional = repository.findById(dto.getId());
        if (optional.isPresent()) {
            Optional<UsuarioEntity> existent = repository.findByEmail(dto.getEmail());
            if (existent.isPresent()) {
                dto = new ModelMapper().map(existent.get(), UsuarioDTO.class);
                return Optional.of(dto);
            }
            dto.setSenha(encoder.encode(dto.getSenha()));
            UsuarioEntity entity = new ModelMapper().map(dto, UsuarioEntity.class);
            repository.save(entity);
            return Optional.of(dto);
        }
        return Optional.empty();
    }
    
    public void deleteById(Integer id) {
        repository.deleteById(id);
    }
}
