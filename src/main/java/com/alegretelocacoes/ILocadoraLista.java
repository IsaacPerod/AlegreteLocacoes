package com.alegretelocacoes;

public interface ILocadoraLista {
    
    void insereInicio(Object elemento);
    void insereFim(Object elemento);
    boolean remove(String chave);
    Object busca(String chave);
    void imprimeFrente();
    void imprimeTras();
    boolean estahVazia();
    int tamanho();
}