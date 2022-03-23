package metaheurystyczne;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Obsluga {
	
	public static void main(String args[]) throws IOException {
		
		int wybor;
		String linia;
		
		int rozmiar;
		int ilosc;
		
		Macierz M = null;
		Euc2D E = null;
		
		boolean euc = false;
		
		int opt = -1;
		
		Rozwiazanie R = null;
		
		Scanner scan = new Scanner(System.in);
		
		System.out.println("Witamy!\n");
		System.out.println("Jaką instancję problemu komiwojażera chcesz rozwiązać?");
		System.out.println("1. Instancję z biblioteki TSPLIB");
		System.out.println("2. Losową instancję\n");
		
		linia = scan.next();
		wybor = Integer.parseInt(linia);
		
		if(wybor == 1) {
			System.out.println("\nPodaj nazwę pliku z instancją\n");
			linia = scan.next();
			String nazwa = linia;
			
			if(linia.endsWith(".atsp")) {
				M = Main.wczytajPlik("/home/jakub_s/AlgMeta/TSP/ALL_atsp/" + linia);
			} else if(linia.endsWith(".tsp")) {
				M = Main.wczytajPlik("/home/jakub_s/AlgMeta/TSP/ALL_tsp/" + linia);
				
				File plik = new File("/home/jakub_s/AlgMeta/TSP/ALL_tsp/" + linia);
				Scanner s = new Scanner(plik);
				
				while(s.hasNextLine() && !linia.startsWith("EDGE_WEIGHT_TYPE")) {
					linia = s.nextLine();
				}
		
				String[] splitLine = linia.split(" ");
				
				String edge_weight = splitLine[splitLine.length-1];
				
				if(edge_weight.startsWith("EUC_2D")) {
					euc = true;
					E = new Euc2D(M.rozmiar);
					E.m1 = M;
					E.m2 = MacierzEuc.wczytajPlik("/home/jakub_s/AlgMeta/TSP/ALL_tsp/" + nazwa);
				}
				s.close();
			}
			
			System.out.println("\nJeśli znasz, podaj wartośc funkcji celu dla optymalnego rozwiązania\n");
			linia = scan.next();
			
			opt = Integer.parseInt(linia);
		
			
		} else if(wybor == 2) {
			System.out.println("\nJaką instancję chcesz wylosować?");
			System.out.println("1. Zwykły problem komiwojażera");
			System.out.println("2. Euklidesowy problem komiwojażera");
			System.out.println("3. Asymetryczny problem komiwojażera\n");
			
			linia = scan.next();
			wybor = Integer.parseInt(linia);
			
			System.out.println("\nJaki ma być rozmiar problemu?");
			linia = scan.next();
			rozmiar = Integer.parseInt(linia);
			
			if(wybor == 1) {
				M = Macierz.randomSymmetricTSP(rozmiar);
			} else if(wybor == 2) {
				euc = true;
				E = Macierz.randomEUC_2D(rozmiar);
				M = E.m1;
			} else if(wybor == 3) {
				M = Macierz.randomATSP(rozmiar);
			}
			
		}
		
		
		System.out.println("\nJakiego algorytmu chcesz użyć do rozwiązania problemu?");
		System.out.println("1. k-random");
		System.out.println("2. Algorytmu najbliższego sąsiada");
		System.out.println("3. Rozszerzonego algorytmu najbliższego sąsiada");
		System.out.println("4. 2-OPT\n");
		
		linia = scan.next();
		wybor = Integer.parseInt(linia);
		
		if(wybor == 1) {
			System.out.println("\nIle rozwiązań chcesz wylosować w metodzie k-random?");
			
			linia = scan.next();
			ilosc = Integer.parseInt(linia);
			
			R = KRandom.solve(M, ilosc);
			
		} else if(wybor == 2) {
			
			NSasiad ns = new NSasiad(M);
			R = ns.rozwiazRandomStart();
			
		} else if(wybor == 3) {
			NSasiad ns = new NSasiad(M);
			R = ns.rozwiazStartNiezalenie();
			
		} else if(wybor == 4) {
			
			R = TwoOpt.solve(M);
			
		}
		
		if(M.rozmiar <= 100) {
			M.drukujMacierz();
		}
		
		System.out.println("\nKolejność odwiedzania miast według rozwiązania:");
		R.wyswietl();
		
		System.out.println("\n Funkcja celu dla rozwiązania: f(x) = " + M.funkcjaCelu(R) + "\n");
		
		if(opt != -1) {
			System.out.println("\nPRD = " + M.PRD(R, opt));
		}
		
		if(euc) {
			Rysowanie rys = new Rysowanie(E.m2, R);
		}
				

	}
	
}
