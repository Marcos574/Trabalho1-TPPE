package com.tppe.app;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized.class)
public class VendaTest {

    private Venda venda;
    private boolean ehCartaoDaEmpresa;
    private int frete;
    private int descontoFrete;
    private int descontoPedido;
    private int cashback;
    private int impostoMunicipal;
    private int icms;

    public VendaTest(Date data, Cliente cliente, List<Produto> produtos, String numCartao, boolean ehCartaoDaEmpresa,
                     int frete, int descontoFrete, int descontoPedido, int cashback, int impostoMunicipal, int icms) {
        this.venda = new Venda(data, cliente, produtos, numCartao);
        this.ehCartaoDaEmpresa = ehCartaoDaEmpresa;
        this.frete = frete;
        this.descontoFrete = descontoFrete;
        this.descontoPedido = descontoPedido;
        this.cashback = cashback;
        this.impostoMunicipal = impostoMunicipal;
        this.icms = icms;
    }

    @Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
            {new Date(), new Cliente("ricardo", "prime", new Endereco("RJ", true)),
                List.of(new Produto("12", "pasta", 20.20, "12")), "8888888888888888", false, 0, 100, 0, 3, 4, 12},
            {new Date(), new Cliente("ricardo", "prime", new Endereco("RJ", true)),
                List.of(new Produto("12", "pasta", 20.20, "12")), "4296130000000000", true, 0, 100, 0, 5, 4, 12},
            {new Date(), new Cliente("cesar", "padrão", new Endereco("GO", false)),
                List.of(new Produto("12", "pasta", 20.20, "12")), "4296130000000000", true, 13, 0, 0, 0, 4, 12},
            {new Date(), new Cliente("rita", "especial", new Endereco("DF", true)),
                List.of(new Produto("12", "pasta", 20.20, "12")), "7296130000000000", false, 3, 30, 10, 0, 0, 18},
            {new Date(), new Cliente("julio", "especial", new Endereco("SE", true)),
                List.of(new Produto("12", "pasta", 20.20, "12")), "7296130000000000", false, 10, 30, 10, 0, 4, 12},
            {new Date(), new Cliente("julio", "especial", new Endereco("SE", true)),
                List.of(
                    new Produto("12", "pasta", 20, "12"),
                    new Produto("13", "sabonete", 25, "12"),
                    new Produto("14", "escova", 2, "12"),
                    new Produto("15", "barbeador", 100, "12")
                ), "7296130000000000", false, 10, 30, 10, 0, 4, 12},
        });
    }

    @Test
    public void testEhCartaoDaLoja() {
        assertEquals(this.ehCartaoDaEmpresa, venda.ehCartaoDaEmpresa());
    }

    @Test
    public void testCalcularFrete() {
        assertEquals(this.frete, venda.getFrete(), 0.01);
    }


    @Test
    public void testCalcularPercentuaDeDescontoDoFrete() {
        assertEquals(this.descontoFrete, venda.calcularPercentuaDeDescontoDoFrete());
    }

    @Test
    public void testCalcularPercentuaDeDesconto() {
        assertEquals(this.descontoPedido, venda.calcularPercentuaDeDesconto());
    }

    @Test
    public void testCalcularCashback() {
        assertEquals(this.cashback, venda.calcularCashback());
    }

    @Test
    public void testCalcularICMS() {
        assertEquals(this.icms, venda.calcularICMS());
    }

    @Test
    public void testCalcularImpostoMunicipal() {
        assertEquals(this.impostoMunicipal, venda.calcularImpostoMunicipal());
    }
}
