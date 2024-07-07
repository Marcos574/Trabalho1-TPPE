package com.tppe.app;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

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

    private boolean ehCartaoDaEmpresa ;

    private int frete;
    private int descontoFrete;
    
	public VendaTest(Date data, Cliente cliente, List<Produto> produtos, String numCartao, Boolean ehCartaoDaEmpresa, int frete, int descontoFrete) {
    this.venda = new Venda(data, cliente, produtos, numCartao);
    this.ehCartaoDaEmpresa = ehCartaoDaEmpresa;

    this.frete = frete;
    this.descontoFrete = descontoFrete;



	}

    @Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
            {new Date(), new Cliente("ricardo", "prime",    new Endereco("RJ", true)), List.of(new Produto("12", "pasta", 20.20,"12")), "4296130000000000", true, 7, 100},
            {new Date(), new Cliente("cesar"  , "padrão",   new Endereco("GO", false)), List.of(new Produto("12", "pasta", 20.20,"12")), "4296130000000000", true, 13, 0},
            {new Date(), new Cliente("rita"   , "especial", new Endereco("DF", true)), List.of(new Produto("12", "pasta", 20.20,"12")), "7296130000000000", false, 5, 30},
            {new Date(), new Cliente("julio"  , "especial", new Endereco("SE", true)), List.of(new Produto("12", "pasta", 20.20,"12")), "7296130000000000", false, 15, 30},
        });
    }


  @Test
  public void ehCartaoDaLojaTest(){
    assertTrue(venda.ehCartaoDaEmpresa() == this.ehCartaoDaEmpresa);
  }

	
	@Test
	public void calcularFrete() {
    assertEquals( this.frete, venda.calcularFrete());
	}

	@Test
	public void calcularDescontoFrete() {
    assertEquals(this.descontoFrete, venda.calcularPercentuaDeDescontoDoFrete());
	}
    
	@Test
	public void calcularDescontoItems() {
    assertTrue (true);
	}
}
