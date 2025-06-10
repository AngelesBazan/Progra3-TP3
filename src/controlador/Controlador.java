package controlador;

import modelo.Grilla;
import modelo.Coordenada;
import vista.Vista;

import java.util.List;

public class Controlador {
    
    private Grilla grilla;
    private Vista vista;
    
    public Controlador() {
        grilla = new Grilla();
        vista = new Vista(this);
    }
    
    public void iniciarBusqueda() {
        boolean encontrado = grilla.buscarCaminosValidosConPoda();
        vista.actualizarTabla(grilla.getTiempoConPoda(), grilla.getTiempoSinPoda(),
                              grilla.getLlamadasConPoda(), grilla.getLlamadasSinPoda());

        if (encontrado) {
            vista.dibujarCaminos(grilla.getCaminosValidos());
        }
    }

    public int getElemento(int x, int y) {
        return grilla.getElemnto(x, y);
    }
    
    public int getN() {
        return grilla.getN();
    }

    public int getM() {
        return grilla.getM();
    }
}
