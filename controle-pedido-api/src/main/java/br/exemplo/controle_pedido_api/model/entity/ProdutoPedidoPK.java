package br.exemplo.controle_pedido_api.model.entity;

import java.io.Serializable;

import jakarta.persistence.Column;

public class ProdutoPedidoPK implements Serializable {

    //#region campos

    @Column(name = "id_pedido")
    private Integer idPedido;

    @Column(name = "id_produto")
    private Integer idProduto;

    //#endregion campos

    //#region getters e setter

    public Integer getPedido() {
        return idPedido;
    }

    public Integer getProduto() {
        return idProduto;
    }

    //#endregion getters e setters

    //#region hash code e equals

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((idPedido == null) ? 0 : idPedido.hashCode());
        result = prime * result + ((idProduto == null) ? 0 : idProduto.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        ProdutoPedidoPK other = (ProdutoPedidoPK) obj;
        if (idPedido == null) {
            if (other.idPedido != null)
                return false;
        } else if (!idPedido.equals(other.idPedido))
            return false;
        if (idProduto == null) {
            if (other.idProduto != null)
                return false;
        } else if (!idProduto.equals(other.idProduto))
            return false;
        return true;
    }

    //#endregion hash code e equals
    
}
