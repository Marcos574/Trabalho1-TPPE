package tests;

import main.Cliente;
import main.Endereco;
import main.Produto;
import main.SistemaVarejo;
import main.Venda;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import java.util.Arrays;
import java.util.Collection;
import java.util.Date;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class SistemaVarejoTest {

    private SistemaVarejo sistema = new SistemaVarejo();
    private String testName;
    private Object input;
    private Object expectedOutput;

    public SistemaVarejoTest(String testName, Object input, Object expectedOutput) {
        this.testName = testName;
        this.input = input;
        this.expectedOutput = expectedOutput;
    }

    @Parameters(name = "{index}: {0}")
    public static Collection<Object[]> data() {
        Endereco endereco = new Endereco("SP", true);
        Cliente cliente1 = new Cliente("João", "padrão", endereco);
        Cliente cliente2 = new Cliente("Maria", "especial", endereco);
        Cliente cliente3 = new Cliente("Pedro", "prime", endereco);

        Produto produto1 = new Produto("001", "Produto 1", 10.0, "unidade");
        Produto produto2 = new Produto("002", "Produto 2", 20.0, "unidade");

        Venda venda1 = new Venda(new Date(), cliente1, Arrays.asList(produto1), "dinheiro");
        Venda venda2 = new Venda(new Date(), cliente2, Arrays.asList(produto2), "cartão");
        Venda venda3 = new Venda(new Date(), cliente3, Arrays.asList(produto1, produto2), "boleto");

        return Arrays.asList(new Object[][]{
            {"deveCadastrarCliente", cliente1, 1},
            {"deveCadastrarProduto", produto1, 1},
            {"deveRealizarVenda", venda1, 1},
            {"deveRealizarVenda", venda2, 1},
            {"deveRealizarVenda", venda3, 1}
        });
    }

    @Test
    public void testSistemaVarejo() {
        switch (testName) {
            case "deveCadastrarCliente":
                sistema.cadastrarCliente((Cliente) input);
                assertEquals(expectedOutput, sistema.getClientes().size());
                assertEquals(input, sistema.getClientes().get(0));
                break;
            case "deveCadastrarProduto":
                sistema.cadastrarProduto((Produto) input);
                assertEquals(expectedOutput, sistema.getProdutos().size());
                assertEquals(input, sistema.getProdutos().get(0));
                break;
            case "deveRealizarVenda":
                sistema.cadastrarCliente(((Venda) input).getCliente());
                sistema.cadastrarProduto(((Venda) input).getProdutos().get(0));
                if (((Venda) input).getProdutos().size() > 1) {
                    sistema.cadastrarProduto(((Venda) input).getProdutos().get(1));
                }
                sistema.realizarVenda((Venda) input);
                assertEquals(expectedOutput, sistema.getVendas().size());
                assertEquals(input, sistema.getVendas().get(0));
                break;
        }
    }
}
