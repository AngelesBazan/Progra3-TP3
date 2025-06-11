package vista;

import controlador.Controlador;
import modelo.Coordenada;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.*;
import java.util.List;

public class Vista extends JFrame {

	private Controlador controlador;
	private JLabel[][] celdas;
	private JTable tablaResultados;
	private DefaultTableModel modeloTabla;

	public Vista(Controlador controlador) {
		this.controlador = controlador;
		inicializarUI();
	}
	
    private void inicializarUI() {
        setLayout(new BorderLayout());
        setBounds(500, 200, 400, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

		inicializarTabla();
		inicializarGrilla();
		setVisible(true);
	}

	private void inicializarTabla() {
		String[] columnas = { "", "Con poda", "Sin poda" };
		String[][] datos = { { "Tiempo (ms)", "-", "-" }, { "Cant. de caminos explorados", "-", "-" } };

		modeloTabla = new DefaultTableModel(datos, columnas);
		tablaResultados = new JTable(modeloTabla);
		tablaResultados.getColumnModel().getColumn(0).setPreferredWidth(200);

		JScrollPane panelTabla = new JScrollPane(tablaResultados);
		panelTabla.setPreferredSize(new Dimension(10, 80));

		add(panelTabla, BorderLayout.NORTH);
	}

	private void inicializarGrilla() {
		int filas = controlador.getFilas();
		int columnas = controlador.getColumnas();
		celdas = new JLabel[filas][columnas];
		JPanel laminaGrilla = new JPanel(new GridLayout(filas, columnas));
		laminaGrilla.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));

		for (int f = 0; f < filas; f++) {
			for (int c = 0; c < columnas; c++) {
				JLabel elem = new JLabel(String.valueOf(controlador.getElemento(f, c)));
				elem.setHorizontalAlignment(JLabel.CENTER);
				elem.setOpaque(true);
				elem.setBorder(BorderFactory.createLineBorder(Color.BLACK));
				celdas[f][c] = elem;
				laminaGrilla.add(elem);
			}
		}
		add(laminaGrilla, BorderLayout.CENTER);
	}

	public void actualizarTabla(double tiempoConPoda, double tiempoSinPoda, int llamadasConPoda, int llamadasSinPoda) {
		modeloTabla.setValueAt(String.valueOf(tiempoConPoda), 0, 1);
		modeloTabla.setValueAt(String.valueOf(tiempoSinPoda), 0, 2);
		modeloTabla.setValueAt(String.valueOf(llamadasConPoda), 1, 1);
		modeloTabla.setValueAt(String.valueOf(llamadasSinPoda), 1, 2);
	}

	public void dibujarCaminos(List<List<Coordenada>> caminos) {
		if (!caminos.isEmpty()) {
			List<Coordenada> camino = caminos.get(0);
			final int[] indice = { 0 };
			Timer timer = new Timer(500, null);
			timer.addActionListener(e -> {
				if (indice[0] < camino.size()) {
					Coordenada coord = camino.get(indice[0]);
					celdas[coord.getX()][coord.getY()].setBackground(Color.YELLOW);
					indice[0]++;
				} else {
					timer.stop();
				}
			});
			timer.start();
		}
	
		   if (!caminos.isEmpty()) {
	            List<Coordenada> camino = caminos.get(0);
	            final int[] indice = {0};

	            Timer timer = new Timer(500, null);
	            timer.addActionListener(e -> {
	                if (indice[0] < camino.size()) {
	                    Coordenada coord = camino.get(indice[0]);
	                    celdas[coord.getX()][coord.getY()].setBackground(Color.YELLOW);
	                    indice[0]++;
	                } else {
	                    timer.stop();
	                }
	            });
	            timer.start();
	        }
	    }
	
	
	public void mostrarMensaje(String mensaje) {
	    JOptionPane.showMessageDialog(this, mensaje, "Información", JOptionPane.INFORMATION_MESSAGE);
	}


		
	

}
