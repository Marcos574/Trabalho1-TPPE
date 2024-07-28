package com.tppe.app;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import java.util.Arrays;
import java.util.Collection;
import java.util.Date;

import static org.junit.Assert.assertEquals;


// Aqui estão os testes mais significativos, relacionados à operações que são feitas no sistema de varejo,
// aqui são testados os cálculos e descontos que uma venda deve gerar, desde o frete até o ICMS
@RunWith(Parameterized.class)
public class SistemaVarejoTest {

    private SistemaVarejo sistema = new SistemaVarejo();
    private String testName;
    private Venda venda;
    private double expectedOutput;

    public SistemaVarejoTest(String testName, Venda venda, double expectedOutput) {
        this.testName = testName;
        this.venda = venda;
        this.expectedOutput = expectedOutput;
    }
    
    // Criando os clientes, produtos e vendas necessários para o teste
    @Parameters(name = "{index}: {0}")
    public static Collection<Object[]> data() {
        Endereco endereco = new Endereco("SP", true);
        Cliente cliente1 = new Cliente("João", "padrão", endereco);
        Cliente cliente2 = new Cliente("Maria", "especial", endereco);
        Cliente cliente3 = new Cliente("Pedro", "prime", endereco);

        Produto produto1 = new Produto("001", "Produto 1", 10.0, "unidade");
        Produto produto2 = new Produto("002", "Produto 2", 20.0, "unidade");

        Venda venda1 = new Venda(new Date(), cliente1, Arrays.asList(produto1), "4296130000000000"); // Cartão da empresa
        Venda venda2 = new Venda(new Date(), cliente2, Arrays.asList(produto2), "1234567890123456"); // Outro cartão
        Venda venda3 = new Venda(new Date(), cliente3, Arrays.asList(produto1, produto2), "4296131234567890"); // Cartão da empresa
        
        // Criando uma lista com cada objeto sendo oque deve ser testado, qual venda está relacionada ao teste e a saída esperada
        return Arrays.asList(new Object[][]{
            {"deveCalcularDescontoVenda1", venda1, 0.0},
            {"deveCalcularDescontoVenda2", venda2, 2.0}, // 10% desconto especial
            {"deveCalcularDescontoVenda3", venda3, 0.0},
            {"deveCalcularFreteVenda1", venda1, 7.0},
            {"deveCalcularFreteVenda2", venda2, 4.0}, // 30% de desconto no frete
            {"deveCalcularFreteVenda3", venda3, 0.0}, // Frete grátis para clientes "prime"
            {"deveCalcularICMSVenda1", venda1, 1.2},
            {"deveCalcularICMSVenda2", venda2, 2.4},
            {"deveCalcularICMSVenda3", venda3, 3.6},
            {"deveCalcularImpostoMunicipalVenda1", venda1, 0.4},
            {"deveCalcularImpostoMunicipalVenda2", venda2, 0.8},
            {"deveCalcularImpostoMunicipalVenda3", venda3, 1.2},
            {"deveCalcularCashbackVenda1", venda1, 0.0},
            {"deveCalcularCashbackVenda2", venda2, 0.0},
            {"deveCalcularCashbackVenda3", venda3, 1.5}, // 5% cashback para cliente "prime" com cartão da empresa
        });
    }
    
    //Neste momento os testes são feitos de fato, utilizando a lista criada anteriormente como base, 
    // usando as saídas esperadas e comparando com a saída obtida pelo método que desejo testar
    @Test
    public void testSistemaVarejo() {
        sistema.cadastrarCliente(venda.getCliente());
        for (Produto produto : venda.getProdutos()) {
            sistema.cadastrarProduto(produto);
        }
        sistema.realizarVenda(venda);

        switch (testName) {
            case "deveCalcularDescontoVenda1":
            case "deveCalcularDescontoVenda2":
            case "deveCalcularDescontoVenda3":
                assertEquals(expectedOutput, venda.getDesconto(), 1);
                break;
            case "deveCalcularFreteVenda1":
            case "deveCalcularFreteVenda2":
            case "deveCalcularFreteVenda3":
                assertEquals(expectedOutput, venda.getFrete(), 1);
                break;
            case "deveCalcularICMSVenda1":
            case "deveCalcularICMSVenda2":
            case "deveCalcularICMSVenda3":
                assertEquals(expectedOutput, venda.getICMS(), 1);
                break;
            case "deveCalcularImpostoMunicipalVenda1":
            case "deveCalcularImpostoMunicipalVenda2":
            case "deveCalcularImpostoMunicipalVenda3":
                assertEquals(expectedOutput, venda.getImpostoMunicipal(), 1);
                break;
            case "deveCalcularCashbackVenda1":
            case "deveCalcularCashbackVenda2":
            case "deveCalcularCashbackVenda3":
                assertEquals(expectedOutput, venda.getCashbackGerado(), 1);
                break;
        }
    }
}
