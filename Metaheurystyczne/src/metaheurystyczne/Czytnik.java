package metaheurystyczne;

import java.io.FileNotFoundException;
import java.util.Scanner;

public class Czytnik {

    public static String szukajKlucz(Dane dane, String klucz) throws FileNotFoundException {

        String linia = "";
        Scanner scan = new Scanner(dane.plik);
        while(scan.hasNextLine() && !linia.startsWith(klucz)) {
            linia = scan.nextLine();
        }
        String splitLine[] = linia.split(" ");
        scan.close();
        if (splitLine[splitLine.length-1].matches(".*[(].*[)].*")) {
            return splitLine[splitLine.length-2];
        }
        return splitLine[splitLine.length-1];
        //return splitLine[1];
    }

    public static Macierz czytaj(Dane dane) throws FileNotFoundException {
        Tryb tryb;
        if (dane.type.startsWith("ATSP")) {
            tryb = new Tryb_full();
        } else if (dane.type.startsWith("TSP")) {
            if (dane.edgeWeight.startsWith("EUC_2D")) {
                tryb = new Tryb_euc_2d();
            } else if (dane.edgeWeight.startsWith("CEIL_2D")) {
                tryb = new Tryb_ceil_2d();
            } else if (dane.edgeWeight.startsWith("EXPLICIT")) {
                if (dane.format.startsWith("FULL_MATRIX")) {
                    tryb = new Tryb_full();
                } else if (dane.format.startsWith("LOWER_DIAG_ROW")) {
                    tryb = new Tryb_lower_diag();
                } else if (dane.format.startsWith("UPPER_DIAG_ROW")) {
                    tryb = new Tryb_upper_diag();
                } else if (dane.format.startsWith("UPPER_ROW")) {
                    tryb = new Tryb_upper();
                } else {
                    throw new IllegalArgumentException("nieznany format macierzy: " + dane.format);
                }
            } else {
                throw new IllegalArgumentException("nieznany typ danych: " + dane.edgeWeight);
            }
        } else {
            throw new IllegalArgumentException("to nie jest TSP/ATSP: " + dane.type);
        }
        return tryb.wczytaj(dane);
    }

}
