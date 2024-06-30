package tests;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import main.Cliente;
import main.Endereco;

import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public class ClienteTest {

    private String nome;
    private String tipo;
    private String estado;
    private boolean capital;
    private double initialCashback;
    private double addedCashback;
    private double usedCashback;
    private double expectedCashback;
    private Cliente cliente;

    public ClienteTest(String nome, String tipo, String estado, boolean capital, double initialCashback, double addedCashback, double usedCashback, double expectedCashback) {
        this.nome = nome;
        this.tipo = tipo;
        this.estado = estado;
        this.capital = capital;
        this.initialCashback = initialCashback;
        this.addedCashback = addedCashback;
        this.usedCashback = usedCashback;
        this.expectedCashback = expectedCashback;
    }

    @Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
            {"João", "padrão", "SP", true, 0.0, 50.0, 20.0, 30.0},
            {"Maria", "especial", "RJ", false, 0.0, 40.0, 10.0, 30.0},
            {"Pedro", "prime", "MG", true, 0.0, 45.0, 5.0, 40.0},
            {"Ana", "padrão", "DF", false, 0.0, 30.0, 10.0, 20.0}
        });
    }

    @Before
    public void setUp() {
        Endereco endereco = new Endereco(estado, capital);
        cliente = new Cliente(nome, tipo, endereco);
        cliente.adicionarCashback(initialCashback);
    }

    @Test
    public void testCriarCliente() {
        assertEquals(nome, cliente.getNome());
        assertEquals(tipo, cliente.getTipo());
        assertEquals(estado, cliente.getEndereco().getEstado());
        assertEquals(capital, cliente.getEndereco().isCapital());
        assertEquals(initialCashback, cliente.getSaldoCashback(), 0.001);
    }

    @Test
    public void testAdicionarEUtilizarCashback() {
        cliente.adicionarCashback(addedCashback);
        cliente.utilizarCashback(usedCashback);
        assertEquals(expectedCashback, cliente.getSaldoCashback(), 0.001);
    }
}
