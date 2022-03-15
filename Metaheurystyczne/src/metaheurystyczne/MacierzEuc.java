package metaheurystyczne;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class MacierzEuc {
	
	public int rozmiar;
	public double x_coordinates[];
	public double y_coordinates[];
	
	public MacierzEuc(int rozmiar) {
		this.rozmiar = rozmiar;
		x_coordinates = new double[rozmiar];
		y_coordinates = new double[rozmiar];
	}
	
	public void drukuj() {
		for(int i=0; i<=rozmiar-1; i++) {
			System.out.print(x_coordinates[i] + " " + y_coordinates[i] + "\n");
		}
	}
	
	public static MacierzEuc wczytajPlik(String nazwaPliku) {
		
		File plik = new File(nazwaPliku);
		
		try {
			
			Scanner scan = new Scanner(plik);
			
			int dimension;
			String linia = "";
			String[] splitLine;
			
			while(scan.hasNextLine() && !linia.startsWith("DIMENSION")) {
				linia = scan.nextLine();
			}
			
			splitLine = linia.split(" ");
			
			dimension = Integer.parseInt(splitLine[splitLine.length-1]);
			
			MacierzEuc m = new MacierzEuc(dimension);
			
			while(scan.hasNextLine() && !linia.startsWith("NODE_COORD_SECTION")) {
				linia = scan.nextLine();
			}
			
			for(int i=0; i<=dimension-1; i++) {
				linia = scan.nextLine();
				splitLine = linia.split(" ");
				splitLine = Test.deleteEmpty(splitLine);
				
				m.x_coordinates[i] = Double.parseDouble(splitLine[1]);
				m.y_coordinates[i] = Double.parseDouble(splitLine[2]);
				
				//System.out.println(x_coordinates[i] + " " + y_coordinates[i]);
			}
			
			return m;
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	public static void main(String args[]) {
		
		MacierzEuc m = wczytajPlik("/home/jakub_s/AlgMeta/TSP/ALL_tsp/d198.tsp");
		m.drukuj();
		
	}
	
}
