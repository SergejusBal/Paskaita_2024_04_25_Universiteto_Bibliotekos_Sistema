package Leidiniai;

public abstract class Leidinys {
    private String pavadinimas;

    public Leidinys(){}
    public Leidinys(String pavadinimas) {
        this.pavadinimas = pavadinimas;
    }

    public abstract void rodytiInformacija();

    public String getPavadinimas() {
        return pavadinimas;
    }
    public void setPavadinimas(String pavadinimas) {
        this.pavadinimas = pavadinimas;
    }
    public abstract void nuskaitytiInformacija();

}
