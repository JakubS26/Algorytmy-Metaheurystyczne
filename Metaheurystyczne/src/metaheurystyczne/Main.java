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
			String edge_format = "";
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
			
			//Przeszukujemy plik w poszukiwaniu informacji o formacie instancji (FULL_MATRIX / LOWER_DIAG_ROW)
			
			
			if(edge_weight.startsWith("EXPLICIT")) {
				
				scan = new Scanner(plik);
				
				while(scan.hasNextLine() && !linia.startsWith("EDGE_WEIGHT_FORMAT")) {
					linia = scan.nextLine();
				}
		
				splitLine = linia.split(" ");
				
				edge_format = splitLine[splitLine.length-1];
				System.out.println(edge_format);
				
				scan.close();
				
			}
			
			
			//Tworzymy macierz i wczytujemy dane do tablicy dwuwymiarowej "odleglości"
			
			
			
			if(edge_weight.startsWith("EUC_2D")) {
				scan = new Scanner(plik);
				Macierz m = new Macierz(dimension);
				
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
				
				//m.drukujMacierz();
				scan.close();
				return m;
				
			} else if(type.startsWith("ATSP") || (type.startsWith("TSP") && edge_format.startsWith("FULL_MATRIX"))) {
				
				scan = new Scanner(plik);
				Macierz m1 = new Macierz(dimension);
				
				while(scan.hasNextLine() && !linia.startsWith("EDGE_WEIGHT_SECTION")) {
					linia = scan.nextLine();
				}
				
				int number = dimension*dimension;
				
				int weights[] = new int[number];
				
				int c = 0;
				
				while(c <= number-1) {
					
					linia = scan.nextLine();
					
					splitLine = linia.split(" ");
					splitLine = Test.deleteEmpty(splitLine);
					
					for(int k=0; k<=splitLine.length-1; k++) {
						weights[c] = Integer.parseInt(splitLine[k]);
						c++;
					}
						
				}
				
				for(int i=0; i<=dimension-1; i++) {
					for(int j=0; j<=dimension-1; j++) {
						m1.odleglosci[i][j] = weights[i*dimension+j];
					}
				}
				
				//m1.drukujMacierz();
				scan.close();
				return m1;
				
			} else if(type.startsWith("TSP") && edge_format.startsWith("LOWER_DIAG_ROW")) {
				
				scan = new Scanner(plik);
				Macierz m2 = new Macierz(dimension);
				
				while(scan.hasNextLine() && !linia.startsWith("EDGE_WEIGHT_SECTION")) {
					linia = scan.nextLine();
				}
				
				int number = dimension*(dimension+1)/2;
				
				int weights[] = new int[number];
				
				int c = 0;
				
				while(c <= number-1) {
					
					linia = scan.nextLine();
					
					splitLine = linia.split(" ");
					splitLine = Test.deleteEmpty(splitLine);
					
					for(int k=0; k<=splitLine.length-1; k++) {
						weights[c] = Integer.parseInt(splitLine[k]);
						c++;
					}
						
				}
				
				for(int i=0; i<=dimension-1; i++) {
					for(int j=0; j<=i; j++) {
						m2.odleglosci[i][j] = weights[i*(i+1)/2+j];
					}
				}
				
				for(int i=0; i<=dimension-1; i++) {
					for(int j=i+1; j<=dimension-1; j++) {
						m2.odleglosci[i][j] = m2.odleglosci[j][i];
					}
				}
				
				//m2.drukujMacierz();
				scan.close();
				return m2;
				
			}
			
			
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		
		return null;
		
	}
	
	public static void main(String args[]) {
		
		//wczytajPlik("/home/jakub_s/AlgMeta/TSP/berlin52.tsp");
		//wczytajPlik("/home/jakub_s/AlgMeta/TSP/ALL_tsp/kroB200.tsp");
		//wczytajPlik("/home/jakub_s/AlgMeta/TSP/ALL_atsp/ft70.atsp");
		//wczytajPlik("/home/jakub_s/AlgMeta/TSP/ALL_tsp/gr120.tsp");
		
		Macierz m = wczytajPlik("/home/jakub_s/AlgMeta/TSP/ALL_tsp/fri26.tsp");
		m.drukujMacierz();
		
		Rozwiazanie r = new Rozwiazanie(26);
		
		r.wierzcholki[0] = 1;
		r.wierzcholki[1] = 25;
		r.wierzcholki[2] = 24;
		r.wierzcholki[3] = 23;
		r.wierzcholki[4] = 26;
		r.wierzcholki[5] = 22;
		r.wierzcholki[6] = 21;
		r.wierzcholki[7] = 17;
		r.wierzcholki[8] = 18;
		r.wierzcholki[9] = 20;
		r.wierzcholki[10] = 19;
		r.wierzcholki[11] = 16;
		r.wierzcholki[12] = 11;
		r.wierzcholki[13] = 12;
		r.wierzcholki[14] = 13;
		r.wierzcholki[15] = 15;
		r.wierzcholki[16] = 14;
		r.wierzcholki[17] = 10;
		r.wierzcholki[18] = 9;
		r.wierzcholki[19] = 8;
		r.wierzcholki[20] = 7;
		r.wierzcholki[21] = 5;
		r.wierzcholki[22] = 6;
		r.wierzcholki[23] = 4;
		r.wierzcholki[24] = 3;
		r.wierzcholki[25] = 2;
		
		r.wyswietl();
		
		int f = m.funkcjaCelu(r);
		
		System.out.println("f(x) = " + f);
		
		System.out.println("PRD = " + m.PRD(r, 937));
		
		
	}
	
}
