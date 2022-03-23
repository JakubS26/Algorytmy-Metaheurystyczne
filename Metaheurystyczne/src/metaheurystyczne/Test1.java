package metaheurystyczne;

public class Test1 {

	public static void main(String[] args) {
		
		Macierz m = Main.wczytajPlik("/home/jakub_s/AlgMeta/TSP/ALL_tsp/berlin52.tsp");		//Instancja typu EUC_2D
		//Macierz m = Main.wczytajPlik("/home/jakub_s/AlgMeta/TSP/ALL_tsp/kroC100.tsp");	    //Instancja typu EUC_2D
		//Macierz m = Main.wczytajPlik("/home/jakub_s/AlgMeta/TSP/ALL_atsp/ry48p.atsp");	    //Instancja typu ATSP - format FULL_MATRIX
		//Macierz m = Main.wczytajPlik("/home/jakub_s/AlgMeta/TSP/ALL_atsp/br17.atsp");			//Instancja typu ATSP - format FULL_MATRIX
		//Macierz m = Main.wczytajPlik("/home/jakub_s/AlgMeta/TSP/ALL_tsp/gr120.tsp");			//Instancja typu TSP symetryczny - format LOWER_DIAG_ROW
		//Macierz m = Main.wczytajPlik("/home/jakub_s/AlgMeta/TSP/ALL_tsp/swiss42.tsp");		//Instancja typu TSP symetryczny - format FULL_MATRIX
		
		//Macierz m = Macierz.randomATSP(50);
		//Macierz m = Macierz.randomSymmetricTSP(20);
		//Macierz m = Macierz.randomEUC_2D_TSP(70);
		
		m.drukujMacierz();
		
	}
	
}
