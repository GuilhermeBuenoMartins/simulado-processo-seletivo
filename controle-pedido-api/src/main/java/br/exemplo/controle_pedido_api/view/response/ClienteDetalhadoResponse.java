package br.exemplo.controle_pedido_api.view.response;

public class ClienteDetalhadoResponse extends ClienteResponse {

    private EnderecoResponse endereco;

    public EnderecoResponse getEndereco() {
        return endereco;
    }

    public void setEndereco(EnderecoResponse endereco) {
        this.endereco = endereco;
    }

}
