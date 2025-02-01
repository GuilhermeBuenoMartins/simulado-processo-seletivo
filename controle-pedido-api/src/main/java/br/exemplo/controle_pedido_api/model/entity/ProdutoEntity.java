package br.exemplo.controle_pedido_api.model.entity;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;

@Entity(name="tb_produto")
@SequenceGenerator(name = "generator_produto_seq", sequenceName = "tb_produto_id_seq", initialValue = 1, allocationSize = 1)
public class ProdutoEntity {

    //#region campos

    @Id
    @GeneratedValue(generator = "generator_produto_seq", strategy = GenerationType.SEQUENCE)
    private Integer id;

    @Column(nullable = false)
    private String nome;

    @Column(name = "qtd_estoque", nullable = false)
    private Integer qtdEstoque;

    @Column(name = "vlr_custo", nullable = false)
    private Double vlrCusto;

    @Column(name = "vlr_venda", nullable = false)
    private Double vlrVenda;

    @Column(name = "dt_cadastro", nullable = false)
    private Date dtCadastro;

    @Column(nullable = false)
    private String observacao;

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

    public Integer getQtdEstoque() {
        return qtdEstoque;
    }

    public void setQtdEstoque(Integer qtdEstoque) {
        this.qtdEstoque = qtdEstoque;
    }

    public Double getVlrCusto() {
        return vlrCusto;
    }

    public void setVlrCusto(Double vlrCusto) {
        this.vlrCusto = vlrCusto;
    }

    public Double getVlrVenda() {
        return vlrVenda;
    }

    public void setVlrVenda(Double vlrVenda) {
        this.vlrVenda = vlrVenda;
    }

    public Date getDtCadastro() {
        return dtCadastro;
    }

    public void setDtCadastro(Date dtCadastro) {
        this.dtCadastro = dtCadastro;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    //#endregion getters e setters
    
}
