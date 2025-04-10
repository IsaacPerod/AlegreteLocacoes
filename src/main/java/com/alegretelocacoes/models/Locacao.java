package models;

public class Locacao {
    private String cnhCliente;
    private String placaVeiculo;
    private String dataRetirada;
    private String dataDevolucao;

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