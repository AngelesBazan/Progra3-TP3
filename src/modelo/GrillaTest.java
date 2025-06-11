package modelo;

import modelo.Grilla;
import modelo.Coordenada;

import static org.junit.Assert.*;
import org.junit.Test;

import java.util.List;

public class GrillaTest {
	// Test que verifica que no hay camino válido cuando no existe sin poda
    @Test
    public void testSinCaminoValidoSinPoda() {
        int[][] matriz = {
            {1, 1},
            {1, 1}
        };
        Grilla grilla = new Grilla(matriz);

        boolean hayCamino = grilla.buscarCaminosValidosSinPoda();

        assertFalse("No debe encontrar camino válido sin poda", hayCamino);
        assertEquals("No debe haber caminos válidos guardados", 0, grilla.getCaminosValidos().size());
    }

    // Test que verifica que no hay camino válido cuando no existe con poda
    @Test
    public void testSinCaminoValidoConPoda() {
        int[][] matriz = {
            {1, 1},
            {1, 1}
        };
        Grilla grilla = new Grilla(matriz);

        boolean hayCamino = grilla.buscarCaminosValidosConPoda();

        assertFalse("No debe encontrar camino válido con poda", hayCamino);
        assertEquals("No debe haber caminos válidos guardados", 0, grilla.getCaminosValidos().size());
    }

    

    // Test que verifica que el tiempo medido es mayor o igual a cero
    @Test
    public void testTiempoEjecucionNoNegativo() {
        int[][] matriz = {
            {1, -1, 1},
            {-1, 1, -1},
            {1, -1, 1}
        };
        Grilla grilla = new Grilla(matriz);

        grilla.buscarCaminosValidosSinPoda();
        assertTrue("Tiempo sin poda debe ser >= 0", grilla.getTiempoSinPoda() >= 0);

        grilla.buscarCaminosValidosConPoda();
        assertTrue("Tiempo con poda debe ser >= 0", grilla.getTiempoConPoda() >= 0);
    }
    
    
}
