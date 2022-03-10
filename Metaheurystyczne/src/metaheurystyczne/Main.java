package metaheurystyczne;
import java.io.File; 
import java.io.FileNotFoundException;  
import java.util.Scanner; 

public class Main {
	
	public static Macierz wczytajPlik(String nazwaPliku) {
		
		File plik = new File(nazwaPliku);
		
		try {
			
			String linia = "";
			String type;
			String edge_weight;
			int dimension;
			
			//Przeszukujemy plik w poszukiwaniu informacji o typie problemu (TSP / ATSP)
			//Uwaga: zgdonie z informacją w dokumentacji, informacje mogą być w różnej kolejności ! (dlatego przeszukujemy plik trzy razy od nowa)
			
			Scanner scan = new Scanner(plik);
			
			while(scan.hasNextLine() && !linia.startsWith("TYPE")) {
				linia = scan.nextLine();
			}
			
			String splitLine[] = linia.split(" ");
			type = splitLine[splitLine.length-1];
			System.out.println(type);
			
			scan.close();
			
			//Przeszukujemy plik w poszukiwaniu informacji o rozmiarze problemu (liczbie wierzchołków grafu)
			
			scan = new Scanner(plik);
			
			while(scan.hasNextLine() && !linia.startsWith("DIMENSION")) {
				linia = scan.nextLine();
			}
			
			splitLine = linia.split(" ");
			
			dimension = Integer.parseInt(splitLine[splitLine.length-1]);
			System.out.println(dimension);
			
			scan.close();
			
			//Przeszukujemy plik w poszukiwaniu informacji o typie wag krawędzi (MATRIX/EUC2D)
			
			scan = new Scanner(plik);
			
			while(scan.hasNextLine() && !linia.startsWith("EDGE_WEIGHT_TYPE")) {
				linia = scan.nextLine();
			}
	
			splitLine = linia.split(" ");
			
			edge_weight = splitLine[splitLine.length-1];
			System.out.println(edge_weight);
			
			scan.close();
			
			//Tworzymy macierz i wczytujemy dane do tablicy dwuwymiarowej "odleglości"
			
			Macierz m = new Macierz(dimension);
			
			if(edge_weight.startsWith("EUC_2D")) {
				scan = new Scanner(plik);
				
				double x_coordinates[] = new double[dimension];
				double y_coordinates[] = new double[dimension];
				
				while(scan.hasNextLine() && !linia.startsWith("NODE_COORD_SECTION")) {
					linia = scan.nextLine();
				}
				
				for(int i=0; i<=dimension-1; i++) {
					linia = scan.nextLine();
					splitLine = linia.split(" ");
					splitLine = Test.deleteEmpty(splitLine);
					
					x_coordinates[i] = Double.parseDouble(splitLine[1]);
					y_coordinates[i] = Double.parseDouble(splitLine[2]);
					
					//System.out.println(x_coordinates[i] + " " + y_coordinates[i]);
				}
				
				for(int i=0; i<=dimension-1; i++) {
					for(int j=0; j<=dimension-1; j++) {
						
						double dx = x_coordinates[i] - x_coordinates[j];
						double dy = y_coordinates[i] - y_coordinates[j];
						
						m.odleglosci[i][j] = (int)Math.round(Math.sqrt(dx*dx+dy*dy));
					}
				}
				
				m.drukujMacierz();
				
				scan.close();
			}
			
			
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		
		return null;
		
	}
	
	public static void main(String args[]) {
		
		//wczytajPlik("/home/jakub_s/AlgMeta/TSP/berlin52.tsp");
		wczytajPlik("/home/jakub_s/AlgMeta/TSP/ALL_tsp/kroB200.tsp");
		
	}
	
}
