package tests;

import org.junit.Test;

import main.Endereco;

import static org.junit.Assert.*;

public class EnderecoTest {

    @Test
    public void testEnderecoCreation() {
        Endereco endereco = new Endereco("SP", true);
        assertNotNull(endereco);
    }

    @Test
    public void testGetEstado() {
        Endereco endereco = new Endereco("RJ", false);
        assertEquals("RJ", endereco.getEstado());
    }

    @Test
    public void testIsCapital() {
        Endereco endereco1 = new Endereco("DF", true);
        assertTrue(endereco1.isCapital());

        Endereco endereco2 = new Endereco("MG", false);
        assertFalse(endereco2.isCapital());
    }
}
