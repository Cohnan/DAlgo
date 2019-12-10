import java.io.BufferedReader; 
import java.io.IOException; 
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class ProblemaA  
{ 
	private static int N;
	private static int[] arreglo; // Cada entrada menor a 10^6 < 2x10^9 ~ 2^32
	private static int[][] sufArr;  // (indIn, rank1, rank2) Cada entrada menor a < 10^6 < 2x10^9 ~ 2^32
	
	private static int[] longComPreArr;  // Cada entrada menor a N < 10^5 < 2x10^9 ~ 2^32
	
    public static void main(String[] args) throws IOException  
    { 
        BufferedReader reader =  new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st; 
        
        N = Integer.parseInt(reader.readLine());
        
        while (N != 0) {
        	// Leer arreglo de numeros
        	arreglo = new int[N];
        	st = new StringTokenizer(reader.readLine()); 
        	for (int i = 0; i < N; i++) {
        		arreglo[i] = Integer.parseInt(st.nextToken());
        		System.out.println(arreglo[i]);
        	}
        	
        	// Hallar el Suffix Array
        	calcularSA();
        	
        	// Hallar el Longest Common Prefix Array usando el SA
        	
        	// Hallar el maximo del LCP
        }
  
    }
    
    private static void calcularSA() {
    	sufArr = new int[N][3];  
        
    	
    }
        
    public static class ComparadorSuf implements Comparator<int[]> {

		@Override
		public int compare(int[] suf1, int[] suf2) {
			if (suf1[1] == suf2[1]) return suf1[2] - suf2[2];
			return suf1[1] - suf2[1];
		}
    	
    }
}