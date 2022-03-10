package metaheurystyczne;

public class Test {
	
	public static String[] deleteEmpty(String[] s) {
		
		int size = 0;
		
		for(int i=0; i<=s.length-1; i++) {
			if(s[i] != "")
				size++;
		}
		
		String arr[] = new String[size];
		
		int j = 0;
		int k = 0;
		
		while(k <= s.length-1) {
			if(s[k] != "") {
				arr[j] = s[k];
				j++;
			}
			k++;
		}
		
		return arr;
	}
	
	public static void main(String[] args) {
		
		String s1 = "  1  2 3";
		String[] arr = s1.split(" ");
		
		arr = deleteEmpty(arr);
		
		for(int i=0; i<=arr.length-1; i++) {
			System.out.println("String " + i + ": " + arr[i]);
		}
		
	}
	
}
