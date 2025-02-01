package br.exemplo.controle_pedido_api.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;

@Entity(name = "tb_produto_pedido")
@IdClass(ProdutoPedidoPK.class)
public class ProdutoPedidoEntity {

    //#region campos

    @Id
    @Column(name = "id_pedido")
    private Integer idPedido;

    @Id
    @Column(name = "id_produto")
    private Integer idProduto;

    @Column(name = "qtd_produto", nullable = false)
    private Integer qtdProduto;

    @Column(name = "vlr_unitario", nullable = false)
    private Double vlrUnitario;

    @Column(name = "desconto_unitario", nullable = false)
    private Double descontoUnitario;

    //#endregion campos

    //#region getters e setters
    
    public Integer getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(Integer idPedido) {
        this.idPedido = idPedido;
    }

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

    public Double getVlrUnitario() {
        return vlrUnitario;
    }

    public void setVlrUnitario(Double vlrUnitario) {
        this.vlrUnitario = vlrUnitario;
    }

    public Double getDescontoUnitario() {
        return descontoUnitario;
    }

    public void setDescontoUnitario(Double descontoUnitario) {
        this.descontoUnitario = descontoUnitario;
    }

    //#endregion getters e setters
        
}
