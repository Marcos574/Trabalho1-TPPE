package main;

public class Endereco {
    private String estado;
    private boolean capital;

    public Endereco(String estado, boolean capital) {
        this.estado = estado;
        this.capital = capital;
    }

    public String getEstado() {
        return estado;
    }

    public boolean isCapital() {
        return capital;
    }
}

