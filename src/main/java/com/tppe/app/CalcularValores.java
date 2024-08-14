// CalcularValores.java
package com.tppe.app;

import java.util.List;

public class CalcularValores {

    private Cliente cliente;
    private List<Produto> produtos;
    private String numCartao;
    private FreteCalculator freteCalculator;

    private double valorTotal;
    private double desconto;
    private double icms;
    private double impostoMunicipal;
    private double frete;
    private double cashbackGerado;

    public CalcularValores(Cliente cliente, List<Produto> produtos, String numCartao, FreteCalculator freteCalculator) {
        this.cliente = cliente;
        this.produtos = produtos;
        this.numCartao = numCartao;
        this.freteCalculator = freteCalculator;
    }

    public void execute() {
        double valorProdutos = produtos.stream().mapToDouble(Produto::getValorVenda).sum();
        this.desconto = valorProdutos * calcularPercentuaDeDesconto() / 100;
        this.frete = freteCalculator.calcularFrete(cliente) * (100 - calcularPercentuaDeDescontoDoFrete()) / 100;
        this.icms = calcularICMS() * valorProdutos / 100;
        this.impostoMunicipal = calcularImpostoMunicipal() * valorProdutos / 100;
        this.cashbackGerado = valorProdutos * calcularCashback() / 100;
        this.valorTotal = valorProdutos - desconto + frete + icms + impostoMunicipal;
    }

    public int calcularPercentuaDeDesconto() {
        if (!cliente.getTipo().equals("especial")) return 0;
        int desconto = 10;
        if (ehCartaoDaEmpresa()) desconto += 10;
        return desconto;
    }

    public Boolean ehCartaoDaEmpresa() {
        return numCartao.startsWith("429613");
    }

    public int calcularPercentuaDeDescontoDoFrete() {
        if (cliente.getTipo().equals("especial")) return 30;
        else if (cliente.getTipo().equals("prime")) return 100;
        return 0;
    }

    public int calcularICMS() {
        return cliente.getEndereco().getEstado().equals("DF") ? 18 : 12;
    }

    public int calcularImpostoMunicipal() {
        return cliente.getEndereco().getEstado().equals("DF") ? 0 : 4;
    }

    public int calcularCashback() {
        if (cliente.getTipo().equals("prime")) {
            return ehCartaoDaEmpresa() ? 5 : 3;
        }
        return 0;
    }

    public double getValorTotal() {
        return valorTotal;
    }

    public double getDesconto() {
        return desconto;
    }

    public double getIcms() {
        return icms;
    }

    public double getImpostoMunicipal() {
        return impostoMunicipal;
    }

    public double getFrete() {
        return frete;
    }

    public double getCashbackGerado() {
        return cashbackGerado;
    }
}
