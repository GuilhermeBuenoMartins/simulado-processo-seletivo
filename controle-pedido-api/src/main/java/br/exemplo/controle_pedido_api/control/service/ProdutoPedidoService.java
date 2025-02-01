package br.exemplo.controle_pedido_api.control.service;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.exemplo.controle_pedido_api.control.dto.ProdutoDTO;
import br.exemplo.controle_pedido_api.control.dto.ProdutoPedidoDTO;
import br.exemplo.controle_pedido_api.model.entity.ProdutoPedidoEntity;
import br.exemplo.controle_pedido_api.model.repository.ProdutoPedidoRepository;

@Service
public class ProdutoPedidoService {

    @Autowired
    private ProdutoPedidoRepository repository;

    @Autowired
    private ProdutoService produtoService;

    public List<ProdutoPedidoDTO> findAllByIdPedido(Integer idPedido) {
        List<ProdutoPedidoEntity> entities = repository.findAllByIdPedido(idPedido);
        ModelMapper mapper = new ModelMapper();
        return entities.stream()
                .map(entity -> mapper.map(entity, ProdutoPedidoDTO.class)).toList();
    }

    public List<ProdutoPedidoDTO> insertAll(List<ProdutoPedidoDTO> dtos) {
        ModelMapper mapper = new ModelMapper();
        List<ProdutoPedidoEntity> entities = dtos.stream()
                .map(dto -> mapper.map(dto, ProdutoPedidoEntity.class)).toList();
        List<ProdutoDTO> produtoDTOs = new ArrayList<>();
        for (ProdutoPedidoDTO produtoPedidoDTO: dtos) {
            ProdutoDTO produtoDTO = produtoService.findById(produtoPedidoDTO.getIdProduto()).get();
            produtoDTO.setQtdEstoque(produtoDTO.getQtdEstoque() - produtoPedidoDTO.getQtdProduto());
            produtoDTOs.add(produtoDTO);
        }
        produtoService.insertAll(produtoDTOs);
        entities = repository.saveAll(entities);
        return dtos;
    }

    public void deleteByPedido(Integer idPedido) {
        List<ProdutoPedidoEntity> produtosPedidoEntity = repository.findAllByIdPedido(idPedido);
        repository.deleteAll(produtosPedidoEntity);
    }
}
