import java.io.BufferedReader; 
import java.io.IOException; 
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.StringTokenizer; 

public class ProblemaB  
{ 
	private static Persona[] lista;
	private static int N; 
	
	private static ArrayList<ArrayList<Persona>> candidatos;
	private static Hashtable<Integer, Integer> isMinLex;
	private static Hashtable<Integer, Integer> isFirstL;
	
    public static void main(String[] args) throws IOException  
    { 
        BufferedReader reader =  new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        N = Integer.parseInt(reader.readLine());
        
        while (N != 0) {
        		// Crear arreglo a analizar
        	lista = new Persona[N];
        	for (int i = 0; i < N; i++) {
        		st = new StringTokenizer(reader.readLine());
        		lista[i] = new Persona(Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        	}
        	
        	Arrays.sort(lista, new ComparatorA());
        	
        	/* Print array
        	System.out.println(N);
        	for (Persona persona:lista) {
        		System.out.println(persona);
        	}
        	*/
        		// Initialize necessary data structures
        	candidatos = new ArrayList<ArrayList<Persona>>();
        	isMinLex = new Hashtable<Integer, Integer> ();
        	isFirstL = new Hashtable<Integer, Integer> ();
        	
        	for (Persona persAct : lista) {
        		int t = candidatos.size();
        			
        			// Si el elemento actual de b es menor a cualquier otra cabeza de los candidatos
        		if (candidatos.size() == 0 || persAct.b < candidatos.get(t-1).get(0).b ) {
        				// Agregar nueva lista candidata
        			candidatos.add( new ArrayList<Persona>() );
        			candidatos.get(0).add(persAct);
        			
        				// Actualizar estructuras
        			update(persAct, 1, t);
        		}
        		
        		int i = busquedaBinariaCols(persAct.b);
        		
        	}
        	
        		// Restart
        	N = Integer.parseInt(reader.readLine());
        }
        
    }
    
    private static int busquedaBinariaCols(int b) {
    	int lo = 0, hi = candidatos.size() - 1; 
        while (lo <= hi) { 
            int m = lo + (hi - lo) / 2; //
            int elemAct = candidatos.get(m).get(candidatos.get(m).size() - 1).b; 
            if (elemAct == b) 
                return m+1; // Maximo hay uno que es igual al que busco
  
            if (elemAct < b)  
                hi = m - 1;	// Por lo que las colas estan ordenadas desccendentemente
  
            else
                lo = m + 1; 
        } 
  
        // if we reach here, then element was 
        // not present 
        return lo;
	}

	private static int iMinLex(int l) {
    	if (isMinLex.containsKey(l)) {
    		return candidatos.size(); 
    	}
    	return isMinLex.get(l);
    }
    
    private static int iFirstL(int l) {
    	if (isFirstL.containsKey(l)) {
    		return candidatos.size(); 
    	}
    	return isFirstL.get(l);
    }
    
    private static void update(Persona persAct, int l, int t) {
    	if (iFirstL(l) == t+1) {
			isFirstL.put(l, t);
			isMinLex.put(l, t);
		} else {
			isMinLex.put(l, 
					persAct.index < isMinLex.get(l)? persAct.index : isMinLex.get(l) 
							);  
		}
    }
    
    public static class Persona {

        int index;
        int a;
        int b;

        Persona(int i, int a, int b) {
            index = i;
            this.a = a;
            this.b = b;
        }
        
        @Override
        public String toString() {
        	return "(" + index +", "+ a + ", "+ b + ")";
        }
    }
    
    public static class ComparatorLex implements Comparator<Persona> {
        @Override
        public int compare(Persona p1, Persona p2) {
            return p1.index - p2.index;
        }
    }
    
    public static class ComparatorA implements Comparator<Persona> {
        @Override
        public int compare(Persona p1, Persona p2) {
            return (p1.a - p2.a);
        }
    }
    
    public static class ComparatorB implements Comparator<Persona> {
        @Override
        public int compare(Persona p1, Persona p2) {
            return -(p1.b - p2.b);
        }
    }
}