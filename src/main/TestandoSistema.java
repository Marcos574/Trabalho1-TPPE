package main;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TestandoSistema {

    public static void main(String[] args) {
        // Criando o sistema de varejo
        SistemaVarejo sistemaVarejo = new SistemaVarejo();

        // Criando alguns produtos
        Produto produto1 = new Produto("001", "Camisa", 50.0, "un");
        Produto produto2 = new Produto("002", "Calça", 100.0, "un");

        // Cadastrando produtos no sistema
        sistemaVarejo.cadastrarProduto(produto1);
        sistemaVarejo.cadastrarProduto(produto2);

        // Criando clientes
        Endereco enderecoCliente1 = new Endereco("SP", true); // Cliente em SP capital
        Cliente cliente1 = new Cliente("João", "padrão", enderecoCliente1);

        Endereco enderecoCliente2 = new Endereco("DF", false); // Cliente no DF não capital
        Cliente cliente2 = new Cliente("Maria", "especial", enderecoCliente2);

        Endereco enderecoCliente3 = new Endereco("RJ", true); // Cliente no RJ capital
        Cliente cliente3 = new Cliente("José", "prime", enderecoCliente3);

        // Cadastrando clientes no sistema
        sistemaVarejo.cadastrarCliente(cliente1);
        sistemaVarejo.cadastrarCliente(cliente2);
        sistemaVarejo.cadastrarCliente(cliente3);

        // Criando vendas para diferentes tipos de clientes
        List<Produto> produtosVenda1 = new ArrayList<>();
        produtosVenda1.add(produto1);

        List<Produto> produtosVenda2 = new ArrayList<>();
        produtosVenda2.add(produto2);

        List<Produto> produtosVenda3 = new ArrayList<>();
        produtosVenda3.add(produto1);
        produtosVenda3.add(produto2);

        // Venda para cliente padrão
        Date dataVenda1 = new Date();
        Venda venda1 = new Venda(dataVenda1, cliente1, produtosVenda1, "123456789012");
        sistemaVarejo.realizarVenda(venda1);

        // Venda para cliente especial
        Date dataVenda2 = new Date(dataVenda1.getTime() - (2 * 24 * 60 * 60 * 1000)); // Data há 2 dias atrás
        Venda venda2 = new Venda(dataVenda2, cliente2, produtosVenda2, "429613987654");
        sistemaVarejo.realizarVenda(venda2);

        // Venda para cliente prime
        Date dataVenda3 = new Date(dataVenda1.getTime() - (5 * 24 * 60 * 60 * 1000)); // Data há 5 dias atrás
        Venda venda3 = new Venda(dataVenda3, cliente3, produtosVenda3, "429613123456");
        sistemaVarejo.realizarVenda(venda3);

        // Utilizando cashback em uma venda para cliente prime
        double cashbackUtilizado = 20.0;
        cliente3.utilizarCashback(cashbackUtilizado);

        // Mostrando informações após as vendas
        System.out.println("Informações após as vendas:");
        System.out.println("--------------------");

        // Exibindo informações dos clientes
        System.out.println("Clientes cadastrados:");
        List<Cliente> clientes = sistemaVarejo.getClientes();
        for (Cliente cliente : clientes) {
            System.out.println("Nome: " + cliente.getNome());
            System.out.println("Tipo: " + cliente.getTipo());
            System.out.println("Saldo de Cashback: R$ " + sistemaVarejo.calcularSaldoCashback(cliente));
            System.out.println("--------------------");
        }

        // Exibindo vendas registradas no sistema
        List<Venda> vendasRegistradas = sistemaVarejo.getVendas();
        System.out.println("\nVendas registradas:");
        for (Venda venda : vendasRegistradas) {
            System.out.println("Data da venda: " + venda.getData());
            System.out.println("Cliente: " + venda.getCliente().getNome());
            System.out.println("Produtos:");
            for (Produto produto : venda.getProdutos()) {
                System.out.println(" - " + produto.getDescricao() + ": R$ " + produto.getValorVenda());
            }
            System.out.println("Valor total: R$ " + venda.getValorTotal());
            System.out.println("Desconto: R$ " + venda.getDesconto());
            System.out.println("Frete: R$ " + venda.getFrete());
            System.out.println("ICMS: R$ " + venda.getICMS());
            System.out.println("Imposto Municipal: R$ " + venda.getImpostoMunicipal());
            System.out.println("Cashback gerado: R$ " + venda.getCashbackGerado());
            System.out.println("--------------------");
        }
    }
}
