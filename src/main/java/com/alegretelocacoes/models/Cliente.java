package com.alegretelocacoes.models;

public class Cliente {
    private String nome;
    private String cnh;
    private String telefone;
    private String cpf;

    public Cliente(String nome, String cnh, String telefone, String cpf) {
        this.nome = nome;
        this.cnh = cnh;
        this.telefone = telefone;
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getCnh() {
        return cnh;
    }

    public void setCnh(String cnh) {
        this.cnh = cnh;
    }

    @Override
    public String toString() {
        return "Cliente: " + nome + ", CNH: " + cnh + ", Telefone: " + telefone + ", CPF: " + cpf;
    }
}