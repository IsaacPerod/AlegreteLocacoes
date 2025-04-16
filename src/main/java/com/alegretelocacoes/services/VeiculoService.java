package com.alegretelocacoes.services;

import com.alegretelocacoes.models.Categoria;
import com.alegretelocacoes.models.Veiculo;
import com.alegretelocacoes.utils.ListaLocadora;

import java.util.Scanner;

public class VeiculoService {
    public void criarVeiculo(ListaLocadora veiculos, ListaLocadora categorias) {
        Scanner scanner = new Scanner(System.in);
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

        String categorias_veiculos = "\n1 - 1010 - esportivo\n" +
                "2 - 1011 - sedan comptacto\n" +
                "3 - 1012 - sedan medio\n" +
                "4 - 1013 - SUV compacto\n" +
                "5 - 1014 - SUV\n" +
                "6 - 1015 - caminhonete\n" +
                "7 - 1016 - hatch";
        System.out.println("Categorias: " + categorias_veiculos);

        int escolhaCategoria = scanner.nextInt();
        //scanner.close();

        Categoria categoria;
        switch (escolhaCategoria) {
            case 1:
                categoria = new Categoria("esportivo", 1010);
                break;
            case 2:
                categoria = new Categoria("sedan compacto", 1011);
                break;
            case 3:
                categoria = new Categoria("sedan medio", 1012);
                break;
            case 4:
                categoria = new Categoria("SUV compacto", 1013);
                break;
            case 5:
                categoria = new Categoria("SUV", 1014);
                break;
            case 6:
                categoria = new Categoria("caminhonete", 1015);
                break;
            case 7:
                categoria = new Categoria("hatch", 1016);
                break;
            default:
                throw new IllegalArgumentException("Código inválido: " + escolhaCategoria);
        }
        veiculos.insereFim(new Veiculo(placa, modelo, marca, ano, potencia, lugares, categoria));
        System.out.println("Veículo adicionado!");
    }

    public void listarVeiculos(ListaLocadora veiculos) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("\n╔════════════════════════════════════════╗");
        System.out.println("║         LISTAGEM DE Veiculos           ║");
        System.out.println("╠════╗═══════════════════════════════════╣");
        System.out.println("║ 1  ║ Listar do início ao fim           ║");
        System.out.println("║ 2  ║ Listar do fim ao início           ║");
        System.out.println("╠════╝═══════════════════════════════════╣");
        System.out.println("║ 0. Voltar                              ║");
        System.out.println("╚════════════════════════════════════════╝");
        System.out.print("Escolha uma opção: ");
        
        int opcao = scanner.nextInt();
        
        System.out.println("\nLista de Veículos:");
        
        switch (opcao) {
            case 1:
                veiculos.imprimeDoComeco();
                break;
            case 2:
                veiculos.imprimeDoFim();
                break;
            default:
                System.out.println("Opção inválida.");
        }
    }

    public void atualizarVeiculos(ListaLocadora veiculos) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Digite a placa do veículo a ser atualizado: ");
        String placa = scanner.nextLine();
        //scanner.close();

        Veiculo veiculo = (Veiculo) veiculos.busca(placa);
        if (veiculo != null) {

            System.out.println("\nO que deseja atualizar?");
            System.out.println("1. Modelo");
            System.out.println("2. Marca");
            System.out.println("3. Ano");
            System.out.println("4. Potência");
            System.out.println("5. Número de lugares");
            System.out.println("6. Categoria");

            int escolha = scanner.nextInt();

            switch (escolha) {
                case 1:
                    System.out.print("Novo modelo: ");
                    String novoModelo = scanner.nextLine();
                    veiculo.setModelo(novoModelo);
                    break;
                case 2:
                    System.out.print("Nova marca: ");
                    String novaMarca = scanner.nextLine();
                    veiculo.setMarca(novaMarca);
                    break;
                case 3:
                    System.out.print("Novo ano: ");
                    int novoAno = scanner.nextInt();
                    veiculo.setAno(novoAno);
                    break;
                case 4:
                    System.out.print("Nova potência: ");
                    int novaPotencia = scanner.nextInt();
                    veiculo.setPotencia(novaPotencia);
                    break;
                case 5:
                    System.out.print("Novo número de lugares: ");
                    int novosLugares = scanner.nextInt();
                    veiculo.setLugares(novosLugares);
                    break;
                case 6:
                    System.out.print("Nova categoria: ");
                    String categorias_veiculos = "\n1 - 1010 - esportivo\n" +
                            "2 - 1011 - sedan comptacto\n" +
                            "3 - 1012 - sedan medio\n" +
                            "4 - 1013 - SUV compacto\n" +
                            "5 - 1014 - SUV\n" +
                            "6 - 1015 - caminhonete\n" +
                            "7 - 1016 - hatch";
                    System.out.println("Categorias: " + categorias_veiculos);

                    int escolhaCategoria = scanner.nextInt();
                    scanner.close();

                    Categoria categoria;
                    switch (escolhaCategoria) {
                        case 1:
                            categoria = new Categoria("esportivo", 1010);
                            break;
                        case 2:
                            categoria = new Categoria("sedan compacto", 1011);
                            break;
                        case 3:
                            categoria = new Categoria("sedan medio", 1012);
                            break;
                        case 4:
                            categoria = new Categoria("SUV compacto", 1013);
                            break;
                        case 5:
                            categoria = new Categoria("SUV", 1014);
                            break;
                        case 6:
                            categoria = new Categoria("caminhonete", 1015);
                            break;
                        case 7:
                            categoria = new Categoria("hatch", 1016);
                            break;
                        default:
                            throw new IllegalArgumentException("Código inválido: " + escolhaCategoria);
                    }
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
            System.out.println("Veículo atualizado com sucesso!");
        } else {
            System.out.println("Veículo não encontrado!");
        }
    }

    public void removerVeiculo(ListaLocadora veiculos) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Digite a placa do veículo a ser removido: ");
        String placa = scanner.nextLine();

        Veiculo veiculo = (Veiculo) veiculos.busca(placa);
        if (veiculo != null) {
            veiculos.remove(placa);
            System.out.println("Veículo removido com sucesso!");
        } else {
            System.out.println("Veículo não encontrado!");
        }
    }
}

