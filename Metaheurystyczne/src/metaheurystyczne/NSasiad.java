package metaheurystyczne;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class NSasiad {
    private Macierz m;
    private List<Integer> nieOdw;
    public NSasiad(Macierz m) {
        this.m = m;
    }

    /**
     * Znajduje najbliższego nieodwiedzonego sąsiada i usuwa go z listy nieodwiedzonych
     * @param w wierzchołek, od którego szukamy numerowany {0,...,dimension-1}
     * @return najbliższy wierzchołek numerowany {0,...,dimension-1}
     */
    private int znajdzNajblizszy(int w) {

        int wynik = nieOdw.get(0);
        int min = m.odleglosci[w][wynik];
        int pozz = 0;

        for (int i = 1; i < nieOdw.size(); i++) {
            if (m.odleglosci[w][nieOdw.get(i)] < min) {
                wynik = nieOdw.get(i);
                min = m.odleglosci[w][wynik];
                pozz = i;
            }
        }
        nieOdw.remove(pozz);
        return wynik;
    }

    /**
     * Rozwiązanie
     * @param start wierzchołek numerowany {1,...,dimension}
     * @return rozwiązanie
     */
    public Rozwiazanie rozwiaz(int start) {
        nieOdw = new LinkedList<>();
        for (int i = 0; i < m.rozmiar; i++) {
            if (i != start - 1) {
                nieOdw.add(i);
            }
        }
        Rozwiazanie rozw = new Rozwiazanie(m.rozmiar);
        rozw.wierzcholki[0] = start;
        int obecny = start - 1;
        for (int i = 1; i < m.rozmiar; i++) {
            obecny = znajdzNajblizszy(obecny);
            rozw.wierzcholki[i] = obecny + 1;
        }
        return rozw;
    }

    /**
     * Rozwiązanie zaczynając z losowego wierzchołka
     * @return rozwiązanie
     */
    public Rozwiazanie rozwiazRandomStart() {
        return rozwiaz(new Random().nextInt(m.rozmiar));
    }

    /**
     * rozszerzona metoda najbliższego sąsiada
     * @return rozwiązanie
     */
    public Rozwiazanie rozwiazStartNiezalenie() {
        Rozwiazanie opt = rozwiaz(1);
        int min = m.funkcjaCelu(opt);
        for (int i = 2; i <= m.rozmiar; i++) {
            Rozwiazanie temp = rozwiaz(i);
            if (m.funkcjaCelu(temp) < min) {
                min = m.funkcjaCelu(temp);
                opt = temp;
            }
        }
        return opt;
    }
}