package com.alegretelocacoes.services;

import java.util.Scanner;

import com.alegretelocacoes.models.*;
import com.alegretelocacoes.utils.ListaLocadora;
import com.alegretelocacoes.utils.RegistroLocadora;


public class ClienteService {
    private ListaLocadora clientes;
    private ListaLocadora locacoes;
    private Scanner scanner = new Scanner(System.in);

    public ClienteService(ListaLocadora clientes, ListaLocadora locacoes) {
        this.clientes = clientes;
        this.locacoes = locacoes;
    }

    public void gerenciarClientes() {
        System.out.println("1. Incluir Cliente");
        System.out.println("2. Imprimir Clientes do comeco");
        System.out.println("3. Imprimir Clientes do fim");
        System.out.println("4. Editar Cliente");
        System.out.println("5. Excluir Cliente");
        System.out.print("Escolha: ");
        int op = scanner.nextInt();
        scanner.nextLine();

        switch (op) {
            case 1: incluirCliente(); break;
            case 2: clientes.imprimeDoComeco(); break;
            case 3: clientes.imprimeDoFim(); break;
            case 4: editarCliente(); break;
            case 5: excluirCliente(); break;
            default: System.out.println("Opcao invalida!");
        }
    }

    private void incluirCliente() {
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
    }

    private void editarCliente() {
        System.out.print("CPF do cliente a ser editado: ");
        String cpf = scanner.nextLine();
        Cliente cliente = (Cliente) clientes.busca(cpf);

        if (cliente == null) {
            System.out.println("Cliente não encontrado!");
            return;
        }

        System.out.println("Editando cliente: " + cliente.getNome());
        System.out.print("Novo nome (deixe vazio para manter o atual): ");
        String novoNome = scanner.nextLine();
        if (!novoNome.isEmpty()) {
            cliente.setNome(novoNome);
        }

        System.out.print("Nova CNH (deixe vazio para manter a atual): ");
        String novaCnh = scanner.nextLine();
        if (!novaCnh.isEmpty()) {
            cliente.setCnh(novaCnh);
        }

        System.out.print("Novo telefone (deixe vazio para manter o atual): ");
        String novoTelefone = scanner.nextLine();
        if (!novoTelefone.isEmpty()) {
            cliente.setTelefone(novoTelefone);
        }

        System.out.println("Cliente atualizado com sucesso!");
    }

    private void excluirCliente() {
        System.out.print("CPF do cliente a ser excluido: ");
        String cpf = scanner.nextLine();
        Cliente cliente = (Cliente) clientes.busca(cpf);

        if (cliente == null) {
            System.out.println("Cliente nao encontrado!");
            return;
        }

        // Verificar se o cliente está atrelado a uma locação
        RegistroLocadora atual = locacoes.getInicio(); // Supondo que exista um método para obter o início da lista
        while (atual != null) {
            Locacao locacao = (Locacao) atual.getInfo();
            if (locacao != null && locacao.getCnhCliente().equals(cliente.getCnh())) {
                System.out.println("Nao eh possivel excluir o cliente. Ele esta atrelado a uma locação.");
                return;
            }
            atual = atual.getProx();
        }

        // Remover cliente
        if (clientes.remove(cpf)) {
            System.out.println("Cliente excluido com sucesso!");
        } else {
            System.out.println("Erro ao excluir o cliente.");
        }
    }
}
