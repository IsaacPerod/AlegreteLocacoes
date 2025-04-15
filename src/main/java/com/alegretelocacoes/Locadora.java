package com.alegretelocacoes;

import java.util.Scanner;
import com.alegretelocacoes.ListaLocadora;
import com.alegretelocacoes.models.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Locadora {
    private static ListaLocadora clientes = new ListaLocadora();
    private static ListaLocadora locacoes = new ListaLocadora();
    private static ClienteService clienteService = new ClienteService(clientes, locacoes);
    private static ListaLocadora veiculos = new ListaLocadora();
    private static ListaLocadora categorias = new ListaLocadora();
    private static LocacaoService locacaoService = new LocacaoService(locacoes, clientes, veiculos);
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        // Inicializar listas a partir dos arquivos CSV
        try {
            lerCategoriasCSV("Categorias.csv");
            lerVeiculosCSV("Veiculos.csv");
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
                case 2: gerenciarVeiculos(); break;
                case 3: gerenciarCategorias(); break;
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
        System.out.println("\n=== Locadora Alegrete ===");
        System.out.println("1. Gerenciar Clientes");
        System.out.println("2. Gerenciar Veiculos");
        System.out.println("3. Gerenciar Categorias");
        System.out.println("4. Gerenciar Locacoes");
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
        //    public Veiculo(String placa, String modelo, String marca,
        //    int ano, int potencia, int lugares, Categoria categoria)
        System.out.println("1. Adicionar Veículo");
        System.out.println("2. Excluir Veículo");
        System.out.println("3. Listar Veículos");
        System.out.println("4. Editar Veículo");
        System.out.println("0. Voltar");
        int op = scanner.nextInt();
        scanner.nextLine();
        if (op == 1) {
            // Seria interessante verificar se veículo já existe
            System.out.print("Placa: ");
            String placa = scanner.nextLine();

            System.out.print("Modelo: ");
            String modelo = scanner.nextLine();

            System.out.print("Marca: ");
            String marca = scanner.nextLine();

            System.out.print("Ano: ");
            int ano = scanner.nextInt();

            System.out.print("Potência: ");
            int potencia = scanner.nextInt();

            System.out.print("Número de Lugares: ");
            int lugares = scanner.nextInt();

            String categorias =
                    "\n1 - 1010;esportivo\n" +
                    "2 - 1011;sedan comptacto\n" +
                    "3 - 1012;sedan medio\n" +
                    "4 - 1013;SUV compacto\n" +
                    "5 - 1014;SUV\n" +
                    "6 - 1015;caminhonete\n" +
                    "7 - 1016;hatch";
            System.out.println("Categorias: " + categorias);

            int escolhaCategoria = scanner.nextInt();

            Categoria categoria;
            switch (escolhaCategoria) {
                case 1:
                    categoria = new Categoria("esportivo", 1010);
                    break;
                case 2:
                    categoria = new  Categoria("sedan compacto", 1011);
                    break;
                case 3:
                    categoria = new  Categoria("sedan medio", 1012);
                    break;
                case 4:
                    categoria = new  Categoria("SUV compacto", 1013);
                    break;
                case 5:
                    categoria = new  Categoria("SUV", 1014);
                    break;
                case 6:
                    categoria = new  Categoria("caminhonete", 1015);
                    break;
                case 7:
                    categoria = new  Categoria("hatch", 1016);
                    break;
                default:
                    throw new IllegalArgumentException("Código inválido: " + escolhaCategoria);
            }
            veiculos.insereFim(new Veiculo(placa, modelo, marca, ano, potencia, lugares, categoria));
        }

        if (op == 2) {
            System.out.println("Implementar");
            //veiculos.remove();
        }
        if (op == 3) {
            veiculos.imprimeTras();
        }
    }

    private static void gerenciarCategorias() {
        System.out.println("Função a ser implementada.");
    }

    
}