package metaheurystyczne;

import java.util.Random;

public class Rozwiazanie {
	
	public int rozmiar;
	public int[] wierzcholki;
	
	public Rozwiazanie(int rozmiar) {
		this.rozmiar = rozmiar;
		this.wierzcholki = new int[rozmiar];
	}
	
	private void potasuj(int[] tab) {
		
		Random r = new Random();
		int n;
		int c;
		
		for(int i=0; i<=tab.length-1; i++) {
			n = r.nextInt(tab.length-i)+i;
			
			c = tab[i];
			tab[i] = tab[n];
			tab[n] = c;
		}
		
	}
	
	public void kopiuj(Rozwiazanie r) {
		
		for(int i=0; i<=this.rozmiar-1; i++) {
			this.wierzcholki[i] = r.wierzcholki[i];
		}
		
	}
	
	public void losoweRozwiazanie() {
		
		for(int i=0; i<=rozmiar-1; i++) {
			wierzcholki[i] = i+1;
		}
		
		potasuj(wierzcholki);
		
	}
	
	public void wyswietl() {
		
		for(int i=0; i<=rozmiar-1; i++) {
			System.out.print(wierzcholki[i] + " -> ");	
		}
		System.out.println(wierzcholki[0]);
		
	}
		
}
