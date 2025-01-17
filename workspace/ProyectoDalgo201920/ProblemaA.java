/*
 * Sebastian Puerto
 * 201318518
 * Dalgo 20192
 */

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
	
	private static int[] longComPre;  // Cada entrada menor a N < 10^5 < 2x10^9 ~ 2^32
	
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
        		//System.out.println(arreglo[i]);
        	}
        	
        	// Hallar el Suffix Array
        	calcularSA();
        	
        	// Hallar el Longest Common Prefix Array usando el SA
        	calcularLCP();
        	
        	// Hallar el maximo del LCP
        	int res = longComPre[0];
        	
        	for (int l : longComPre) {
        		if (l > res) res = l;
        	} //FIN iteracion
        	
        	System.out.println(res);
        	
        	N = Integer.parseInt(reader.readLine());
        } // FIN Programa
  
    } // FIN Main
    
    private static void calcularSA() {
    	sufArr = new int[N][3];  
        
        // Inicializar suffix array con sus respectivos rangos
    	for (int i = 0; i < N; i++) {
    		sufArr[i] = new int[] {i, rankElIn(i), rankElIn(i+1)};
    	}
    	
    	// Ordenar suffix array respecto a primeros 2 elementos
    	Arrays.sort(sufArr, new ComparadorSuf());
    	//imprimirsufArrC();
    	
    	// Ordenar suffix array respecto a los primeros 2h elementos,
    	// dado que esta ordenado por los primeros h
    	invSA = new int[N];
    	for (int h = 4; h < 2*N; h *= 2) {
    		// Recordar pareja de ranks del actual para evaluar los nuevos ranks
    		// del siguiente sufijo
    		int pr1 = sufArr[0][1];
    		// Hacer primer rank como indicador de h/2-bucket, y
    		sufArr[0][1] = 0;
    		// Actualizar lista de inversas (dado indice original, donde esta el sufijo en sufArr?)
    		invSA[sufArr[0][0]] = 0;
    		
    		for (int i = 1; i < N; i++) {
    			
    			if (sufArr[i][1] == pr1 && sufArr[i][2] == sufArr[i-1][2]) { // Mismo h/2 bucket
    				//System.out.println("Entra " + (sufArr[i-1][1]) + i);
    				pr1 = sufArr[i][1];
    				sufArr[i][1] = sufArr[i-1][1]; // Hacer esto explicito en el primer rank
    			} else {
    				pr1 = sufArr[i][1];
    				sufArr[i][1] = sufArr[i-1][1] + 1;
    			}
    			
    			invSA[sufArr[i][0]] = i;
    		}
    		
    		int indDecis;
    		for (int i = 0; i < N; i++) {
    			// Actualizar segundo rank basado en el sufijo del sufijo actual
    			indDecis = (sufArr[i][0] + h/2);
    			sufArr[i][2] = indDecis < N? sufArr[invSA[indDecis]][1] : -1;
    		}
    		
    		// Ordenar para dejar suffix array en h-orden
    		Arrays.sort(sufArr, new ComparadorSuf());
    		//imprimirsufArrC();
    	} // FINNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNN
    	
    	//imprimirsufArrC();
    }
    
    private static void calcularLCP() {
    	longComPre = new int[N];
    	// Asegurarse de que el inverso sea un inverso completo
        for (int i = 0; i < N; i++) invSA[sufArr[i][0]] = i;
        
        for (int i = 0, l = 0; i < N; i++) {
          if (invSA[i] > 0) {
            int k = sufArr[invSA[i] - 1][0];
            while ((i + l < N) && (k + l < N) && arreglo[i + l] == arreglo[k + l]) l++;
            longComPre[invSA[i] - 1] = l;
            if (l > 0) l--;
          }
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
    
    private static void imprimirsufArrC() {
    	String res = "";
    	for (int[] el : sufArr) res += Arrays.toString(el) + " ";
    	System.out.println(res);
    }
}