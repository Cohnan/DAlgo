/*
 * Sebastian Puerto
 * 201318518
 * Dalgo 20192
 */

import java.io.BufferedReader; 
import java.io.IOException; 
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class ProblemaC {
	
	private static String linea1;
	private static String linea2;
	private static int Max; // < 10^3 < 2x10^9 
	private static int N; // 3 <= N < 10^6 < 2x10^9
	private static int a;
	private static int b;
	private static int[] x;
	private static int[] y;
	private static int res;
	
	private static BufferedReader reader;
	private static StringTokenizer st;

	public static void main(String[] args) throws IOException  
    { 
        reader =  new BufferedReader(new InputStreamReader(System.in));
        
        String entrada1 = reader.readLine();
        
        while (!entrada1.contentEquals("0 0 0 0")) {
        		// Entender linea 1 de entrada
        	st = new StringTokenizer(entrada1);
        	Max = Integer.parseInt(st.nextToken());
        	N = Integer.parseInt(st.nextToken());
        	a = Integer.parseInt(st.nextToken());
        	b = Integer.parseInt(st.nextToken());
        	
        	if (Max < 1 || N < 3) break; // Por si acaso hay problema leyendo la linea de terminacion
        	
        		// Inicializar arreglos de vertices
        	x = new int[N+1];
        	y = new int[N+1];
        			
        		// Entender linea 2
        	st = new StringTokenizer(reader.readLine());
        	for (int i = 0; i < N; i++) {
        		x[i] = Integer.parseInt(st.nextToken());
        		y[i] = Integer.parseInt(st.nextToken());
        	}
        	x[N] = x[0];
        	y[N] = y[0];
        	
        		// Resolver el problema para los datos de entrada
        	resolver();
        	System.out.println(res);
        	
        	entrada1 = reader.readLine();
        }

	}
	
	private static int resolver() {
		int crossings = 0;
		int sgn;
		int R;
		res = 2;
		
		for (int i = 0; (N-i)*res != 0; i++) {
			if ( (y[i] <= b && y[i+1] >= b) || (y[i] >= b && y[i+1] <= b) ) {
					// Determinar si (a, b) esta en la arista
				if ((x[i] - a)*(y[i+1] - b) - (x[i+1] - a)*(y[i] - b) == 0) {
					res = 0;
					break;
				}
				
					// Contar crossings
				if ( (y[i] <= b && y[i+1] > b) || (y[i] > b && y[i+1] <= b) ) {
					R = y[i+1] - y[i];
					sgn = Integer.signum(R);
					if ( (a-x[i])* R * sgn < sgn * (b - y[i]) * (x[i+1] - x[i])) crossings +=1 ;
				}
			}
		}
		
		if (res != 0) {
			res = (crossings % 2)*2 - 1; 
		}
		
		return res;
	}
	
}
