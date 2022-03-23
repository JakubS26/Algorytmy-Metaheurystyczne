package metaheurystyczne;

public class Test4 {
	
	public static void main(String args[]) {
		
		Macierz m = Main.wczytajPlik("/home/jakub_s/AlgMeta/TSP/ALL_atsp/br17.atsp");
		
		Rozwiazanie r = KRandom.solve(m, 10000000);
		
		r.wyswietl();
		
		int f = m.funkcjaCelu(r);
		
		System.out.println("f(x) = " + f);
		
		System.out.println("PRD = " + m.PRD(r, 39));
		
	}

}
