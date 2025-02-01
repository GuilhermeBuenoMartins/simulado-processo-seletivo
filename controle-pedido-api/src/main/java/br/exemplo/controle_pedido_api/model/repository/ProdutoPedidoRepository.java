package br.exemplo.controle_pedido_api.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.exemplo.controle_pedido_api.model.entity.ProdutoPedidoEntity;
import br.exemplo.controle_pedido_api.model.entity.ProdutoPedidoPK;

@Repository
public interface ProdutoPedidoRepository extends JpaRepository<ProdutoPedidoEntity, ProdutoPedidoPK> {

    List<ProdutoPedidoEntity> findAllByIdPedido(Integer idPedido);
    
}
