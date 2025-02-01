package br.exemplo.controle_pedido_api.control.dto;

import java.util.Date;
import java.util.List;

public class PedidoDTO {

    //#region campos

    private Integer id;

    private Integer idUsuario;

    private Integer idCliente;

    private Date dtPedido;

    private Double vlrTotal;

    private Double descontoTotal;

    private List<ProdutoPedidoDTO> produtosPedido;

    //#endregion campos

    //#region getters e setters

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

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

    public Date getDtPedido() {
        return dtPedido;
    }

    public void setDtPedido(Date dtPedido) {
        this.dtPedido = dtPedido;
    }

    public Double getVlrTotal() {
        return vlrTotal;
    }

    public void setVlrTotal(Double vlrTotal) {
        this.vlrTotal = vlrTotal;
    }

    public Double getDescontoTotal() {
        return descontoTotal;
    }

    public void setDescontoTotal(Double descontoTotal) {
        this.descontoTotal = descontoTotal;
    }

    public List<ProdutoPedidoDTO> getProdutosPedido() {
        return produtosPedido;
    }

    public void setProdutosPedido(List<ProdutoPedidoDTO> produtosPedido) {
        this.produtosPedido = produtosPedido;
    }

    //#endregion getters e setters
    
}
