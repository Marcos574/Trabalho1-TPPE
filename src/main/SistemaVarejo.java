package main;

import java.util.ArrayList;
import java.util.List;
import java.util.Date;

public class SistemaVarejo {
    private List<Cliente> clientes;


    public SistemaVarejo() {
        this.clientes = new ArrayList<>();

    }

    public void cadastrarCliente(Cliente cliente) {
        clientes.add(cliente);
    }


    public double calcularSaldoCashback(Cliente cliente) {
        return cliente.getSaldoCashback();
    }

    public List<Cliente> getClientes() {
        return clientes;
    }

}