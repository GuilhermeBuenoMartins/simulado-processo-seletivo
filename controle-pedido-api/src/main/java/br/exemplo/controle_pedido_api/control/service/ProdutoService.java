package br.exemplo.controle_pedido_api.control.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.exemplo.controle_pedido_api.control.dto.ProdutoDTO;
import br.exemplo.controle_pedido_api.model.entity.ProdutoEntity;
import br.exemplo.controle_pedido_api.model.repository.ProdutoRepository;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository repository;

    public List<ProdutoDTO> findAll() {
        List<ProdutoEntity> entities = repository.findAll();
        ModelMapper mapper = new ModelMapper();
        return entities.stream().map(entity -> mapper.map(entity, ProdutoDTO.class)).toList();
    }

    public Optional<ProdutoDTO> findById(Integer id) {
        Optional<ProdutoEntity> optional = repository.findById(id);
        if (optional.isPresent()) {
            ProdutoDTO dto = new ModelMapper().map(optional.get(), ProdutoDTO.class);
            return Optional.of(dto);
        }
        return Optional.empty();
    }


    public List<ProdutoDTO> insertAll(List<ProdutoDTO> dtos) {
        ModelMapper mapper = new ModelMapper();
        List<ProdutoEntity> entities = dtos.stream().map(dto -> mapper.map(dto, ProdutoEntity.class)).toList();
        entities = repository.saveAll(entities);
        return entities.stream().map(entity -> mapper.map(entity, ProdutoDTO.class)).toList();
    }

    public ProdutoDTO insert(ProdutoDTO dto) {
        dto.setDtCadastro(new Date());
        ProdutoEntity entity = new ModelMapper().map(dto, ProdutoEntity.class);
        entity = repository.save(entity);
        dto.setId(entity.getId());
        return dto;
    }

    public Optional<ProdutoDTO> update(ProdutoDTO dto) {
        Optional<ProdutoEntity> optional = repository.findById(dto.getId());
        if (optional.isPresent()) {
            dto.setDtCadastro(optional.get().getDtCadastro());
            ProdutoEntity entity = new ModelMapper().map(dto, ProdutoEntity.class);
            repository.save(entity);
            return Optional.of(dto);
        }
        return Optional.empty();
    }

    public void delete(Integer id) {
        repository.deleteById(id);
    }
}
