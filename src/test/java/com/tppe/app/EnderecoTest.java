package com.tppe.app;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized.class)
public class EnderecoTest {

    private String estado;
    private boolean capital;
    private boolean expectedCapital;

    public EnderecoTest(String estado, boolean capital, boolean expectedCapital) {
        this.estado = estado;
        this.capital = capital;
        this.expectedCapital = expectedCapital;
    }

    @Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
            {"SP", true, true},
            {"RJ", false, false},
            {"DF", true, true},
            {"MG", false, false},
            {"BA", true, true},
            {"RS", false, false}
        });
    }

    @Test
    public void testEnderecoCreation() {
        Endereco endereco = new Endereco(estado, capital);
        assertNotNull(endereco);
        assertEquals(estado, endereco.getEstado());
        assertEquals(expectedCapital, endereco.isCapital());
    }
}
