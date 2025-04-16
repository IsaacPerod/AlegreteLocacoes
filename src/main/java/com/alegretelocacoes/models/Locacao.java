package models;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class Locacao {
    private String cnhCliente;
    private String placaVeiculo;
    private String dataRetirada;
    private String dataDevolucao;
    private double valorDiaria;
    private double valorAPagar;


    public Locacao(String cnhCliente, String placaVeiculo, String dataRetirada, String dataDevolucao, double valorDiaria) throws ParseException {
        this.cnhCliente = cnhCliente;
        this.placaVeiculo = placaVeiculo;
        this.dataRetirada = dataRetirada;
        this.dataDevolucao = dataDevolucao;
        this.valorDiaria = valorDiaria;
        this.valorAPagar = calcularValorAPagar(dataRetirada, dataDevolucao, valorDiaria);
    }

    public String getPlacaVeiculo() {
        return placaVeiculo;
    }

    public String getCnhCliente() {
        return cnhCliente;
    }

    private double calcularValorAPagar(String dataRetirada, String dataDevolucao, double valorDiaria) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Date inicio = sdf.parse(dataRetirada);
        Date fim = sdf.parse(dataDevolucao);
        long diffInMillies = Math.abs(fim.getTime() - inicio.getTime());
        long dias = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);
        // Garantir pelo menos 1 dia
        if (dias == 0) dias = 1;
        return valorDiaria * dias;
    }

    @Override
    public String toString() {
        return "Locacao: Cliente CNH " + cnhCliente + ", Veiculo Placa " + placaVeiculo + 
               ", Retirada: " + dataRetirada + ", Devolucao: " + dataDevolucao +
               ", Valor Diaria: R$ " + valorDiaria + ", Total a Pagar: R$ " + valorAPagar;
    }
}