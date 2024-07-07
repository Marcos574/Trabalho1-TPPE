package com.tppe.app;

public class Cliente {

    private String nome;
    private String tipo; // "padrão", "especial" ou "prime"
    private Endereco endereco;
    private double saldoCashback;

    public Cliente(String nome, String tipo, Endereco endereco) {
        this.nome = nome;
        this.tipo = tipo;
        this.endereco = endereco;
        this.saldoCashback = 0.0;
    }

    public String getNome() {
        return nome;
    }

    public String getTipo() {
        return tipo;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public double getSaldoCashback() {
        return saldoCashback;
    }

    public void adicionarCashback(double valor) {
        this.saldoCashback += valor;
    }

    public void utilizarCashback(double valor) {
        this.saldoCashback -= valor;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}

