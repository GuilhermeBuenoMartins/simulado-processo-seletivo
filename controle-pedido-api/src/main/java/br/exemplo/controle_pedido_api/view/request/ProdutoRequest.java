package br.exemplo.controle_pedido_api.view.request;

public class ProdutoRequest {

    // #region campos

    private String nome;

    private Integer qtdEstoque;

    private Double vlrCusto;

    private Double vlrVenda;

    private String observacao;

    // #endregion campos

    // #region getters e setters

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

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    // #endregion getters e setters

}
