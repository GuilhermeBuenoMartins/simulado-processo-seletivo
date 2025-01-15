package br.exemplo.controle_pedido_api.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.exemplo.controle_pedido_api.model.entity.UsuarioEntity;
import java.util.Optional;


@Repository
public interface UsuarioRepository extends JpaRepository<UsuarioEntity, Integer> {

    Optional<UsuarioEntity> findByEmail(String email);

}
