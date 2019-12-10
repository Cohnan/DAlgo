import java.io.BufferedReader; 
import java.io.IOException; 
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class ProblemaA  
{ 
	private static int N;
	private static int[] arreglo; // 0 <= cada entrada < 10^6 < 2x10^9 ~ 2^32
	private static int[][] sufArr;  // (indIn, rank1, rank2) Cada entrada menor a < 10^6 < 2x10^9 ~ 2^32
	private static int[] invSA; // Dado indice original, donde se encuentra ese sufijo en sufArr
	
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
        
        // Inicializar suffix array con sus respectivos rangos
    	for (int i = 0; i < N; i++) {
    		sufArr[i] = new int[] {i, rankElIn(i+1), rankElIn(i+2)};
    	}
    	
    	// Ordenar suffix array respecto a primeros 2 elementos
    	Arrays.sort(sufArr, new ComparadorSuf());
    	
    	// Ordenar suffix array respecto a los primeros 2h elementos,
    	// dado que esta ordenado por los primeros h
    	invSA = new int[N];
    	for (int h = 4; h < 2*N; h *= 2) {
    		// Recordar pareja de ranks del actual para evaluar los nuevos ranks
    		// del siguiente sufijo
    		int pr1 = sufArr[0][1];
    		int pr2 = sufArr[0][2];
    		// Hacer primer rank como indicador de h/2-bucket, y
    		sufArr[0][1] = 0;
    		sufArr[0][2] = rankElIn(0 + h/2);
    		// Actualizar lista de inversas (dado indice original, donde esta el sufijo en sufArr?)
    		invSA[sufArr[0][0]] = 0;
    		
    		for (int i = 1; i < N; i++) {
    			pr1 = sufArr[i][1];
    			pr2 = sufArr[i][2]; // Si no modificara aun los segundos ranks, que no es necesario, podria evitar esta variable y esta linea
    			
    			if (sufArr[i][1] == pr1 && sufArr[i][2] == pr2) { // Mismo h/2 bucket
    				sufArr[i][1] = sufArr[i-1][1]; // Hacer esto explicito en el primer rank
    			} else {
    				sufArr[i][1] = sufArr[i-1][1] + 1;
    			}
    			
    			// Actualizar segundo rank y lista de inversas
    			sufArr[i][2] = rankElIn(i+2);
    			invSA[sufArr[i][0]] = i;
    		}
    		
    		// Ordenar
    		Arrays.sort(sufArr, new ComparadorSuf());
    	}
    	
    }
        
    public static class ComparadorSuf implements Comparator<int[]> {

		@Override
		public int compare(int[] suf1, int[] suf2) {
			if (suf1[1] == suf2[1]) return suf1[2] - suf2[2];
			return suf1[1] - suf2[1];
		}
    	
    }
    
    private static int rankElIn(int i) {
    	if (i < N) return arreglo[i]; 
    	return -1; // Cada numero del arreglo es no negativo
    }
}