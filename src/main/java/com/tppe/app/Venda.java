// Venda.java
package com.tppe.app;

import java.util.Date;
import java.util.List;

public class Venda {

    private Date data;
    private Cliente cliente;
    private List<Produto> produtos;
    private String numCartao;

    // valores derivados
    private double valorTotal;
    private double desconto;
    private double icms;
    private double impostoMunicipal;
    private double frete;
    private double cashbackGerado;

    private FreteCalculator freteCalculator;

    public Venda(Date data, Cliente cliente, List<Produto> produtos, String numCartao) {
        this.data = data;
        this.cliente = cliente;
        this.produtos = produtos;
        this.numCartao = numCartao;
        this.freteCalculator = new FreteCalculator();
        calcularValores();
    }

    void calcularValores() {
        CalcularValores calculo = new CalcularValores(cliente, produtos, numCartao, freteCalculator);
        calculo.execute();
        this.valorTotal = calculo.getValorTotal();
        this.desconto = calculo.getDesconto();
        this.icms = calculo.getIcms();
        this.impostoMunicipal = calculo.getImpostoMunicipal();
        this.frete = calculo.getFrete();
        this.cashbackGerado = calculo.getCashbackGerado();
        cliente.adicionarCashback(cashbackGerado);
    }

    public Boolean ehCartaoDaEmpresa() {
        return numCartao.startsWith("429613");
    }

    public int calcularPercentuaDeDesconto() {
        if (!cliente.getTipo().equals("especial")) return 0;
        int desconto = 10;
        if (ehCartaoDaEmpresa()) desconto += 10;
        return desconto;
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

    public Date getData() {
        return data;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public List<Produto> getProdutos() {
        return produtos;
    }

    public String getNumCartao() {
        return numCartao;
    }

    public double getValorTotal() {
        return valorTotal;
    }

    public double getDesconto() {
        return desconto;
    }

    public double getICMS() {
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
