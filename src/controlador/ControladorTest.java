package controlador;

import static org.junit.Assert.*;

import org.junit.Test;

public class ControladorTest {


	@Test
	public void testControladorInicializacion() {
	    Controlador controlador = new Controlador();
	    assertNotNull(controlador.getFilas());
	    assertNotNull(controlador.getColumnas());
	}

	@Test
	public void testIniciarBusqueda() {
	    Controlador controlador = new Controlador();
	    controlador.iniciarBusqueda();
	    assertTrue(controlador.getFilas() > 0);
	    assertTrue(controlador.getColumnas() > 0);
	}

	@Test
	public void testGetElementoEnRango() {
	    Controlador controlador = new Controlador();
	    int elemento = controlador.getElemento(0, 0); 
	    assertNotNull(elemento);
	}

	
}
