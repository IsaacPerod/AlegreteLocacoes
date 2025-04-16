package services;

import models.Categoria;
import models.Locacao;
import models.Veiculo;
import utils.ListaLocadora;
import utils.RegistroLocadora;

import java.util.Scanner;

public class VeiculoService {
    private ListaLocadora veiculos;
    private ListaLocadora categorias;
    private ListaLocadora locacoes;

    public VeiculoService(ListaLocadora veiculos, ListaLocadora categorias, ListaLocadora locacoes) {
        this.locacoes = locacoes;
        this.veiculos = veiculos;
        this.categorias = categorias;
    }

    public void gerenciarVeiculos() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n╔══════════════════════════════════════╗");
            System.out.println("║          GERENCIAR VEÍCULOS          ║");
            System.out.println("╠════╗═════════════════════════════════╣");
            System.out.println("║ 1  ║ Criar Veículo                   ║");
            System.out.println("║ 2  ║ Listar Veículo - Começo ao fim  ║");
            System.out.println("║ 3  ║ Listar Veículo - Fim o começo   ║");
            System.out.println("║ 4  ║ Atualizar Veículos              ║");
            System.out.println("║ 5  ║ Remover Veículo                 ║");
            System.out.println("╠════╝═════════════════════════════════╣");
            System.out.println("║ 0. Voltar                            ║");
            System.out.println("╚══════════════════════════════════════╝");
            System.out.print("Escolha: ");

            int op = scanner.nextInt();
            scanner.nextLine();

        switch (op) {
            case 1: criarVeiculo();  gerenciarVeiculos();break;
            case 2: imprimeDoComeco(); gerenciarVeiculos();break;
            case 3: imprimeDoFim(); gerenciarVeiculos();break;
            case 4: atualizarVeiculos(); gerenciarVeiculos();break;
            case 5: removerVeiculo(); gerenciarVeiculos();break;
            case 0: { return; }
            default: System.out.println("Opção inválida!");
        }
    }

    public void imprimeDoComeco() {
        veiculos.imprimeDoComeco();
    }

    public void imprimeDoFim() {
        veiculos.imprimeDoFim();
    }

    public void criarVeiculo() {
        Scanner scanner = new Scanner(System.in);
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

    public void atualizarVeiculos() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Digite a placa do veículo a ser atualizado: ");
        String placa = scanner.nextLine();

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

    public void removerVeiculo() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Digite a placa do veículo a ser removido: ");
        String placa = scanner.nextLine();
        Veiculo veiculo = (Veiculo) veiculos.busca(placa);

        if (veiculo == null) {
            System.out.println("Veículo não encontrado!");
            return;
        }

        RegistroLocadora atual = locacoes.getInicio();
        while (atual != null) {
            Locacao locacao = (Locacao) atual.getInfo();
            if (locacao != null && locacao.getPlacaVeiculo().equals(veiculo.getPlaca())) {
                System.out.println("Nao eh possivel excluir o veiculo. Ele esta atrelado a uma locação.");
                return;
            }
            atual = atual.getProx();
        }

        if (veiculos.remove(placa)) {
            System.out.println("Veiculo excluido com sucesso!");
        } else {
            System.out.println("Erro ao excluir o Veiculo.");
        }
    }
}

