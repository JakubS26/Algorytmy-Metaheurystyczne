package metaheurystyczne;

import java.io.FileNotFoundException;

public interface Tryb {
    public Macierz wczytaj(Dane dane) throws FileNotFoundException;
}
