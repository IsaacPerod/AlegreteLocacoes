/**
 * Interface defining operations for a double-linked list data structure used in a rental system.
 * Provides methods for inserting elements at the beginning and end of the list,
 * removing elements by key, searching elements, printing in both directions,
 * checking if the list is empty and getting its size.
 */
package interfaces;

public interface ILocadoraLista {
    
    void insereInicio(Object elemento);
    void insereFim(Object elemento);
    boolean remove(String chave);
    Object busca(String chave);
    void imprimeDoComeco();
    void imprimeDoFim();
    boolean estahVazia();
    int tamanho();
}