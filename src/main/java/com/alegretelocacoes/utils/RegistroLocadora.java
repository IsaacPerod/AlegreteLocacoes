package com.alegretelocacoes.utils;

public class RegistroLocadora {
    private Object info;
    private RegistroLocadora ant;
    private RegistroLocadora prox;

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