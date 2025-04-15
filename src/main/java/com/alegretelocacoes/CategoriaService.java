package com.alegretelocacoes;

import com.alegretelocacoes.models.Categoria;
import com.alegretelocacoes.RegistroLocadora;

import java.io.*;
import java.util.Scanner;

public class CategoriaService {
    private RegistroLocadora inicio;
    private RegistroLocadora fim;
    private final String caminhoArquivo = "categorias.csv";
    private final Scanner scanner = new Scanner(System.in);

    public CategoriaService() {
        carregarCategoriasDoCSV();
    }

    private void carregarCategoriasDoCSV() {
        try (BufferedReader br = new BufferedReader(new FileReader(caminhoArquivo))) {
            String linha;
            br.readLine(); // pula cabeçalho
            while ((linha = br.readLine()) != null) {
                String[] partes = linha.split(";");
                int id = Integer.parseInt(partes[0]);
                String nome = partes[1];
                Categoria categoria = new Categoria(nome, id);
                insereFim(categoria);
            }
        } catch (IOException e) {
            System.out.println("Erro ao carregar CSV: " + e.getMessage());
        }
    }

    private void salvarCategoriasNoCSV() {
        try (PrintWriter writer = new PrintWriter(new FileWriter(caminhoArquivo))) {
            writer.println("identificador;nome");
            RegistroLocadora atual = inicio;
            while (atual != null) {
                Categoria cat = (Categoria) atual.getInfo();
                writer.printf("%d;%s%n", cat.getIdentificador(), cat.getNome());
                atual = atual.getProx();
            }
        } catch (IOException e) {
            System.out.println("Erro ao salvar CSV: " + e.getMessage());
        }
    }

    public boolean adicionarCategoria(Categoria nova) {
        if (buscarCategoria(String.valueOf(nova.getIdentificador())) != null) {
            System.out.println("Categoria já existe.");
            return false; // já existe
        }
        insereFim(nova);
        salvarCategoriasNoCSV();
        System.out.println("Categoria adicionada com sucesso!");
        return true;
    }

    public boolean editarCategoria(int id, String novoNome) {
        RegistroLocadora atual = buscarRegistro(String.valueOf(id));
        if (atual != null) {
            Categoria c = (Categoria) atual.getInfo();
            c.setNome(novoNome);
            salvarCategoriasNoCSV();
            System.out.println("Categoria editada com sucesso!");
            return true;
        }
        System.out.println("Categoria não encontrada.");
        return false;
    }

    public boolean excluirCategoria(int id) {
        if (temVeiculosAtrelados(id)) {
            System.out.println("Não é possível excluir a categoria. Existe um veículo atrelado.");
            return false;
        }

        RegistroLocadora atual = buscarRegistro(String.valueOf(id));
        if (atual == null) {
            System.out.println("Categoria não encontrada.");
            return false;
        }

        // Remover da lista
        if (atual == inicio && atual == fim) {
            inicio = null;
            fim = null;
        } else if (atual == inicio) {
            inicio = atual.getProx();
            inicio.setAnt(null);
        } else if (atual == fim) {
            fim = atual.getAnt();
            fim.setProx(null);
        } else {
            atual.getAnt().setProx(atual.getProx());
            atual.getProx().setAnt(atual.getAnt());
        }

        salvarCategoriasNoCSV();
        System.out.println("Categoria excluída com sucesso!");
        return true;
    }

    public void listarCategoriasFrente() {
        RegistroLocadora atual = inicio;
        while (atual != null) {
            System.out.println(atual.getInfo());
            atual = atual.getProx();
        }
    }

    public void listarCategoriasTras() {
        RegistroLocadora atual = fim;
        while (atual != null) {
            System.out.println(atual.getInfo());
            atual = atual.getAnt();
        }
    }

    public Categoria buscarCategoria(String id) {
        RegistroLocadora r = buscarRegistro(id);
        return (r != null) ? (Categoria) r.getInfo() : null;
    }

    private RegistroLocadora buscarRegistro(String id) {
        RegistroLocadora atual = inicio;
        while (atual != null) {
            Categoria cat = (Categoria) atual.getInfo();
            if (String.valueOf(cat.getIdentificador()).equals(id)) {
                return atual;
            }
            atual = atual.getProx();
        }
        return null;
    }

    private void insereFim(Categoria cat) {
        RegistroLocadora novo = new RegistroLocadora(cat);
        if (inicio == null) {
            inicio = novo;
            fim = novo;
        } else {
            fim.setProx(novo);
            novo.setAnt(fim);
            fim = novo;
        }
    }

    private boolean temVeiculosAtrelados(int idCategoria) {
        try (BufferedReader br = new BufferedReader(new FileReader("veiculos.csv"))) {
            String linha;
            br.readLine();
            while ((linha = br.readLine()) != null) {
                String[] partes = linha.split(";");
                int idCat = Integer.parseInt(partes[2]); // ajustar se necessário
                if (idCat == idCategoria) {
                    return true;
                }
            }
        } catch (IOException e) {
            System.out.println("Erro ao verificar veículos: " + e.getMessage());
        }
        return false;
    }


    public void mostrarMenu() {
        while (true) {
            System.out.println("\nMenu de Categorias:");
            System.out.println("1. Adicionar Categoria");
            System.out.println("2. Editar Categoria");
            System.out.println("3. Listar Categorias");
            System.out.println("4. Excluir Categoria");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opção: ");
            int opcao = scanner.nextInt();
            scanner.nextLine(); // Limpar o buffer

            switch (opcao) {
                case 1: adicionarCategoria(); break;
                case 2: editarCategoria(); break;
                case 3: listarCategorias(); break;
                case 4: excluirCategoria(); break;
                case 0: return;
                default: System.out.println("Opção inválida!");
            }
        }
    }

    private void adicionarCategoria() {
        System.out.print("Digite o nome da nova categoria: ");
        String nome = scanner.nextLine();
        System.out.print("Digite o identificador da nova categoria: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Limpar o buffer
        Categoria nova = new Categoria(nome, id);
        if (adicionarCategoria(nova)) {
            System.out.println("Categoria adicionada com sucesso!");
        } else {
            System.out.println("Erro ao adicionar categoria.");
        }
    }

    private void editarCategoria() {
        System.out.print("Digite o identificador da categoria a ser editada: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Digite o novo nome da categoria: ");
        String novoNome = scanner.nextLine();
        if (editarCategoria(id, novoNome)) {
            System.out.println("Categoria editada com sucesso!");
        } else {
            System.out.println("Erro ao editar categoria.");
        }
    }

    private void listarCategorias() {
        System.out.println("\nCategorias listadas do começo para o fim:");
        listarCategoriasFrente();
        System.out.println("\nCategorias listadas do fim para o começo:");
        listarCategoriasTras();
    }

    private void excluirCategoria() {
        System.out.print("Digite o identificador da categoria a ser excluída: ");
        int id = scanner.nextInt();
        if (excluirCategoria(id)) {
            System.out.println("Categoria excluída com sucesso!");
        } else {
            System.out.println("Erro ao excluir categoria.");
        }
    }
}



