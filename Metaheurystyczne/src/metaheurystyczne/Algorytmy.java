package metaheurystyczne;

public class Algorytmy {

	public static Rozwiazanie kRandom(Macierz m, int k) {
		
		
		Rozwiazanie r = new Rozwiazanie(m.rozmiar);
		Rozwiazanie obecneRozwiazanie = new Rozwiazanie(m.rozmiar);
		
		r.losoweRozwiazanie();
		
		int minFunkcjaCelu = m.funkcjaCelu(r);
		
		for(int i=1; i<=k-1; i++) {
			
			r.losoweRozwiazanie();
			
			if(m.funkcjaCelu(r) < minFunkcjaCelu) {
				
				obecneRozwiazanie.kopiuj(r);
				minFunkcjaCelu = m.funkcjaCelu(r);
				System.out.println(i + ".  " + minFunkcjaCelu);
				
			}
		}
		System.out.println("Koniec");
		return obecneRozwiazanie;
	}
	
}
