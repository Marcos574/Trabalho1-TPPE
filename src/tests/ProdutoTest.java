package tests;

import static org.junit.Assert.assertEquals;
import org.junit.Test;
import main.Produto;

public class ProdutoTest {

    @Test
    public void testProdutoConstructor() {
        String codigo = "12345";
        String descricao = "Produto Teste";
        double valorVenda = 10.0;
        String unidade = "unidade";

        Produto produto = new Produto(codigo, descricao, valorVenda, unidade);

        assertEquals(codigo, produto.getCodigo());
        assertEquals(descricao, produto.getDescricao());
        assertEquals(valorVenda, produto.getValorVenda(), 0.001);
        assertEquals(unidade, produto.getUnidade());
    }

    @Test
    public void testGetCodigo() {
        String codigo = "12345";
        Produto produto = new Produto(codigo, "Produto Teste", 10.0, "unidade");
        assertEquals(codigo, produto.getCodigo());
    }

    @Test
    public void testGetDescricao() {
        String descricao = "Produto Teste";
        Produto produto = new Produto("12345", descricao, 10.0, "unidade");
        assertEquals(descricao, produto.getDescricao());
    }

    @Test
    public void testGetValorVenda() {
        double valorVenda = 10.0;
        Produto produto = new Produto("12345", "Produto Teste", valorVenda, "unidade");
        assertEquals(valorVenda, produto.getValorVenda(), 0.001);
    }

    @Test
    public void testGetUnidade() {
        String unidade = "unidade";
        Produto produto = new Produto("12345", "Produto Teste", 10.0, unidade);
        assertEquals(unidade, produto.getUnidade());
    }
}
