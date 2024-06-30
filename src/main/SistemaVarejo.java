package main;

import java.util.ArrayList;
import java.util.List;
import java.util.Date;

public class SistemaVarejo {
    private List<Cliente> clientes;
    private List<Produto> produtos;

    public SistemaVarejo() {
        this.clientes = new ArrayList<>();
        this.produtos = new ArrayList<>();
    }

    public void cadastrarCliente(Cliente cliente) {
        clientes.add(cliente);
    }

    public void cadastrarProduto(Produto produto) {
        produtos.add(produto);
    }

    public double calcularSaldoCashback(Cliente cliente) {
        return cliente.getSaldoCashback();
    }

    public List<Cliente> getClientes() {
        return clientes;
    }

    public List<Produto> getProdutos() {
        return produtos;
    }
}