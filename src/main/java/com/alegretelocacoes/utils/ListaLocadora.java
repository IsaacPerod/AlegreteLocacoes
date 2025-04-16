package com.alegretelocacoes.utils;
import com.alegretelocacoes.interfaces.ILocadoraLista;
import com.alegretelocacoes.models.*;

public class ListaLocadora implements ILocadoraLista {
    private RegistroLocadora inicio;
    private RegistroLocadora fim;

    public ListaLocadora() {
        this.inicio = null;
        this.fim = null;
    }

    public RegistroLocadora getInicio() {
        return inicio;
    }

    @Override
    public void insereInicio(Object elemento) {
        RegistroLocadora novo = new RegistroLocadora(elemento);
        if (estahVazia()) {
            inicio = novo;
            fim = novo;
        } else {
            novo.setProx(inicio);
            inicio.setAnt(novo);
            inicio = novo;
        }
    }

    @Override
    public void insereFim(Object elemento) {
        RegistroLocadora novo = new RegistroLocadora(elemento);
        if (estahVazia()) {
            inicio = novo;
            fim = novo;
        } else {
            fim.setProx(novo);
            novo.setAnt(fim);
            fim = novo;
        }
    }

    @Override
    public boolean remove(String chave) {
        RegistroLocadora noRemover = buscaRegistro(chave); // Ajustado para o novo nome
        if (noRemover == null) {
            return false;
        }

        // Caso seja o único registro
        if (noRemover == inicio && noRemover == fim) {
            inicio = null;
            fim = null;
        }
        // Caso seja o primeiro registro
        else if (noRemover == inicio) {
            inicio = inicio.getProx();
            inicio.setAnt(null);
        }
        // Caso seja o último registro
        else if (noRemover == fim) {
            fim = fim.getAnt();
            fim.setProx(null);
        }
        // Caso esteja no meio
        else {
            noRemover.getAnt().setProx(noRemover.getProx());
            noRemover.getProx().setAnt(noRemover.getAnt());
        }
        return true;
    }

    @Override
    public Object busca(String chave) {
        RegistroLocadora atual = buscaRegistro(chave);
        return (atual != null) ? atual.getInfo() : null;
    }

    // Método auxiliar para buscar o registro (renomeado de buscaNo para buscaRegistro)
    private RegistroLocadora buscaRegistro(String chave) {
        RegistroLocadora atual = inicio;
        while (atual != null) {
            if (matchesChave(atual.getInfo(), chave)) {
                return atual;
            }
            atual = atual.getProx();
        }
        return null;
    }

    @Override
    public void imprimeDoComeco() {
        RegistroLocadora atual = inicio;
        while (atual != null) {
            System.out.println(atual.getInfo().toString());
            atual = atual.getProx();
        }
    }

    @Override
    public void imprimeDoFim() {
        RegistroLocadora atual = fim;
        while (atual != null) {
            System.out.println(atual.getInfo().toString());
            atual = atual.getAnt();
        }
    }

    @Override
    public boolean estahVazia() {
        return inicio == null;
    }

    @Override
    public int tamanho() {
        int tam = 0;
        RegistroLocadora atual = inicio;
        while (atual != null) {
            tam++;
            atual = atual.getProx();
        }
        return tam;
    }

    // Método auxiliar para verificar a chave dependendo do tipo de objeto
    private boolean matchesChave(Object dado, String chave) {
        if (dado instanceof Cliente) {
            return ((Cliente) dado).getCpf().equals(chave);
        } else if (dado instanceof Veiculo) {
            return ((Veiculo) dado).getPlaca().equals(chave);
        } else if (dado instanceof Categoria) {
            return String.valueOf(((Categoria) dado).getIdentificador()).equals(chave);
        } else if (dado instanceof Locacao) {
            return ((Locacao) dado).getPlacaVeiculo().equals(chave);
        }
        return false;
    }
}