package tests;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

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
}
