package metaheurystyczne;

import java.io.FileNotFoundException;
import java.util.Scanner;

public class Tryb_upper_diag implements Tryb {
    @Override
    public Macierz wczytaj(Dane dane) throws FileNotFoundException {
        Scanner scan = new Scanner(dane.plik);
        Macierz m = new Macierz(dane.dimension);

        String linia = "";
        String[] splitLine = null;

        while(scan.hasNextLine() && !linia.startsWith("EDGE_WEIGHT_SECTION")) {
            linia = scan.nextLine();
        }

        int number = dane.dimension*(dane.dimension+1)/2;

        int weights[] = new int[number];

        int c = 0;

        while(c <= number-1) {

            linia = scan.nextLine();

            splitLine = linia.split(" ");
            splitLine = Test.deleteEmpty(splitLine);

            for(int k=0; k<=splitLine.length-1; k++) {
                weights[c] = Integer.parseInt(splitLine[k]);
                c++;
            }

        }

        for(int i=0; i<=dane.dimension-1; i++) {
            for(int j=i; j<=dane.dimension-1; j++) {
                m.odleglosci[i][j] = weights[i*(i+1)/2+j];
                m.odleglosci[j][i] = m.odleglosci[i][j];
            }
        }

        scan.close();
        return m;
    }
}
