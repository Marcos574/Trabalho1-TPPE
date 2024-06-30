package main;

import java.util.ArrayList;
import java.util.List;
import java.util.Date;

public class SistemaVarejo {
    private List<Cliente> clientes;
    private List<Produto> produtos;
    private List<Venda> vendas;

    public SistemaVarejo() {
        this.clientes = new ArrayList<>();
        this.produtos = new ArrayList<>();
        this.vendas = new ArrayList<>();
    }

    public void cadastrarCliente(Cliente cliente) {
        clientes.add(cliente);
    }

    public void cadastrarProduto(Produto produto) {
        produtos.add(produto);
    }

    public void realizarVenda(Venda venda) {
        vendas.add(venda);
        // Atualiza o status do cliente especial se necessário
        if (calcularValorUltimoMes(venda.getCliente()) > 100.0) {
            venda.getCliente().setTipo("especial");
        }
    }

    public double calcularValorUltimoMes(Cliente cliente) {
        double total = 0.0;
        Date umMesAtras = new Date(System.currentTimeMillis() - (30L * 24 * 60 * 60 * 1000));
        for (Venda venda : vendas) {
            if (venda.getCliente().equals(cliente) && venda.getData().after(umMesAtras)) {
                total += venda.getValorTotal();
            }
        }
        return total;
    }

    public double calcularSaldoCashback(Cliente cliente) {
        return cliente.getSaldoCashback();
    }

    public List<Venda> getVendas() {
        return vendas;
    }

    public List<Cliente> getClientes() {
        return clientes;
    }

    public List<Produto> getProdutos() {
        return produtos;
    }
}