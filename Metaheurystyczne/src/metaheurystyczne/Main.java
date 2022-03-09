package metaheurystyczne;
import java.io.File; 
import java.io.FileNotFoundException;  
import java.util.Scanner; 

public class Main {
	
	public static Macierz wczytajPlik(String nazwaPliku) {
		
		File plik = new File(nazwaPliku);
		
		try {
			Scanner scan = new Scanner(plik);
			while(scan.hasNextLine()) {
				String linia = scan.nextLine();
				System.out.println(linia);
			}
			scan.close();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		
		return null;
		
	}
	
	public static void main(String args[]) {
		
		wczytajPlik("/home/jakub_s/Pobrane/berlin52.tsp");
		
	}
	
}
