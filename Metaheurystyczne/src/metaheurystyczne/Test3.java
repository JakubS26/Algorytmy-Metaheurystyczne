package metaheurystyczne;

public class Test3 {
		
	public static void main(String args[]) {
		
		String nazwaPliku1 = "/home/jakub_s/AlgMeta/TSP/ALL_tsp/a280.tsp";
		String nazwaPliku2 = "/home/jakub_s/AlgMeta/TSP/ALL_tsp/d198.tsp";
		String nazwaPliku3 = "/home/jakub_s/AlgMeta/TSP/ALL_tsp/berlin52.tsp";
		String nazwaPliku4 = "/home/jakub_s/AlgMeta/TSP/ALL_tsp/pr144.tsp";
		
		//Macierz m = Macierz.randomEUC_2D_TSP(10);
		
		Euc2D e = Macierz.randomEUC_2D(10);
		
		//MacierzEuc meuc = MacierzEuc.wczytajPlik(nazwaPliku3);
		//Macierz m = Main.wczytajPlik(nazwaPliku3);
				
		Rozwiazanie r1 = Algorytmy.kRandom(e.m1, 100000);
		
		Rozwiazanie r2 = new Rozwiazanie(10);
		r2.losoweRozwiazanie();
				
		Rysowanie rys1 = new Rysowanie(e.m2, r1);
		Rysowanie rys2 = new Rysowanie(e.m2, r2);
		
	}
}
