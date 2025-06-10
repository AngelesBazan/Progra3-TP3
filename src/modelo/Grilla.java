package modelo;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Grilla {

    private int n;
    private int m;
    private int[][] matriz;
    private long tiempoSinPoda;
    private long tiempoConPoda;
	private boolean caminoEncontradoSinPoda = false;
	private boolean caminoEncontrado = false;


    public int llamadasSinPoda;
    public int llamadasConPoda;
    public List<List<Coordenada>> caminosValidos = new ArrayList<>();

    public Grilla() {
    	n=numeroRandom();
    	m=numeroRandom();
        matriz = new int[n][m];
        crearMatriz();
    }

    private void crearMatriz() {
    	Random random = new Random();
        for (int f = 0; f < n; f++) {
        	
            for (int c = 0; c < m; c++) {
            	int numero = random.nextBoolean() ? 1 : -1;

                matriz[f][c] = numero;
            }
        }
    }
	private void backtrackSinPoda(int x, int y, int suma, List<Coordenada> camino) {
	    if (caminoEncontradoSinPoda) {
	    	return;  
	    }
	    llamadasSinPoda++;
	    if (x >= n || y >= m) return;
	    suma += matriz[x][y];
	    camino.add(new Coordenada(x, y));

	    if (x == n - 1 && y == m - 1) {
	        if (suma == 0) {
	            caminosValidos.add(new ArrayList<>(camino));
	            caminoEncontradoSinPoda = true; 
	        }
	    } else {
	        backtrackSinPoda(x + 1, y, suma, camino);
	        backtrackSinPoda(x, y + 1, suma, camino);
	    }

	    camino.remove(camino.size() - 1);
	}


	private void backtrackConPoda(int x, int y, int suma, List<Coordenada> camino) {
	    if (caminoEncontrado) {
	    	return; 
	    }
	
	    llamadasConPoda++;
	
	    if (x >= n || y >= m) {
	    	return;
	    }
	
	    suma += matriz[x][y];
	    camino.add(new Coordenada(x, y));
	
	    int pasosRestantes = (n - 1 - x) + (m - 1 - y);
	    if (Math.abs(suma) > pasosRestantes) {
	        camino.remove(camino.size() - 1);
	        return;
	    }
	
	    if (x == n - 1 && y == m - 1) {
	        if (suma == 0) {
	            caminosValidos.add(new ArrayList<>(camino));
	            caminoEncontrado = true;  // Marcar que ya encontramos uno
	        }
	    } else {
	        backtrackConPoda(x + 1, y, suma, camino);
	        backtrackConPoda(x, y + 1, suma, camino);
	    }
	
	    camino.remove(camino.size() - 1);
	}

	private int numeroRandom() {
	    Random random = new Random();
	    int numeroRandom=random.nextInt(9)+2;
	    return numeroRandom;
	
	}


    public int getLlamadasSinPoda() {
		return llamadasSinPoda;
	}

	public int getLlamadasConPoda() {
		return llamadasConPoda;
	}

	public int getN() {
		return n;
	}

	public int getM() {
		return m;
	}

	public boolean buscarCaminosValidosSinPoda() {
        if ((n + m - 1) % 2 != 0) return false; // cantidad de celdas debe ser par

        llamadasSinPoda = 0;
        caminoEncontradoSinPoda=false;
        caminosValidos.clear();
        long inicio = System.currentTimeMillis();
        backtrackSinPoda(0, 0, 0, new ArrayList<>());
        long fin = System.currentTimeMillis();

        tiempoSinPoda=fin-inicio;

        return !caminosValidos.isEmpty();
    }

    public long getTiempoSinPoda() {
		return tiempoSinPoda;
	}

	public long getTiempoConPoda() {
		return tiempoConPoda;
	}

	public boolean buscarCaminosValidosConPoda() {
        if ((n + m - 1) % 2 != 0) {
        	return false;
        }

        llamadasConPoda = 0;
        caminosValidos.clear();
        long inicio = System.currentTimeMillis();
        backtrackConPoda(0, 0, 0, new ArrayList<>());
        long fin = System.currentTimeMillis();
        tiempoConPoda=fin-inicio;

        return caminosValidos.size()>00;
    }



    public List<List<Coordenada>> getCaminosValidos() {
        return caminosValidos;
    }

    public void imprimirMatriz() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                System.out.print(matriz[i][j] + " ");
            }
            System.out.println();
        }
    }
    

    
   public int getElemnto(int x, int y) {
	   return matriz[x][y];
   }
   
  
}
