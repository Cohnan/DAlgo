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
	private static int[] xs;
	private static int[] ys;
	
	private static BufferedReader reader;
	private static StringTokenizer st;

	public static void main(String[] args) throws IOException  
    { 
        reader =  new BufferedReader(new InputStreamReader(System.in));
        
        String entrada1 = reader.readLine();
        
        while (!entrada1.contentEquals("0 0 0 0")) {
        		// Entender linea 1
        	st = new StringTokenizer(entrada1);
        	Max = Integer.parseInt(st.nextToken());
        	N = Integer.parseInt(st.nextToken());
        	a = Integer.parseInt(st.nextToken());
        	b = Integer.parseInt(st.nextToken());
        	
        		// Inicializar arreglos de vertices
        	xs = new int[N];
        	ys = new int[N];
        			
        		// Entender linea 2
        	for (int i = 0; i < N; i++) {
        		st = new StringTokenizer(reader.readLine());
        		xs[i] = Integer.parseInt(st.nextToken());
        		ys[i] = Integer.parseInt(st.nextToken());
        	}
        }

	}
	
	private static void leerLinea1() {
		
	}
	
	private static void leerLinea2() {
		
	}

}
