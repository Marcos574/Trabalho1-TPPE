package tests;

import main.Cliente;
import main.Endereco;
import main.Produto;
import main.SistemaVarejo;
import org.junit.Test;
import java.util.Arrays;
import java.util.Date;
import static org.junit.Assert.assertEquals;

public class SistemaVarejoTest {

    @Test
    public void deveCadastrarCliente() {
        SistemaVarejo sistema = new SistemaVarejo();
        Endereco endereco = new Endereco("SP", true);
        Cliente cliente = new Cliente("João", "padrão", endereco);
        
        sistema.cadastrarCliente(cliente);
        
        assertEquals(1, sistema.getClientes().size());
        assertEquals(cliente, sistema.getClientes().get(0));
    }
    
    @Test
    public void deveCadastrarProduto() {
        SistemaVarejo sistema = new SistemaVarejo();
        Produto produto = new Produto("001", "Produto 1", 10.0, "unidade");
        
        sistema.cadastrarProduto(produto);
        
        assertEquals(1, sistema.getProdutos().size());
        assertEquals(produto, sistema.getProdutos().get(0));
    }
    
    @Test
    public void deveRealizarVenda() {
        SistemaVarejo sistema = new SistemaVarejo();
        Endereco endereco = new Endereco("SP", true);
        Cliente cliente = new Cliente("João", "padrão", endereco);
        Produto produto = new Produto("001", "Produto 1", 10.0, "unidade");
        sistema.cadastrarCliente(cliente);
        sistema.cadastrarProduto(produto);
        Venda venda = new Venda(new Date(), cliente, Arrays.asList(produto), "dinheiro");

        sistema.realizarVenda(venda);

        assertEquals(1, sistema.getVendas().size());
        assertEquals(venda, sistema.getVendas().get(0));
    }
}

