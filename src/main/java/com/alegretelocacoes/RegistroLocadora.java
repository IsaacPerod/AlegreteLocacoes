package com.alegretelocacoes;

public class RegistroLocadora {
    private Object info;           // Armazena o registro (Object)
    private RegistroLocadora ant;  // Referência ao registro anterior
    private RegistroLocadora prox; // Referência ao registro próximo

    public RegistroLocadora(Object info) {
        this.info = info;
        this.ant = null;
        this.prox = null;
    }

    public Object getInfo() {
        return info;
    }

    public RegistroLocadora getAnt() {
        return ant;
    }

    public void setAnt(RegistroLocadora r) {
        this.ant = r;
    }

    public RegistroLocadora getProx() {
        return prox;
    }

    public void setProx(RegistroLocadora r) {
        this.prox = r;
    }
}