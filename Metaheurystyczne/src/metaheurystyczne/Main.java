package metaheurystyczne;
import java.io.File; 
import java.io.FileNotFoundException;

public class Main {
	
	public static Macierz wczytajPlik(String nazwaPliku) {

		Dane dane = new Dane(new File(nazwaPliku));
		Macierz macierz = null;
		try {
			dane.setType(Czytnik.szukajKlucz(dane, "TYPE"));
			dane.setDimension(Czytnik.szukajKlucz(dane, "DIMENSION"));
			dane.setEdgeWeight(Czytnik.szukajKlucz(dane, "EDGE_WEIGHT_TYPE"));
			if (dane.edgeWeight.startsWith("EXPLICIT")) {
				dane.setFormat(Czytnik.szukajKlucz(dane, "EDGE_WEIGHT_FORMAT"));
			}
			macierz = Czytnik.czytaj(dane);
			System.out.println(dane.type);
			System.out.println(dane.dimension);
			System.out.println(dane.edgeWeight);
			if (dane.format != null) {
				System.out.println(dane.format);
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return macierz;

	}
	
	public static void main(String args[]) {
		
		//wczytajPlik("/home/jakub_s/AlgMeta/TSP/berlin52.tsp");
		//wczytajPlik("/home/jakub_s/AlgMeta/TSP/ALL_tsp/kroB200.tsp");
		//wczytajPlik("/home/jakub_s/AlgMeta/TSP/ALL_atsp/ft70.atsp");
		//wczytajPlik("/home/jakub_s/AlgMeta/TSP/ALL_tsp/gr120.tsp");
		//wczytajPlik("/home/jakub_s/AlgMeta/TSP/ALL_tsp/fri26.tsp"

		Macierz m;
		if (args.length < 1) {
			m = wczytajPlik("/home/jakub/pwr/meta/tsplib_ex/tsp/dsj1000.tsp");
		} else {
			m = wczytajPlik(args[0]);
		}

		//m.drukujMacierz();

		Rozwiazanie r = new Rozwiazanie(m.rozmiar);

		r.losoweRozwiazanie();
		//r.wyswietl();
		
		int f = m.funkcjaCelu(r);
		
		System.out.println("f(x) = " + f);
		
		System.out.println("PRD = " + m.PRD(r, 937));
		
		
	}
	
}
