package com.tppe.app;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Main {

    private static Scanner scanner = new Scanner(System.in);
    private static SistemaVarejo sistema = new SistemaVarejo();

    public static void main(String[] args) {
        int opcao;
        do {
            exibirMenu();
            opcao = scanner.nextInt();
            scanner.nextLine(); // Consumir a nova linha

            switch (opcao) {
                case 1:
                    cadastrarCliente();
                    break;
                case 2:
                    cadastrarProduto();
                    break;
                case 3:
                    realizarVenda();
                    break;
                case 4:
                    exibirVendas();
                    break;
                case 5:
                    calcularSaldoCashback();
                    break;
                case 6:
                    listarClientes();
                    break;
                case 0:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        } while (opcao != 0);

        scanner.close();
    }

    private static void exibirMenu() {
        System.out.println("\n---- Sistema de Varejo ----");
        System.out.println("1. Cadastrar Cliente");
        System.out.println("2. Cadastrar Produto");
        System.out.println("3. Realizar Venda");
        System.out.println("4. Exibir Vendas");
        System.out.println("5. Calcular Saldo de Cashback do Cliente");
        System.out.println("6. Listar Clientes");
        System.out.println("0. Sair");
        System.out.print("Escolha uma opção: ");
    }

    private static void cadastrarCliente() {
        System.out.print("Nome do cliente: ");
        String nome = scanner.nextLine();
        System.out.print("Tipo de cliente (padrão, especial, prime): ");
        String tipo = scanner.nextLine();
        System.out.print("Estado do cliente: ");
        String estado = scanner.nextLine();
        System.out.print("Cliente mora na capital? (true/false): ");
        boolean capital = scanner.nextBoolean();
        scanner.nextLine(); // Consumir a nova linha

        Endereco endereco = new Endereco(estado, capital);
        Cliente cliente = new Cliente(nome, tipo, endereco);
        sistema.cadastrarCliente(cliente);
        System.out.println("Cliente cadastrado com sucesso!");
    }

    private static void cadastrarProduto() {
        System.out.print("Código do produto: ");
        String codigo = scanner.nextLine();
        System.out.print("Descrição do produto: ");
        String descricao = scanner.nextLine();
        System.out.print("Valor de venda do produto: ");
        double valorVenda = scanner.nextDouble();
        scanner.nextLine(); // Consumir a nova linha
        System.out.print("Unidade do produto: ");
        String unidade = scanner.nextLine();

        Produto produto = new Produto(codigo, descricao, valorVenda, unidade);
        sistema.cadastrarProduto(produto);
        System.out.println("Produto cadastrado com sucesso!");
    }

    private static void realizarVenda() {
        System.out.print("Nome do cliente: ");
        String nomeCliente = scanner.nextLine();
        Cliente cliente = sistema.getClientes().stream()
                .filter(c -> c.getNome().equals(nomeCliente))
                .findFirst()
                .orElse(null);

        if (cliente == null) {
            System.out.println("Cliente não encontrado!");
            return;
        }

        List<Produto> produtosVenda = new ArrayList<>();
        while (true) {
            System.out.print("Código do produto (ou 'fim' para terminar): ");
            String codigoProduto = scanner.nextLine();
            if (codigoProduto.equals("fim")) {
                break;
            }
            Produto produto = sistema.getProdutos().stream()
                    .filter(p -> p.getCodigo().equals(codigoProduto))
                    .findFirst()
                    .orElse(null);
            if (produto != null) {
                produtosVenda.add(produto);
            } else {
                System.out.println("Produto não encontrado!");
            }
        }

        if (produtosVenda.isEmpty()) {
            System.out.println("Nenhum produto adicionado à venda!");
            return;
        }

        System.out.print("Método de pagamento: ");
        String metodoPagamento = scanner.nextLine();

        Venda venda = new Venda(new Date(), cliente, produtosVenda, metodoPagamento);
        sistema.realizarVenda(venda);
        System.out.println("Venda realizada com sucesso!");
    }

    private static void exibirVendas() {
        List<Venda> vendas = sistema.getVendas();
        if (vendas.isEmpty()) {
            System.out.println("Nenhuma venda registrada.");
        } else {
            for (Venda venda : vendas) {
                System.out.println("\nData: " + venda.getData());
                System.out.println("Cliente: " + venda.getCliente().getNome());
                System.out.println("Produtos:");
                for (Produto produto : venda.getProdutos()) {
                    System.out.println("- " + produto.getDescricao() + " (R$ " + produto.getValorVenda() + ")");
                }
                System.out.println("Valor Total: R$ " + venda.getValorTotal());
            }
        }
    }

    private static void calcularSaldoCashback() {
        System.out.print("Nome do cliente: ");
        String nomeCliente = scanner.nextLine();
        Cliente cliente = sistema.getClientes().stream()
                .filter(c -> c.getNome().equals(nomeCliente))
                .findFirst()
                .orElse(null);

        if (cliente == null) {
            System.out.println("Cliente não encontrado!");
        } else {
            double saldoCashback = sistema.calcularSaldoCashback(cliente);
            System.out.println("Saldo de Cashback do cliente " + cliente.getNome() + ": R$ " + saldoCashback);
        }
    }

    private static void listarClientes() {
        List<Cliente> clientes = sistema.getClientes();
        if (clientes.isEmpty()) {
            System.out.println("Nenhum cliente cadastrado.");
        } else {
            System.out.println("\nLista de Clientes:");
            for (Cliente cliente : clientes) {
                System.out.println("Nome: " + cliente.getNome() + ", Tipo: " + cliente.getTipo() + ", Estado: " + cliente.getEndereco().getEstado() + ", Capital: " + cliente.getEndereco().isCapital());
            }
        }
    }
}
