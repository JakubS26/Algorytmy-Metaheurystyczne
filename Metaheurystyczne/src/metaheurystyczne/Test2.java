package metaheurystyczne;

public class Test2 {
	
	public static void main(String args[]) {
		
		Macierz m = Main.wczytajPlik("/home/jakub_s/AlgMeta/TSP/ALL_tsp/fri26.tsp");
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
		
		System.out.println("\n\n");
		
		r.wyswietl();
		
		int f = m.funkcjaCelu(r);
		
		System.out.println("f(x) = " + f);
		
		System.out.println("PRD = " + m.PRD(r, 937));
		

	}
	
}
