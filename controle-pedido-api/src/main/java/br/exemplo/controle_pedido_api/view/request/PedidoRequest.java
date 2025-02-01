package br.exemplo.controle_pedido_api.view.request;

import java.util.List;

public class PedidoRequest {

    //#region campos

    private Integer idUsuario;

    private Integer idCliente;

    private Double descontoTotal;

    private List<ProdutoPedidoRequest> produtosPedido;

    //#endregion campos

    //#region getters e setters

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Integer getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Integer idCliente) {
        this.idCliente = idCliente;
    }

    public Double getDescontoTotal() {
        return descontoTotal;
    }

    public void setDescontoTotal(Double descontoTotal) {
        this.descontoTotal = descontoTotal;
    }

    public List<ProdutoPedidoRequest> getProdutosPedido() {
        return produtosPedido;
    }

    public void setProdutosPedido(List<ProdutoPedidoRequest> produtosPedido) {
        this.produtosPedido = produtosPedido;
    }

    //#endregion getters e setters
    
}