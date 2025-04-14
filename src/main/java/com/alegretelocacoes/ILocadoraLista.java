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