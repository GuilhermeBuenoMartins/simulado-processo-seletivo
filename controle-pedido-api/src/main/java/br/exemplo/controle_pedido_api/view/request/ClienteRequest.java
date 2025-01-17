package br.exemplo.controle_pedido_api.view.request;

public class ClienteRequest {

    //#region campos

    private String nome;

    private String cpf;

    private String telefone;

    private String email;

    private EnderecoRequest endereco;

    //#endregion campos

    //#region getters e setters

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public EnderecoRequest getEndereco() {
        return endereco;
    }

    public void setEndereco(EnderecoRequest endereco) {
        this.endereco = endereco;
    }

    //#endregion getters e setters
    
}
