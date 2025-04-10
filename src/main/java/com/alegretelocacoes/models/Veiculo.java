package models;

public class Veiculo {
    private String placa;
    private String modelo;
    private String marca;
    private int ano;
    private int potencia;
    private int lugares;
    private Categoria categoria;

    public Veiculo(String placa, String modelo, String marca, int ano, int potencia, int lugares, Categoria categoria) {
        this.placa = placa;
        this.modelo = modelo;
        this.marca = marca;
        this.ano = ano;
        this.potencia = potencia;
        this.lugares = lugares;
        this.categoria = categoria;
    }

    public String getPlaca() {
        return placa;
    }

    public int getPotencia() {
        return potencia;
    }

    public int getLugares() {
        return lugares;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    @Override
    public String toString() {
        return "Veículo: " + modelo + " (" + marca + "), Placa: " + placa + ", Ano: " + ano + 
               ", Potência: " + potencia + ", Lugares: " + lugares + ", Categoria: " + categoria.getNome();
    }
}
