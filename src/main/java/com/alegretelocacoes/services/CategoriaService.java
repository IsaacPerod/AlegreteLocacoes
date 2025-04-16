package services;

import models.Categoria;
import models.Veiculo;
import utils.ListaLocadora;
import utils.RegistroLocadora;

import java.util.Scanner;

public class CategoriaService {
    private ListaLocadora categorias;
    private ListaLocadora veiculos;

    public CategoriaService(ListaLocadora categorias, ListaLocadora veiculos) {
        this.categorias = categorias;
        this.veiculos = veiculos;
    }

    public void gerenciarCategorias() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n╔══════════════════════════════════════╗");
            System.out.println("║         GERENCIAR CATEGORIAS         ║");
            System.out.println("╠════╗═════════════════════════════════╣");
            System.out.println("║ 1  ║ Adicionar Categoria             ║");
            System.out.println("║ 2  ║ Editar Categoria                ║");
            System.out.println("║ 3  ║ Listar Categorias do começo     ║");
            System.out.println("║ 4  ║ Listar Categorias do fim        ║");
            System.out.println("║ 5  ║ Excluir Categoria               ║");
            System.out.println("╠════╝═════════════════════════════════╣");
            System.out.println("║ 0. Voltar                            ║");
            System.out.println("╚══════════════════════════════════════╝");
            System.out.print("Escolha uma opção: ");

            int opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1: adicionarCategoria(); break;
                case 2: editarCategoria(); break;
                case 3: listarCategoriasFrente(); break;
                case 4: listarCategoriasTras(); break;
                case 5: excluirCategoria(); break;
                case 0: return;
                default: System.out.println("Opção inválida!");
            }
        }
    }

    private void adicionarCategoria() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Digite o nome da nova categoria: ");
        String nome = scanner.nextLine();

        System.out.print("Digite o identificador da nova categoria: ");
        int id = scanner.nextInt();

        if (buscarCategoria(id) != null) {
            System.out.println("Categoria já existe.");
            return;
        }

        Categoria nova = new Categoria(nome, id);
        categorias.insereFim(nova);
        System.out.println("Categoria adicionada com sucesso!");
    }

    private void editarCategoria() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Digite o identificador da categoria a ser editada: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Limpar o buffer

        Categoria categoria = buscarCategoria(id);
        if (categoria == null) {
            System.out.println("Categoria não encontrada.");
            return;
        }

        System.out.print("Digite o novo nome da categoria: ");
        String novoNome = scanner.nextLine();

        categoria.setNome(novoNome);
        System.out.println("Categoria editada com sucesso!");
    }

    private void listarCategoriasFrente() {
        categorias.imprimeDoComeco();
    }

    private void listarCategoriasTras() {
        categorias.imprimeDoFim();
    }

    private void excluirCategoria() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Digite o identificador da categoria a ser excluída: ");
        int id = scanner.nextInt();
        Categoria categoria = buscarCategoria(id);

        if (categoria == null) {
            System.out.println("Categoria não encontrada.");
            return;
        }

        if (veiculoAtrelado(categoria)) {
            System.out.println("Não é possível excluir a categoria. Existem veículos atrelados a ela.");
            return;
        }

        categorias.remove(String.valueOf(categoria.getIdentificador()));
        System.out.println("Categoria excluída com sucesso!");
    }

    private boolean veiculoAtrelado(Categoria categoria) {
    RegistroLocadora atual = veiculos.getInicio();
    while (atual != null) {
        Veiculo veiculo = (Veiculo) atual.getInfo();
        if (veiculo != null && veiculo.getCategoria().equals(categoria)) {
            return true;
        }
        atual = atual.getProx();
    }
    return false;
}

    private Categoria buscarCategoria(int id) {
        return (Categoria) categorias.busca(String.valueOf(id));
    }
}