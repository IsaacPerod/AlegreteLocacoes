package services;

import models.*;
import utils.ListaLocadora;
import utils.RegistroLocadora;

import java.text.ParseException;
import java.util.Scanner;

public class LocacaoService {
    private ListaLocadora locacoes;
    private ListaLocadora clientes;
    private ListaLocadora veiculos;
    private Scanner scanner = new Scanner(System.in);

    public LocacaoService(ListaLocadora locacoes, ListaLocadora clientes, ListaLocadora veiculos) {
        this.locacoes = locacoes;
        this.clientes = clientes;
        this.veiculos = veiculos;
    }

    public void gerenciarLocacoes() {

        while (true) {
            System.out.println("\n╔════════════════════════════════════════════╗");
            System.out.println("║             GERENCIAR LOCAÇÕES             ║");
            System.out.println("╠════╗═══════════════════════════════════════╣");
            System.out.println("║ 1  ║ Listar Veiculos Disponiveis           ║");
            System.out.println("║ 2  ║ Incluir Locação                       ║");
            System.out.println("║ 3  ║ Excluir Locação                       ║");
            System.out.println("║ 4  ║ Listar Locações Ativas do começo      ║");
            System.out.println("║ 5  ║ Listar Locações Ativas do fim         ║");
            System.out.println("╠════╝═══════════════════════════════════════╣");
            System.out.println("║ 0. Voltar                                  ║");
            System.out.println("╚════════════════════════════════════════════╝");
            System.out.print("Escolha: ");
            
            int op = scanner.nextInt();
            scanner.nextLine();

            switch (op) {
                case 1: listarVeiculosNaoLocados(); break;
                case 2: incluirLocacao(); break;
                case 3: excluirLocacao(); break;
                case 4: locacoes.imprimeDoComeco(); break;
                case 5: locacoes.imprimeDoFim(); break;
                case 0: return;
                default: System.out.println("Opcao invalida!");
            }
        }
    }

    public void listarVeiculosNaoLocados() {
    RegistroLocadora atualVeiculo = veiculos.getInicio();

    System.out.println("Veiculos Disponiveis:");
    while (atualVeiculo != null) {
        Veiculo veiculo = (Veiculo) atualVeiculo.getInfo();
        if (veiculo != null) {
            Locacao locacao = (Locacao) locacoes.busca(veiculo.getPlaca());
            if (locacao == null) {
                System.out.println(veiculo);
            }
        }
        atualVeiculo = atualVeiculo.getProx();
    }
}

    private void incluirLocacao() {
        System.out.print("CPF do cliente: ");
        String cpf = scanner.nextLine();
        Cliente cliente = (Cliente) clientes.busca(cpf);

        if (cliente == null) {
            System.out.println("Cliente nao encontrado!");
            return;
        }

        System.out.print("CNH do cliente: ");
        String cnh = scanner.nextLine();

        if (!cliente.getCnh().equals(cnh)) {
            System.out.println("CNH nao corresponde ao cliente informado!");
            return;
        }

        System.out.print("Placa do veiculo: ");
        String placa = scanner.nextLine();
        Veiculo veiculo = (Veiculo) veiculos.busca(placa);

        if (veiculo == null) {
            System.out.println("Veiculo não encontrado!");
            return;
        }

        Locacao locacaoExistente = (Locacao) locacoes.busca(placa);
        if (locacaoExistente != null) {
            System.out.println("Veiculo ja esta locado!");
            return;
        }

        System.out.print("Data de retirada (dd/MM/yyyy): ");
        String dataRetirada = scanner.nextLine();
        System.out.print("Data de devolucao (dd/MM/yyyy): ");
        String dataDevolucao = scanner.nextLine();
        System.out.print("Valor da diaria (R$): ");
        double valorDiaria = scanner.nextDouble();
        scanner.nextLine();

        try {
            Locacao novaLocacao = new Locacao(cnh, placa, dataRetirada, dataDevolucao, valorDiaria);
            locacoes.insereFim(novaLocacao);
            System.out.println("Locacao registrada com sucesso!");
        } catch (ParseException e) {
            System.out.println("Erro no formato das datas. Use dd/MM/yyyy.");
        }
    }

    private void excluirLocacao() {
        System.out.print("Placa do veiculo: ");
        String placa = scanner.nextLine();
        if (locacoes.remove(placa)) {
            System.out.println("Locacao excluida com sucesso!");
        } else {
            System.out.println("Locacao nao encontrada!");
        }
    }
}