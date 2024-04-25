package Leidiniai;
import Custom.Custom;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Zurnalas extends Leidinys{
    private String BENDRA_KLAIDA = "Įvyko klaida. Bandykite ivesti dar kartą.";
    private LocalDate localDate;

    public Zurnalas(){}
    public Zurnalas(String pavadinimas, LocalDate localDate) {
        super(pavadinimas);
        this.localDate = localDate;
    }

    public LocalDate getLocalDate() {
        return localDate;
    }

    public void setLocalDate(LocalDate localDate) {
        this.localDate = localDate;
    }

    @Override
    public void rodytiInformacija() {
        System.out.println("Pavadinimas: " + this.getPavadinimas() + " ** Leidimo data: " + this.localDate.toString());
    }
    @Override
    public void nuskaitytiInformacija() {
        System.out.println("Iveskite pavadinima:");
        this.setPavadinimas(Custom.nuskaitytiStringVerteCon());
        boolean arTeisingai = true;
        System.out.println("Iveskite data formatu: mmmm-MM-dd");
        while(arTeisingai) {
            String data = Custom.nuskaitytiStringVerteCon();
            try {
                this.localDate = LocalDate.parse(data, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                arTeisingai = false;
            } catch (DateTimeParseException e) {
                System.out.println(BENDRA_KLAIDA);
            }
        }
    }
}
