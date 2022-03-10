package metaheurystyczne;

public class Macierz {
	
	public int rozmiar;
	public int odleglosci[][];
	
	public Macierz(int rozmiar) {
		this.rozmiar = rozmiar;
		odleglosci = new int[rozmiar][rozmiar];
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
