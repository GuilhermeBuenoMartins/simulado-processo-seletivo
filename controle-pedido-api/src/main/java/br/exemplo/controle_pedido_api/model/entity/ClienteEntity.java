package br.exemplo.controle_pedido_api.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;

@Entity(name = "tb_cliente")
@SequenceGenerator(name = "generator_cliente_seq", sequenceName = "tb_cliente_id_seq", initialValue = 1, allocationSize = 1)
public class ClienteEntity {

    //#region campos

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "generator_cliente_seq")
    private Integer id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private String cpf;

    @Column(nullable = false)
    private String telefone;

    @Column(nullable = false)
    private String email;

    @OneToOne(optional = false)
    @JoinColumn(name = "id_endereco", nullable = false)
    private EnderecoEntity endereco;

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

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public EnderecoEntity getEndereco() {
        return endereco;
    }

    public void setEndereco(EnderecoEntity endereco) {
        this.endereco = endereco;
    }

    //#endregion getters e setters
    
}
