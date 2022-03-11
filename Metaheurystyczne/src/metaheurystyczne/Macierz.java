package metaheurystyczne;

import java.util.Random;

public class Macierz {
	
	public int rozmiar;
	public int odleglosci[][];
	
	public Macierz(int rozmiar) {
		this.rozmiar = rozmiar;
		odleglosci = new int[rozmiar][rozmiar];
	}
	
	public static Macierz randomATSP(int rozmiar) {
		
		Macierz m = new Macierz(rozmiar);
		Random r = new Random();
		
		for(int i=0; i<=rozmiar-1; i++) {
			for(int j=0; j<=rozmiar-1; j++) {
				
				if(i != j) {
					m.odleglosci[i][j] = r.nextInt(1000)+1;
				} else {
					m.odleglosci[i][j] = 0;
				}
			}
			
		}
		
		return m;
	}
	
	public static Macierz randomSymmetricTSP(int rozmiar) {
		
		Macierz m = new Macierz(rozmiar);
		Random r = new Random();
		
		for(int i=0; i<=rozmiar-1; i++) {
			for(int j=0; j<=i; j++) {
				
				if(i != j) {
					int l = r.nextInt(1000)+1;
					m.odleglosci[i][j] = l;
					m.odleglosci[j][i] = l;
				} else {
					m.odleglosci[i][j] = 0;
				}
			}
			
		}
		
		return m;
	}
	
	public static Macierz randomEUC_2D_TSP(int rozmiar) {
		
		Macierz m = new Macierz(rozmiar);
		Random r = new Random();
		
		int x_coordinates[] = new int[rozmiar];
		int y_coordinates[] = new int[rozmiar];
		
		for(int i=0; i<=rozmiar-1; i++) {
			x_coordinates[i] = r.nextInt(1000);
			y_coordinates[i] = r.nextInt(1000);
		}
		
		double dx, dy;
		
		for(int i=0; i<=rozmiar-1; i++) {
			for(int j=0; j<=i; j++) {
					
				dx = x_coordinates[i] - x_coordinates[j];
				dy = y_coordinates[i] - y_coordinates[j];
					
				int l = (int)Math.round(Math.sqrt(dx*dx+dy*dy));
				m.odleglosci[i][j] = l;
				m.odleglosci[j][i] = l;
				
			}
			
		}
		
		return m;
	}
	
	public int funkcjaCelu(Rozwiazanie r) {
		
		int suma = 0;
		
		for(int i=0; i<=rozmiar-1; i++) {
			suma = suma + odleglosci[r.wierzcholki[i]-1][r.wierzcholki[(i+1)%rozmiar]-1];
		}
		
		return suma;
	}
	
	public double PRD(Rozwiazanie r, int opt) {
		
		return 100*(double)(funkcjaCelu(r)-opt)/(double)opt;
	}
	
	public void drukujMacierz() {
		
		for(int i=0; i<=rozmiar-1; i++) {
			for(int j=0; j<=rozmiar-1; j++) {
				System.out.print(String.format("%5d ", odleglosci[i][j]));
			}
			System.out.println("");
		}
		
	}
	
}
