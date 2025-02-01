package br.exemplo.controle_pedido_api.view.request;

public class ProdutoPedidoRequest {

    //#region campos

    private Integer idProduto;

    private Integer qtdProduto;

    private Double descontoUnitario;

    //#endregion campos

    //#region getters e setters

    public Integer getIdProduto() {
        return idProduto;
    }

    public void setIdProduto(Integer idProduto) {
        this.idProduto = idProduto;
    }

    public Integer getQtdProduto() {
        return qtdProduto;
    }

    public void setQtdProduto(Integer qtdProduto) {
        this.qtdProduto = qtdProduto;
    }

    public Double getDescontoUnitario() {
        return descontoUnitario;
    }

    public void setDescontoUnitario(Double descontoUnitario) {
        this.descontoUnitario = descontoUnitario;
    }

    //#endregion getters e setters
    
}
