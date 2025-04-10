import java.util.Scanner;

import models.Categoria;
import models.Cliente;
import models.Locacao;
import models.Veiculo;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Locadora {
    private static ListaLocadora clientes = new ListaLocadora();
    private static ListaLocadora veiculos = new ListaLocadora();
    private static ListaLocadora categorias = new ListaLocadora();
    private static ListaLocadora locacoes = new ListaLocadora();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        // Inicializar listas a partir dos arquivos CSV
        try {
            lerCategoriasCSV("Categorias.csv");
            lerVeiculosCSV("Veiculos.csv");
        } catch (IOException e) {
            System.out.println("Erro ao ler os arquivos CSV: " + e.getMessage());
        }

        // Dados de exemplo adicionais (opcional)
        clientes.insereFim(new Cliente("João da Silva", "5482156", "(55)3325-2525", "02408925469"));

        while (true) {
            exibirMenu();
            int opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1: gerenciarClientes(); break;
                case 2: gerenciarVeiculos(); break;
                case 3: gerenciarCategorias(); break;
                case 4: gerenciarLocacoes(); break;
                case 0: System.out.println("Saindo..."); return;
                default: System.out.println("Opção inválida!");
            }
        }
    }

    // Método para ler Categorias.csv
    private static void lerCategoriasCSV(String caminhoArquivo) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(caminhoArquivo))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                String[] dados = linha.split(",");
                if (dados.length == 2) {
                    String nome = dados[0].trim();
                    int identificador = Integer.parseInt(dados[1].trim());
                    categorias.insereFim(new Categoria(nome, identificador));
                }
            }
        }
    }

    // Método para ler Veiculos.csv
    private static void lerVeiculosCSV(String caminhoArquivo) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(caminhoArquivo))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                String[] dados = linha.split(",");
                if (dados.length == 7) {
                    String placa = dados[0].trim();
                    String modelo = dados[1].trim();
                    String marca = dados[2].trim();
                    int ano = Integer.parseInt(dados[3].trim());
                    int lugares = Integer.parseInt(dados[4].trim());
                    int potencia = Integer.parseInt(dados[5].trim());
                    int identificadorCategoria = Integer.parseInt(dados[6].trim());

                    // Buscar a categoria correspondente
                    Categoria categoria = (Categoria) categorias.busca(String.valueOf(identificadorCategoria));
                    if (categoria != null) {
                        veiculos.insereFim(new Veiculo(placa, modelo, marca, ano, lugares, potencia, categoria));
                    } else {
                        System.out.println("Categoria " + identificadorCategoria + " não encontrada para o veículo " + placa);
                    }
                }
            }
        }
    }

    private static void exibirMenu() {
        System.out.println("\n=== Locadora Alegrete ===");
        System.out.println("1. Gerenciar Clientes");
        System.out.println("2. Gerenciar Veículos");
        System.out.println("3. Gerenciar Categorias");
        System.out.println("4. Gerenciar Locações");
        System.out.println("0. Sair");
        System.out.print("Escolha uma opção: ");
    }

    private static void gerenciarClientes() {
        System.out.println("1. Incluir Cliente");
        System.out.println("2. Imprimir Clientes (frente)");
        System.out.println("3. Imprimir Clientes (trás)");
        System.out.print("Escolha: ");
        int op = scanner.nextInt();
        scanner.nextLine();

        if (op == 1) {
            System.out.print("Nome: ");
            String nome = scanner.nextLine();
            System.out.print("CNH: ");
            String cnh = scanner.nextLine();
            System.out.print("Telefone: ");
            String telefone = scanner.nextLine();
            System.out.print("CPF: ");
            String cpf = scanner.nextLine();
            clientes.insereFim(new Cliente(nome, cnh, telefone, cpf));
            System.out.println("Cliente adicionado!");
        } else if (op == 2) {
            clientes.imprimeFrente();
        } else if (op == 3) {
            clientes.imprimeTras();
        }
    }

    private static void gerenciarVeiculos() {
        System.out.println("Função a ser implementada.");
    }

    private static void gerenciarCategorias() {
        System.out.println("Função a ser implementada.");
    }

    private static void gerenciarLocacoes() {
        System.out.println("Função a ser implementada.");
    }
}