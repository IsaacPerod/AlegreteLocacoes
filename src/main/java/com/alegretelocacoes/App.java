package com.alegretelocacoes;

import java.util.Scanner;

import com.alegretelocacoes.models.*;
import com.alegretelocacoes.services.ClienteService;
import com.alegretelocacoes.services.LocacaoService;
import com.alegretelocacoes.services.VeiculoService;
import com.alegretelocacoes.services.CategoriaService;
import com.alegretelocacoes.utils.ListaLocadora;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class App {
    private static ListaLocadora clientes = new ListaLocadora();
    private static ListaLocadora locacoes = new ListaLocadora();
    private static ListaLocadora veiculos = new ListaLocadora();
    private static ListaLocadora categorias = new ListaLocadora();
    private static ClienteService clienteService = new ClienteService(clientes, locacoes);
    private static LocacaoService locacaoService = new LocacaoService(locacoes, clientes, veiculos);
    private static VeiculoService veiculoService = new VeiculoService(veiculos, categorias);
    private static CategoriaService categoriaService;
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        // Inicializar listas a partir dos arquivos CSV
        try {
            lerCategoriasCSV("src\\main\\java\\com\\alegretelocacoes\\data\\Categorias.csv");
            categoriaService = new CategoriaService(categorias);
            lerVeiculosCSV("src\\main\\java\\com\\alegretelocacoes\\data\\Veiculos.csv");
        } catch (IOException e) {
            System.out.println("Erro ao ler os arquivos CSV: " + e.getMessage());
        }

        // Adicionar clientes de exemplo
        clientes.insereFim(new Cliente("Joao da Silva", "5482156", "(55)3325-2525", "02408925469"));
        clientes.insereFim(new Cliente("Maria Oliveira", "5482157", "(55)3325-2526", "02408925470"));
        clientes.insereFim(new Cliente("Carlos Pereira", "5482158", "(55)3325-2527", "02408925471"));


        while (true) {
            exibirMenu();
            int opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1: clienteService.gerenciarClientes(); break;
                case 2: veiculoService.gerenciarVeiculos(); break;
                case 3: categoriaService.mostrarMenu(); break;
                case 4: locacaoService.gerenciarLocacoes(); break;
                case 0: System.out.println("Saindo..."); return;
                default: System.out.println("Opcao invalida!");
            }
        }
    }

    // Método para ler Categorias.csv
    private static void lerCategoriasCSV(String caminhoArquivo) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(caminhoArquivo))) {
            String linha = br.readLine(); // Ignorar o cabeçalho
            while ((linha = br.readLine()) != null) {
                String[] dados = linha.split(";");
                if (dados.length == 2) {
                    int identificador = Integer.parseInt(dados[0].trim());
                    String nome = dados[1].trim();
                    categorias.insereFim(new Categoria(nome, identificador));
                }
            }
        }
    }

    // Método para ler Veiculos.csv
    private static void lerVeiculosCSV(String caminhoArquivo) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(caminhoArquivo))) {
            String linha = br.readLine(); // Ignorar o cabeçalho
            while ((linha = br.readLine()) != null) {
                String[] dados = linha.split(";");
                if (dados.length == 7) {
                    String placa = dados[0].trim();
                    String modelo = dados[1].trim();
                    String marca = dados[2].trim();
                    int ano = Integer.parseInt(dados[3].trim());
                    int potencia = Integer.parseInt(dados[4].trim());
                    int lugares = Integer.parseInt(dados[5].trim());
                    int identificadorCategoria = Integer.parseInt(dados[6].trim());

                    // Buscar a categoria correspondente
                    Categoria categoria = (Categoria) categorias.busca(String.valueOf(identificadorCategoria));
                    if (categoria != null) {
                        veiculos.insereFim(new Veiculo(placa, modelo, marca, ano, lugares, potencia, categoria));
                    } else {
                        System.out.println("Categoria " + identificadorCategoria + " nao encontrada para o veiculo " + placa);
                    }
                }
            }
        }
    }

    private static void exibirMenu() {
        System.out.println("\n╔══════════════════════════╗");
        System.out.println("║     LOCADORA ALEGRETE    ║");
        System.out.println("╠═══╗══════════════════════╣");
        System.out.println("║ 1 ║ Gerenciar Clientes   ║");
        System.out.println("║ 2 ║ Gerenciar Veículos   ║");
        System.out.println("║ 3 ║ Gerenciar Categorias ║");
        System.out.println("║ 4 ║ Gerenciar Locações   ║");
        System.out.println("╠═══╝══════════════════════╣");
        System.out.println("║ 0  Sair                  ║");
        System.out.println("╚══════════════════════════╝");
        System.out.print("Escolha uma opção: ");
    }
}