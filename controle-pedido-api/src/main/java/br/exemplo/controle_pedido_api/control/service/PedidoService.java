package br.exemplo.controle_pedido_api.control.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.exemplo.controle_pedido_api.control.dto.PedidoDTO;
import br.exemplo.controle_pedido_api.control.dto.ProdutoDTO;
import br.exemplo.controle_pedido_api.control.dto.ProdutoPedidoDTO;
import br.exemplo.controle_pedido_api.model.entity.ClienteEntity;
import br.exemplo.controle_pedido_api.model.entity.PedidoEntity;
import br.exemplo.controle_pedido_api.model.entity.UsuarioEntity;
import br.exemplo.controle_pedido_api.model.repository.PedidoRepository;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository repository;

    @Autowired
    private ProdutoPedidoService produtoPedidoService;

    @Autowired
    private ProdutoService produtoService;

    public List<PedidoDTO> findAll() {
        List<PedidoEntity> entities = repository.findAll();
        return entities.stream().map(entity -> convertToPedidoDTO(entity)).toList();
    }

    public Optional<PedidoDTO> findById(Integer id) {
        Optional<PedidoEntity> optional = repository.findById(id);
        if (optional.isEmpty()) {
            return Optional.empty();
        }
        PedidoDTO dto = convertToPedidoDTO(optional.get());
        // Produtos do Pedido
        List<ProdutoPedidoDTO> produtoPedidoDTOs = produtoPedidoService.findAllByIdPedido(id);
        dto.setProdutosPedido(produtoPedidoDTOs);
        return Optional.of(dto);
    }

    public Optional<PedidoDTO> insert(PedidoDTO dto) {
        dto.setDtPedido(new Date());
        Double vlrTotal = calculatePedido(dto, getProdutoDTOs(dto.getProdutosPedido()));
        if (vlrTotal == null) { return Optional.empty(); }
        dto.setVlrTotal(vlrTotal);
        PedidoEntity entity = convertToPedidoEntity(dto);
        entity = repository.save(entity);
        dto.setId(entity.getId());
        dto.getProdutosPedido().stream().forEach(produto -> produto.setIdPedido(dto.getId()));
        dto.setProdutosPedido(produtoPedidoService.insertAll(dto.getProdutosPedido()));
        return Optional.of(dto);
    }

    public Optional<PedidoDTO> update(PedidoDTO dto) {
        Optional<PedidoDTO> optional = findById(dto.getId());
        if (optional.isEmpty()) { return Optional.empty(); }
        List<ProdutoDTO> produtoDTOs = getProdutoDTOs(optional.get().getProdutosPedido());
        produtoPedidoService.deleteByPedido(optional.get().getId());
        for (ProdutoPedidoDTO produtoPedidoDTO : optional.get().getProdutosPedido()) {
            ProdutoDTO produtoDTO = produtoDTOs.stream()
                    .filter(produto -> produto.getId() == produtoPedidoDTO.getIdProduto())
                    .findFirst().get();
            produtoDTO.setQtdEstoque(produtoDTO.getQtdEstoque() + produtoPedidoDTO.getQtdProduto());
            produtoService.update(produtoDTO);
        }
        Double vlrTotal = calculatePedido(dto, getProdutoDTOs(dto.getProdutosPedido()));
        if (vlrTotal == null) { 
            produtoPedidoService.insertAll(optional.get().getProdutosPedido());
            return Optional.empty(); 
        }
        dto.setVlrTotal(vlrTotal);
        PedidoEntity entity = convertToPedidoEntity(dto);
        entity = repository.save(entity);
        dto.getProdutosPedido().stream().forEach(produto -> produto.setIdPedido(dto.getId()));
        dto.setProdutosPedido(produtoPedidoService.insertAll(dto.getProdutosPedido()));
        return Optional.of(dto);
    }

    public void delete(Integer id) {
        repository.deleteById(id);
        produtoPedidoService.deleteByPedido(id);
    }

    private PedidoDTO convertToPedidoDTO(PedidoEntity entity) {
        PedidoDTO dto = new ModelMapper().map(entity, PedidoDTO.class);
        dto.setIdCliente(entity.getCliente().getId());
        dto.setIdUsuario(entity.getUsuario().getId());
        return dto;
    }

    private Double calculatePedido(PedidoDTO dto, List<ProdutoDTO> produtoDTOs) {
        double partialVlrTotal = 0;
        if (produtoDTOs.contains(null)) { return null; }
        for (ProdutoDTO produtoDTO: produtoDTOs) {
            ProdutoPedidoDTO produtoPedidoDTO = dto.getProdutosPedido().stream()
                .filter(produtoPedido -> produtoPedido.getIdProduto() == produtoDTO.getId())
                .findFirst().get();
            if (produtoDTO.getQtdEstoque() < produtoPedidoDTO.getQtdProduto()) { return null; }
            produtoPedidoDTO.setVlrUnitario(
                produtoDTO.getVlrVenda() * (1 - produtoPedidoDTO.getDescontoUnitario()));
            partialVlrTotal += produtoPedidoDTO.getQtdProduto() * produtoPedidoDTO.getVlrUnitario();
        }
        return partialVlrTotal * (1 - dto.getDescontoTotal());
    }

    private List<ProdutoDTO> getProdutoDTOs(List<ProdutoPedidoDTO> produtoPedidoDTOs) {
        List<ProdutoDTO> produtoDTOs = new ArrayList<>();
        for (ProdutoPedidoDTO produtoPedidoDTO : produtoPedidoDTOs) {
            Optional<ProdutoDTO> optional = produtoService.findById(produtoPedidoDTO.getIdProduto());
            produtoDTOs.add(optional.isPresent() ? optional.get() : null);
        }
        return produtoDTOs;
    }

    private PedidoEntity convertToPedidoEntity(PedidoDTO dto) {
        PedidoEntity entity = new ModelMapper().map(dto, PedidoEntity.class);
        ClienteEntity cliente = new ClienteEntity();
        cliente.setId(dto.getIdCliente());
        entity.setCliente(cliente);
        UsuarioEntity usuario = new UsuarioEntity();
        usuario.setId(dto.getIdUsuario());
        entity.setUsuario(usuario);
        return entity;
    }
    
}
