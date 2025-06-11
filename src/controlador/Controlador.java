package controlador;

import modelo.Grilla;
import vista.Vista;


public class Controlador {

    private Grilla grilla;
    private Vista vista;

    public Controlador() {
        grilla = new Grilla();
        vista = new Vista(this);
    }

    public void iniciarBusqueda() {
        boolean encontradoConPoda = grilla.buscarCaminosValidosConPoda();
        boolean encontradoSinPoda = grilla.buscarCaminosValidosSinPoda();

        vista.actualizarTabla(grilla.getTiempoConPoda(), grilla.getTiempoSinPoda(),
                grilla.getLlamadasConPoda(), grilla.getLlamadasSinPoda());

        if (encontradoConPoda || encontradoSinPoda) {
            vista.dibujarCaminos(grilla.getCaminosValidos());
        }
    }

    public int getElemento(int x, int y) {
        return grilla.getElemnto(x, y);
    }

    public int getFilas() {
        return grilla.getFilas();
    }

    public int getColumnas() {
        return grilla.getColumnas();
    }
}
