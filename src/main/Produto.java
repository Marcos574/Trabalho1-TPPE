package main;

public class Produto {
    private String codigo;
    private String descricao;
    private double valorVenda; // Valor de venda do produto
    private String unidade;

    public Produto(String codigo, String descricao, double valorVenda, String unidade) {
        this.codigo = codigo;
        this.descricao = descricao;
        this.valorVenda = valorVenda;
        this.unidade = unidade;
    }

    public String getCodigo() {
        return codigo;
    }

    public String getDescricao() {
        return descricao;
    }

    public double getValorVenda() {
        return valorVenda;
    }

    public String getUnidade() {
        return unidade;
    }
}