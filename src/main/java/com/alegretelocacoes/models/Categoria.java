package com.alegretelocacoes.models;
/*
                    "1 - 1010;esportivo\n" +
                    "2 - 1011;sedan comptacto\n" +
                    "3 - 1012;sedan medio\n" +
                    "4 - 1013;SUV compacto\n" +
                    "5 - 1014;SUV\n" +
                    "6 - 1015;caminhonete\n" +
                    "7 - 1016;hatch";
 */
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
