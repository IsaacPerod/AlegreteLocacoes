package utils;

import interfaces.ILocadoraLista;
import models.*;

/** Lista duplamente encadeada para armazenar elementos da locadora */
public class ListaLocadora implements ILocadoraLista {
    /** Nós da lista */
    private RegistroLocadora inicio;
    private RegistroLocadora fim;

    /** Construtor - inicializa lista vazia */
    public ListaLocadora() {
        this.inicio = null;
        this.fim = null;
    }

    /** @return Primeiro nó da lista */
    public RegistroLocadora getInicio() {
        return inicio;
    }

    /** @param elemento Elemento a ser inserido no início da lista */
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

    /** @param elemento Elemento a ser inserido no fim da lista */
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

    /**
     * Remove elemento pela chave
     * @param chave Identificador do elemento
     * @return true se removido com sucesso
     */
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

    /**
     * Busca elemento pela chave
     * @param chave Identificador do elemento
     * @return Elemento encontrado ou null
     */
    @Override
    public Object busca(String chave) {
        RegistroLocadora atual = buscaRegistro(chave);
        return (atual != null) ? atual.getInfo() : null;
    }

    /**
     * Busca nó pela chave
     * @param chave Identificador do elemento
     * @return Nó encontrado ou null
     */
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

    /** Imprime elementos do início ao fim */
    @Override
    public void imprimeDoComeco() {
        if (estahVazia()) {
            System.out.println("\n╔════════════════════════════════╗");
            System.out.println("║  Não há registros para exibir. ║");
            System.out.println("╚════════════════════════════════╝");
            return;
        }
        RegistroLocadora atual = inicio;
        while (atual != null) {
            System.out.println(atual.getInfo().toString());
            atual = atual.getProx();
        }
    }

    /** Imprime elementos do fim ao início */
    @Override
    public void imprimeDoFim() {
        if (estahVazia()) {
            System.out.println("\n╔════════════════════════════════╗");
            System.out.println("║  Não há registros para exibir. ║");
            System.out.println("╚════════════════════════════════╝");
            return;
        }
        RegistroLocadora atual = fim;
        while (atual != null) {
            System.out.println(atual.getInfo().toString());
            atual = atual.getAnt();
        }
    }

    /** @return true se lista vazia */
    @Override
    public boolean estahVazia() {
        return inicio == null;
    }

    /** @return Número de elementos na lista */
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

    /**
     * Verifica se objeto corresponde à chave
     * @param dado Objeto a verificar
     * @param chave Chave para comparação
     * @return true se corresponde
     */
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