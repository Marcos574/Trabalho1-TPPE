package com.tppe.app;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;


import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Collection;


// Nesta classe estão os testes somente do cadastro de produtos
@RunWith(Parameterized.class)
public class ProdutoTest {

    private String codigo;
    private String descricao;
    private double valorVenda;
    private String unidade;

    public ProdutoTest(String codigo, String descricao, double valorVenda, String unidade) {
        this.codigo = codigo;
        this.descricao = descricao;
        this.valorVenda = valorVenda;
        this.unidade = unidade;
    }

    @Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
            {"12345", "Produto Teste 1", 10.0, "unidade"},
            {"67890", "Produto Teste 2", 20.0, "pacote"},
            {"11121", "Produto Teste 3", 30.0, "caixa"},
            {"31415", "Produto Teste 4", 40.0, "litro"},
            {"16171", "Produto Teste 5", 50.0, "quilo"}
        });
    }

    @Test
    public void testProdutoConstructor() {
        Produto produto = new Produto(codigo, descricao, valorVenda, unidade);

        assertEquals(codigo, produto.getCodigo());
        assertEquals(descricao, produto.getDescricao());
        assertEquals(valorVenda, produto.getValorVenda(), 0.001);
        assertEquals(unidade, produto.getUnidade());
    }

    @Test
    public void testGetCodigo() {
        Produto produto = new Produto(codigo, descricao, valorVenda, unidade);
        assertEquals(codigo, produto.getCodigo());
    }

    @Test
    public void testGetDescricao() {
        Produto produto = new Produto(codigo, descricao, valorVenda, unidade);
        assertEquals(descricao, produto.getDescricao());
    }

    @Test
    public void testGetValorVenda() {
        Produto produto = new Produto(codigo, descricao, valorVenda, unidade);
        assertEquals(valorVenda, produto.getValorVenda(), 0.001);
    }

    @Test
    public void testGetUnidade() {
        Produto produto = new Produto(codigo, descricao, valorVenda, unidade);
        assertEquals(unidade, produto.getUnidade());
    }
}
