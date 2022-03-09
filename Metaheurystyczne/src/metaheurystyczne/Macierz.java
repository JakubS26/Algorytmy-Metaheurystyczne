package metaheurystyczne;

public class Macierz {
	
	public int rozmiar;
	public int odleglosci[][];
	
	public Macierz(int rozmiar) {
		this.rozmiar = rozmiar;
		odleglosci = new int[rozmiar][rozmiar];
	}
	
}
