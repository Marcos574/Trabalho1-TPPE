package tests;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

import main.Cliente;
import main.Endereco;

public class ClienteTest {

    @Test
    public void testCriarCliente() {
        Endereco endereco = new Endereco("SP", true);
        Cliente cliente = new Cliente("João", "padrão", endereco);

        assertEquals("João", cliente.getNome());
        assertEquals("padrão", cliente.getTipo());
        assertEquals(endereco, cliente.getEndereco());
        assertEquals(0.0, cliente.getSaldoCashback(), 0.001);
    }
    
    @Test
    public void testAdicionarCashback() {
        Endereco endereco = new Endereco("SP", true);
        Cliente cliente = new Cliente("João", "padrão", endereco);
        cliente.adicionarCashback(50.0);

        assertEquals(50.0, cliente.getSaldoCashback(), 0.001);
    }

    @Test
    public void testUtilizarCashback() {
        Endereco endereco = new Endereco("SP", true);
        Cliente cliente = new Cliente("João", "padrão", endereco);
        cliente.adicionarCashback(50.0);
        cliente.utilizarCashback(20.0);

        assertEquals(30.0, cliente.getSaldoCashback(), 0.001);
    }
}
