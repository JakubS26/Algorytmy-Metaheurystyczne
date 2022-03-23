package metaheurystyczne;

public class TwoOpt {
	
	private static void invert(int[] tab, int i, int j) {
		
		int len = j-i+1;
		int[] temp = new int[len];
		
		for(int k=0; k<=len-1; k++) {
			
			temp[k] = tab[k+i];
			
		}
		
		for(int k=0; k<=len-1; k++) {
			tab[i+k] = temp[len-1-k];
		}		
	}
	
	public static Rozwiazanie solve (Macierz m) {
		
		Rozwiazanie obecneRozwiazanie = new Rozwiazanie(m.rozmiar);
		Rozwiazanie posrednieRozwiazanie = new Rozwiazanie(m.rozmiar);
		Rozwiazanie r = new Rozwiazanie(m.rozmiar);
		
		obecneRozwiazanie.losoweRozwiazanie();
		posrednieRozwiazanie.kopiuj(obecneRozwiazanie);
		r.kopiuj(obecneRozwiazanie);
		
		while(true) {
			
			//posrednieRozwiazanie.kopiuj(obecneRozwiazanie);
			
			for(int i=0; i<=m.rozmiar-1; i++) {
				for(int j=0; j<=i; j++) {
					
					r.kopiuj(obecneRozwiazanie);
					invert(r.wierzcholki, j, i);
					
					if(m.funkcjaCelu(r) < m.funkcjaCelu(posrednieRozwiazanie)) {
						posrednieRozwiazanie.kopiuj(r);
						//System.out.println(m.funkcjaCelu(r));
					} 
					
				}
			}
			
			if(m.funkcjaCelu(obecneRozwiazanie) == m.funkcjaCelu(posrednieRozwiazanie)) {
				return obecneRozwiazanie; 
			}

			obecneRozwiazanie.kopiuj(posrednieRozwiazanie);
							
		}
		
	}
	
	public static void main(String args[]) {
		
		//Macierz m = Main.wczytajPlik("/home/jakub_s/AlgMeta/TSP/ALL_tsp/fri26.tsp");
		Macierz m = Main.wczytajPlik("/home/jakub_s/AlgMeta/TSP/ALL_tsp/a280.tsp");
		m.drukujMacierz();
		
		Rozwiazanie r = solve(m);
		r.wyswietl();
		System.out.println("\n\n" + m.funkcjaCelu(r));
		System.out.println("Koniec");
		
	}
	
}
