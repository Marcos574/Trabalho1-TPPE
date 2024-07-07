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



    public Venda(Date data, Cliente cliente, List<Produto> produtos, String numCartao) {
        this.data = data;
        this.cliente = cliente;
        this.produtos = produtos;
        this.numCartao = numCartao;
        calcularValores();
    }



    private void calcularValores() {
        double valorProdutos = produtos.stream().mapToDouble(Produto::getValorVenda).sum();
        this.desconto = valorProdutos * (100 - calcularPercentuaDeDesconto())  / 100;
        this.frete = calcularFrete() * (100 - calcularPercentuaDeDescontoDoFrete())/100;
        this.icms = calcularICMS(valorProdutos);
        this.impostoMunicipal = calcularImpostoMunicipal(valorProdutos);
        this.cashbackGerado = valorProdutos * calcularCashback();
        this.valorTotal = valorProdutos - desconto + frete + icms + impostoMunicipal;
        cliente.adicionarCashback(cashbackGerado);
    }

    public Boolean ehCartaoDaEmpresa() {
      return numCartao.startsWith("429613");
    }



    public int calcularPercentuaDeDesconto() {


        if ( !cliente.getTipo().equals("especial"))
          return 0;

        int desconto = 10;

        if (ehCartaoDaEmpresa())
          desconto += 10;


        return desconto;
    }

    public int calcularPercentuaDeDescontoDoFrete(){

        if ( cliente.getTipo().equals("especial"))
          return 30;

        else if (  cliente.getTipo().equals("prime"))
          return 100;

      return 0;

    }

    public int calcularFrete() {
        String estado = cliente.getEndereco().getEstado();
        boolean capital = cliente.getEndereco().isCapital();

        switch (estado) {
            case "DF":
                return 5;
            case "GO":
            case "MS":
            case "MT":
                return capital ? 10 : 13;
            case "BA":
            case "PE":
            case "MA":
            case "CE":
            case "RN":
            case "PB":
            case "PI":
            case "SE":
            case "AL":
                return capital ? 15 : 18;
            case "AM":
            case "PA":
            case "AP":
            case "AC":
            case "RO":
            case "RR":
            case "TO":
                return capital ? 20 : 25;
            case "SP":
            case "RJ":
            case "ES":
            case "MG":
                return capital ? 7: 10;
            case "RS":
            case "SC":
            case "PR":
                return capital ? 10 : 13;
            default:
                return 0;
        }
    }

    private double calcularICMS(double valorProdutos) {
        return cliente.getEndereco().getEstado().equals("DF") ? valorProdutos * 0.18 : valorProdutos * 0.12;
    }

    private double calcularImpostoMunicipal(double valorProdutos) {
        return cliente.getEndereco().getEstado().equals("DF") ? 0.0 : valorProdutos * 0.04;
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
