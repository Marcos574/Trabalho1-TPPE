// FreteCalculator.java
package com.tppe.app;

public class FreteCalculator {

    public int calcularFrete(Cliente cliente) {
        String estado = cliente.getEndereco().getEstado();
        boolean capital = cliente.getEndereco().isCapital();

        switch (estado) {
            case "DF":
                return 5;
            case "GO":
            case "MS":
            case "MT":
                return capital ? 10 : 13;
            case "BA":
            case "PE":
            case "MA":
            case "CE":
            case "RN":
            case "PB":
            case "PI":
            case "SE":
            case "AL":
                return capital ? 15 : 18;
            case "AM":
            case "PA":
            case "AP":
            case "AC":
            case "RO":
            case "RR":
            case "TO":
                return capital ? 20 : 25;
            case "SP":
            case "RJ":
            case "ES":
            case "MG":
                return capital ? 7 : 10;
            case "RS":
            case "SC":
            case "PR":
                return capital ? 10 : 13;
            default:
                return 0;
        }
    }
}
