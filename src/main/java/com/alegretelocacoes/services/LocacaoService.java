package services;

import models.*;
import utils.*;

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
        System.out.println("1. Incluir Locacao");
        System.out.println("2. Excluir Locacao");
        System.out.println("3. Listar Locacoes Ativas do comeco");
        System.out.println("4. Listar Locacoes Ativas do fim");
        System.out.print("Escolha: ");
        int op = scanner.nextInt();
        scanner.nextLine();

        switch (op) {
            case 1: incluirLocacao(); break;
            case 2: excluirLocacao(); break;
            case 3: locacoes.imprimeDoComeco(); break;
            case 4: locacoes.imprimeDoFim(); break;
            default: System.out.println("Opcao invalida!");
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