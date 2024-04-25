package BibliotekosValdymas;

import Custom.Custom;
import Leidiniai.Knyga;
import Leidiniai.Leidinys;
import Leidiniai.Zurnalas;

import java.sql.SQLOutput;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class UniversitetoBibliotekaImp implements BibliotekosValdymas {

    private List<Leidinys> leidinysList;
    private String LEIDINIU_PATH = "C:/Users/Sergejus/IdeaProjects/Paskaita_2024_04_25_Universiteto_Bibliotekos_Sistema/src/Resources/LeidiniuSarasas.csv";

    private String BENDRA_KLAIDA = "Įvyko klaida. Bandykite ivesti dar kartą.";

    public UniversitetoBibliotekaImp(){
        leidinysList = new ArrayList<>();
        importuotiCSV(LEIDINIU_PATH);
    }

    public void paleistiUI(){

        boolean veikia = true;
        while(veikia) {
            System.out.println("*********************************************************");
            System.out.println("Pasirinkite paslaugą: ");
            System.out.println("Pridėti leidinį: (1)");
            System.out.println("Pašalinti leidinį: (2)");
            System.out.println("Surasti leidinį: (3)");
            System.out.println("Spauzdintivisą sąrašą: (4)");
            System.out.println("Uždaryti programa: (0)");
            System.out.println("Iveskite pasirinkima: ");
            int pasirinkimas = Custom.nuskaitytiIntVerteCon();
            switch (pasirinkimas){
                case 1:
                    pridetiLeidini();
                    break;
                case 2:
                    System.out.println("Iveskite leidinio pavadinima, kurį norite pašalinti:");
                    pašalintiLeidini(Custom.nuskaitytiStringVerteCon());
                    System.out.println("Leidinys pašalintas");
                    break;
                case 3:
                    System.out.println("Iveskite leidinio pavadinima:");
                    String pavadinimas = Custom.nuskaitytiStringVerteCon();
                    try {
                        ieškotiLeidinio(pavadinimas);
                        System.out.println(String.format("Leidinys: %s rastas.",pavadinimas));
                    }catch (LeidinioNerastaException e){
                        System.out.println(String.format("Leidinys: %s nerastas.",pavadinimas));
                    }
                    break;
                case 4:
                    for(Leidinys l:getLeidinysList()) l.rodytiInformacija();
                    break;
                case 0:
                    veikia = false;
                    System.out.println("Programa užsidaro");
                    break;
                default:
                    System.out.println("Tokios operacijos neatliekame!");
                    break;
            }
        }
    }

    @Override
    public void pridetiLeidini(Leidinys leidinys) {
        leidinysList.add(leidinys);
    }
    public void pridetiLeidini() {
        leidinysList.add(nuskaitytiLeidini());
    }

    @Override
    public void pašalintiLeidini(String pavadinimas) {
        Leidinys leidinys = null;
        try{
            leidinys = ieškotiLeidinio(pavadinimas);
        }catch (LeidinioNerastaException e){
            System.out.println(BENDRA_KLAIDA);
            pašalintiLeidini(pavadinimas);
        }
        leidinysList.remove(leidinys);
    }

    @Override
    public Leidinys ieškotiLeidinio(String pavadinimas) throws LeidinioNerastaException{
        int index = -1;
        for(int i = 0; i < leidinysList.size(); i++){
            if(leidinysList.get(i).getPavadinimas().equals(pavadinimas)){
                index = i;
                break;
            }
        }
        if(index == -1) throw new LeidinioNerastaException("Leidinys nerastas!");
        return getLeidinysList().get(index);
    }

    public List<Leidinys> getLeidinysList() {
        return leidinysList;
    }

    private void importuotiCSV(String path){
        List<String> leidiniiaiCSV = Custom.nuskaitytiFaila(path);
        Leidinys leidinys =null;
        for(String s:leidiniiaiCSV){
            String[] vertes = s.split(",");
            try {
                LocalDate date = LocalDate.parse(vertes[1], DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                leidinys = new Zurnalas(vertes[0],date);
            }catch (DateTimeParseException e){
                leidinys = new Knyga(vertes[0],vertes[1]);
            }
            pridetiLeidini(leidinys);
        }
    }

    private Leidinys nuskaitytiLeidini(){
        System.out.println("Pasirinkite leidinio tipa: Knyga (1), Žurnalas (2)");
        int pasirinkimas = Custom.nuskaitytiIntVerteCon();
        Leidinys leidinys = null;
        switch (pasirinkimas) {
            case 1:
                leidinys = new Knyga();
                leidinys.nuskaitytiInformacija();
                break;
            case 2:
                leidinys = new Zurnalas();
                leidinys.nuskaitytiInformacija();
                break;
            default:
                System.out.println("Tokio tipo leidinių kolkas nėra.");
                nuskaitytiLeidini();
                break;
        }
        return leidinys;
    }


}
