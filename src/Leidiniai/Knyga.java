package Leidiniai;

import Custom.Custom;

import java.util.Objects;

public class Knyga extends Leidinys{

    private String autorius;
    public Knyga(){}
    public Knyga(String pavadinimas, String autorius) {
        super(pavadinimas);
        this.autorius = autorius;
    }

    public String getAutorius() {
        return autorius;
    }

    public void setAutorius(String autorius) {
        this.autorius = autorius;
    }



    @Override
    public void rodytiInformacija() {
        System.out.println("Pavadinimas: " + this.getPavadinimas() + " ** Autorius: " + this.autorius);
    }

    @Override
    public void nuskaitytiInformacija() {
        System.out.println("Iveskite pavadinima:");
        this.setPavadinimas(Custom.nuskaitytiStringVerteCon());
        System.out.println("Iveskite autoriu:");
        this.setAutorius(Custom.nuskaitytiStringVerteCon());
    }


}
