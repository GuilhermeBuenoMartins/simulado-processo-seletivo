package br.exemplo.controle_pedido_api.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;

@Entity(name = "tb_usuario")
@SequenceGenerator(name = "generator_usuario_seq", sequenceName = "tb_usuario_id_seq", initialValue = 1, allocationSize = 1)
public class UsuarioEntity {

    //#region campos

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "generator_usuario_seq")
    private Integer id;

    @Column(nullable= false)
    private String nome;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String senha;

    //#endregion campos

    //#region getters e setters

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    //#endregion getters e setters
    
}
