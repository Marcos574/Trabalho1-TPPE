package com.tppe.app;

import java.util.Date;
import java.util.List;


public class Venda {
    private Date data;
    private Cliente cliente;
    private List<Produto> produtos;
    private String metodoPagamento;
    private double valorTotal;
    private double desconto;
    private double icms;
    private double impostoMunicipal;
    private double frete;
    private double cashbackGerado;

    public Venda(Date data, Cliente cliente, List<Produto> produtos, String metodoPagamento) {
        this.data = data;
        this.cliente = cliente;
        this.produtos = produtos;
        this.metodoPagamento = metodoPagamento;
        calcularValores();
    }

    private void calcularValores() {
        double valorProdutos = produtos.stream().mapToDouble(Produto::getValorVenda).sum();
        this.desconto = calcularDesconto(valorProdutos);
        this.frete = calcularFrete();
        this.icms = calcularICMS(valorProdutos);
        this.impostoMunicipal = calcularImpostoMunicipal(valorProdutos);
        this.cashbackGerado = calcularCashback(valorProdutos);
        this.valorTotal = valorProdutos - desconto + frete + icms + impostoMunicipal;
        cliente.adicionarCashback(cashbackGerado);
    }

    private double calcularDesconto(double valorProdutos) {
        double desconto = 0.0;
        if (cliente.getTipo().equals("especial")) {
            desconto += valorProdutos * 0.10;
            if (metodoPagamento.startsWith("429613")) {
                desconto += valorProdutos * 0.10;
            }
            desconto += calcularFrete() * 0.30;
        } else if (cliente.getTipo().equals("prime")) {
            desconto += calcularFrete();
        }
        return desconto;
    }

    private double calcularFrete() {
        String estado = cliente.getEndereco().getEstado();
        boolean capital = cliente.getEndereco().isCapital();
        
        switch (estado) {
            case "DF":
                return 5.0;
            case "GO":
            case "MS":
            case "MT":
                return capital ? 10.0 : 13.0;
            case "BA":
            case "PE":
            case "MA":
            case "CE":
            case "RN":
            case "PB":
            case "PI":
            case "SE":
            case "AL":
                return capital ? 15.0 : 18.0;
            case "AM":
            case "PA":
            case "AP":
            case "AC":
            case "RO":
            case "RR":
            case "TO":
                return capital ? 20.0 : 25.0;
            case "SP":
            case "RJ":
            case "ES":
            case "MG":
                return capital ? 7.0 : 10.0;
            case "RS":
            case "SC":
            case "PR":
                return capital ? 10.0 : 13.0;
            default:
                return 0.0;
        }
    }

    private double calcularICMS(double valorProdutos) {
        return cliente.getEndereco().getEstado().equals("DF") ? valorProdutos * 0.18 : valorProdutos * 0.12;
    }

    private double calcularImpostoMunicipal(double valorProdutos) {
        return cliente.getEndereco().getEstado().equals("DF") ? 0.0 : valorProdutos * 0.04;
    }

    private double calcularCashback(double valorProdutos) {
        if (cliente.getTipo().equals("prime")) {
            return metodoPagamento.startsWith("429613") ? valorProdutos * 0.05 : valorProdutos * 0.03;
        }
        return 0.0;
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

    public String getMetodoPagamento() {
        return metodoPagamento;
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
