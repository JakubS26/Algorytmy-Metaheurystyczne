package metaheurystyczne.tryby;

import metaheurystyczne.Dane;
import metaheurystyczne.Macierz;

import java.io.FileNotFoundException;

public interface Tryb {
    public Macierz wczytaj(Dane dane) throws FileNotFoundException;
}
