package br.exemplo.controle_pedido_api.control.dto;

import java.util.Date;

public class ProdutoDTO {

    //#region campos

    private Integer id;

    private String nome;

    private Integer qtdEstoque;

    private Double vlrCusto;

    private Double vlrVenda;

    private Date dtCadastro;

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
