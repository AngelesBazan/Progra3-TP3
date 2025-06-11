package modelo;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Grilla {

	private int filas;
	private int columnas;
	private int[][] matriz;
	private double tiempoSinPoda;
	private double tiempoConPoda;
	private boolean caminoEncontradoSinPoda = false;
	private boolean caminoEncontrado = false;

	public int llamadasSinPoda;
	public int llamadasConPoda;
	
	public List<List<Coordenada>> caminosValidos = new ArrayList<>();

	public Grilla() {
		filas = numeroRandom();
		columnas = numeroRandom();
		matriz = new int[filas][columnas];
		crearMatriz();
	}

	private void crearMatriz() {
		Random random = new Random();
		for (int f = 0; f < filas; f++) {
			for (int c = 0; c < columnas; c++) {
				int numero = random.nextBoolean() ? 1 : -1;
				matriz[f][c] = numero;
			}
		}
	}

	private void backtrackSinPoda(int filaAcutal, int columnaActual, int suma, List<Coordenada> camino) {
		if (caminoEncontradoSinPoda) {
			return;
		}
		llamadasSinPoda++;
		if (filaAcutal >= filas || columnaActual >= columnas)
			return;
		suma += matriz[filaAcutal][columnaActual];
		camino.add(new Coordenada(filaAcutal, columnaActual));

		if (filaAcutal == filas - 1 && columnaActual == columnas - 1) {
			if (suma == 0) {
				caminosValidos.add(new ArrayList<>(camino));
				caminoEncontradoSinPoda = true;
			}
		} else {
			backtrackSinPoda(filaAcutal + 1, columnaActual, suma, camino);
			backtrackSinPoda(filaAcutal, columnaActual + 1, suma, camino);
		}

		camino.remove(camino.size() - 1);
	}

	private void backtrackConPoda(int filaAcutal, int columnaActual, int suma, List<Coordenada> camino) {
		if (caminoEncontrado) {
			return;
		}
		llamadasConPoda++;

		boolean esLimiteX = filaAcutal >= filas;
		boolean esLimiteY = columnaActual >= columnas;
		if (esLimiteX || esLimiteY) {
			return;
		}

		suma += matriz[filaAcutal][columnaActual];
		camino.add(new Coordenada(filaAcutal, columnaActual));

		int pasosRestantes = (filas - 1 - filaAcutal) + (columnas - 1 - columnaActual);
		if (Math.abs(suma) > pasosRestantes) {
			camino.remove(camino.size() - 1);
			return;
		}

		if (filaAcutal == filas - 1 && columnaActual == columnas - 1) {
			if (suma == 0) {
				caminosValidos.add(new ArrayList<>(camino));
				caminoEncontrado = true;
			}
		} else {
			backtrackConPoda(filaAcutal + 1, columnaActual, suma, camino);
			backtrackConPoda(filaAcutal, columnaActual + 1, suma, camino);
		}
		camino.remove(camino.size() - 1);
	}

	private int numeroRandom() {
		Random random = new Random();
		int numeroRandom = random.nextInt(9) + 2;
		return numeroRandom;
	}

	public int getLlamadasSinPoda() {
		return llamadasSinPoda;
	}

	public int getLlamadasConPoda() {
		return llamadasConPoda;
	}

	public int getFilas() {
		return filas;
	}

	public int getColumnas() {
		return columnas;
	}

	public boolean buscarCaminosValidosSinPoda() {
		if ((filas + columnas - 1) % 2 != 0)
			return false;
		llamadasSinPoda = 0;
		caminoEncontradoSinPoda = false;
		caminosValidos.clear();
		double inicio = System.nanoTime();
		backtrackSinPoda(0, 0, 0, new ArrayList<>());
		double fin = System.nanoTime();
		tiempoSinPoda = (fin - inicio) / 1000000;
		
		System.out.print("Tiempo sin poda(ms): " +tiempoSinPoda); 

		return !caminosValidos.isEmpty();
	}

	public double getTiempoSinPoda() {
		return tiempoSinPoda;
	}

	public double getTiempoConPoda() {
		return tiempoConPoda;
	}

	public boolean buscarCaminosValidosConPoda() {
		if ((filas + columnas - 1) % 2 != 0) {
			return false;
		}

		llamadasConPoda = 0;
		caminosValidos.clear();
		long inicio = System.currentTimeMillis();
		backtrackConPoda(0, 0, 0, new ArrayList<>());
		long fin = System.currentTimeMillis();
		tiempoConPoda = fin - inicio;
		return caminosValidos.size() > 00;
	}

	public List<List<Coordenada>> getCaminosValidos() {
		return caminosValidos;
	}

	public void imprimirMatriz() {
		for (int i = 0; i < filas; i++) {
			for (int j = 0; j < columnas; j++) {
				System.out.print(matriz[i][j] + " ");
			}
			System.out.println();
		}
	}

	public int getElemnto(int x, int y) {
		return matriz[x][y];
	}

}
