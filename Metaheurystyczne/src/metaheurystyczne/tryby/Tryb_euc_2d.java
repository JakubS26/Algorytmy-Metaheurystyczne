package metaheurystyczne.tryby;

import metaheurystyczne.Dane;
import metaheurystyczne.Macierz;
import metaheurystyczne.Test;

import java.io.FileNotFoundException;
import java.util.Scanner;

public class Tryb_euc_2d implements Tryb {
    @Override
    public Macierz wczytaj(Dane dane) throws FileNotFoundException {

        Scanner scan = new Scanner(dane.plik);
        Macierz m = new Macierz(dane.dimension);

        double x_coordinates[] = new double[dane.dimension];
        double y_coordinates[] = new double[dane.dimension];

        String linia = "";
        String[] splitLine = null;

        while(scan.hasNextLine() && !linia.startsWith("NODE_COORD_SECTION")) {
            linia = scan.nextLine();
        }

        for(int i=0; i<= dane.dimension-1; i++) {
            linia = scan.nextLine();
            splitLine = linia.split(" ");
            splitLine = Test.deleteEmpty(splitLine);

            x_coordinates[i] = Double.parseDouble(splitLine[1]);
            y_coordinates[i] = Double.parseDouble(splitLine[2]);

        }

        for(int i=0; i<= dane.dimension-1; i++) {
            for(int j=0; j<= dane.dimension-1; j++) {

                double dx = x_coordinates[i] - x_coordinates[j];
                double dy = y_coordinates[i] - y_coordinates[j];

                m.odleglosci[i][j] = (int)Math.round(Math.sqrt(dx*dx+dy*dy));
            }
        }

        scan.close();
        return m;
    }
}