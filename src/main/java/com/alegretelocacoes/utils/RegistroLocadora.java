package com.alegretelocacoes.utils;

/**
 * Implementa um nó para uma estrutura de dados de lista duplamente encadeada.
 * Cada nó contém uma informação genérica e referências para o nó anterior e próximo.
 */
public class RegistroLocadora {
    private Object info;
    private RegistroLocadora ant;
    private RegistroLocadora prox;

    /**
     * Cria um novo nó com a informação especificada.
     * @param info A informação a ser armazenada no nó
     */
    public RegistroLocadora(Object info) {
        this.info = info;
        this.ant = null;
        this.prox = null;
    }

    /**
     * Retorna a informação armazenada no nó.
     * @return O objeto armazenado no nó
     */
    public Object getInfo() {
        return info;
    }

    /**
     * Retorna a referência para o nó anterior.
     * @return O nó anterior na lista
     */
    public RegistroLocadora getAnt() {
        return ant;
    }

    /**
     * Define o nó anterior na lista.
     * @param r O nó a ser definido como anterior
     */
    public void setAnt(RegistroLocadora r) {
        this.ant = r;
    }

    /**
     * Retorna a referência para o próximo nó.
     * @return O próximo nó na lista
     */
    public RegistroLocadora getProx() {
        return prox;
    }

    /**
     * Define o próximo nó na lista.
     * @param r O nó a ser definido como próximo
     */
    public void setProx(RegistroLocadora r) {
        this.prox = r;
    }
}