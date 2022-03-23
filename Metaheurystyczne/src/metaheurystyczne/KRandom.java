package metaheurystyczne;

public class KRandom {
	
	public static Rozwiazanie solve(Macierz m, int k) {
		
		
		Rozwiazanie r = new Rozwiazanie(m.rozmiar);
		Rozwiazanie obecneRozwiazanie = new Rozwiazanie(m.rozmiar);
		
		r.losoweRozwiazanie();
		obecneRozwiazanie.kopiuj(r);
		
		int minFunkcjaCelu = m.funkcjaCelu(r);
		
		for(int i=1; i<=k-1; i++) {
			
			r.losoweRozwiazanie();
			
			if(m.funkcjaCelu(r) < minFunkcjaCelu) {
				
				obecneRozwiazanie.kopiuj(r);
				minFunkcjaCelu = m.funkcjaCelu(r);
				//System.out.println(i + ".  " + minFunkcjaCelu);
				
			}
		}
		//System.out.println("Koniec");
		return obecneRozwiazanie;
	}
	
	/*public static void main(String args[]) {
		
		Rozwiazanie R = new Rozwiazanie(3);
		R.losoweRozwiazanie();
		R.wyswietl();
		
	}*/
	
}
