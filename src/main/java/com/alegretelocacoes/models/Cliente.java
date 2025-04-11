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

    public String getCpf() {
        return cpf;
    }

    public String getCnh() {
        return cnh;
    }

    @Override
    public String toString() {
        return "Cliente: " + nome + ", CNH: " + cnh + ", Telefone: " + telefone + ", CPF: " + cpf;
    }

    // Getters e setters para edição
    public void setNome(String nome) { this.nome = nome; }
    public void setTelefone(String telefone) { this.telefone = telefone; }
}