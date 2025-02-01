package br.exemplo.controle_pedido_api.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.exemplo.controle_pedido_api.model.entity.ClienteEntity;

@Repository
public interface ClienteRepository extends JpaRepository<ClienteEntity, Integer> {

}
