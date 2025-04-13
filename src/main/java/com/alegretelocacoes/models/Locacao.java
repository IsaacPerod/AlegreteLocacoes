package com.alegretelocacoes.models;

public class Locacao {
    private final String cnhCliente;
    private final String placaVeiculo;
    private final String dataRetirada;
    private final String dataDevolucao;

    public Locacao(String cnhCliente, String placaVeiculo, String dataRetirada, String dataDevolucao, double valor) {
        this.cnhCliente = cnhCliente;
        this.placaVeiculo = placaVeiculo;
        this.dataRetirada = dataRetirada;
        this.dataDevolucao = dataDevolucao;
    }

    public String getPlacaVeiculo() {
        return placaVeiculo;
    }

    public String getCnhCliente() {
        return cnhCliente;
    }

    @Override
    public String toString() {
        return "Locação: Cliente CNH " + cnhCliente + ", Veículo Placa " + placaVeiculo + 
               ", Retirada: " + dataRetirada + ", Devolução: " + dataDevolucao;
    }
}