import java.io.BufferedReader; 
import java.io.IOException; 
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.StringTokenizer; 

public class ProblemaB  
{ 
	private static Persona[] lista;
	private static int N; 
	
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
        	
        	
        	N = Integer.parseInt(reader.readLine());
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
            return p1.a - p2.a;
        }
    }
    
    public static class ComparatorB implements Comparator<Persona> {
        @Override
        public int compare(Persona p1, Persona p2) {
            return -(p1.b - p2.b);
        }
    }
}
