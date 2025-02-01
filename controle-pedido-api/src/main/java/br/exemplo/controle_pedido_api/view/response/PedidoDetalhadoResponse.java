package br.exemplo.controle_pedido_api.view.response;

import java.util.List;

public class PedidoDetalhadoResponse extends PedidoResponse {

    private List<ProdutoPedidoResponse> produtosPedido;

    public List<ProdutoPedidoResponse> getProdutosPedido() {
        return produtosPedido;
    }

    public void setProdutosPedido(List<ProdutoPedidoResponse> produtosPedido) {
        this.produtosPedido = produtosPedido;
    }

}
