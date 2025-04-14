package models;

public class Categoria {
    private String nome;
    private int identificador;

    public Categoria(String nome, int identificador) {
        this.nome = nome;
        this.identificador = identificador;
    }

    public int getIdentificador() {
        return identificador;
    }

    public String getNome() {
        return nome;
    }

    @Override
    public String toString() {
        return "Categoria: " + nome + " (ID: " + identificador + ")";
    }
}
