package BibliotekosValdymas;

import Leidiniai.Leidinys;

public interface BibliotekosValdymas {
    void pridetiLeidini(Leidinys leidinys);
    void pašalintiLeidini(String pavadinimas);
    Leidinys ieškotiLeidinio(String pavadinimas) throws LeidinioNerastaException;




}
