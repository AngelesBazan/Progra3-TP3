
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;	
import javax.swing.Timer;
import javax.swing.JTable;
import javax.swing.JScrollPane;

import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;

public class Vista extends JFrame {
	JPanel laminaGrilla;
	Grilla grilla;
	

	JLabel tiempoConPoda;
	JLabel tiempoSinPoda;

	JTable tablaResultados;
	JScrollPane panelTabla;
	DefaultTableModel modeloTabla;
	
    private JLabel[][] celdas; // Matriz para guardar cada JLabel

	public Vista() {
		setLayout(new BorderLayout());
		setBounds(400,400,400,400);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		grilla=new Grilla();
		int n=grilla.getN();
		int m=grilla.getM();
        celdas = new JLabel[grilla.getN()][grilla.getM()]; // Inicializar matriz

		
		laminaGrilla=new JPanel();
		laminaGrilla.setLayout(new GridLayout(grilla.getN(),grilla.getM()));
		Border borde = BorderFactory.createLineBorder(Color.BLACK, 2);
		laminaGrilla.setBorder(borde);
		crearGrilla(n,m);



	 
	    String[] columnas = { "", "Con poda", "Sin poda" }; 
	    String[][] datos = {
	        { "Tiempo (ms)", "-", "-" },  
	        { "Cant. de caminos explorados", "-", "-" }  
	    };
	    

	    modeloTabla = new DefaultTableModel(datos, columnas);
	    tablaResultados = new JTable(modeloTabla);
	    panelTabla = new JScrollPane(tablaResultados); 
	    
	    panelTabla.setPreferredSize(new Dimension(400, 80));
	    laminaGrilla.setPreferredSize(new Dimension(400, 300)); 
	    
	   
	    add(panelTabla, BorderLayout.NORTH); 
	    add(laminaGrilla, BorderLayout.CENTER);

	    pack();
	    setVisible(true);
		
		grilla.imprimirMatriz();

		System.out.println("buscartcaminosValidos:"+grilla.buscarCaminosValidosConPoda());
		System.out.println("llamadas con poda:"+grilla.getLlamadasConPoda());
		
		System.out.println(grilla.getCaminosValidos().size());
		System.out.println("tiempo de ejecucion:"+grilla.getTiempoConPoda());

		
		System.out.println();
		System.out.println("---------------------------------");
		System.out.println("buscartcaminosValidos:"+grilla.buscarCaminosValidosSinPoda());
		System.out.println("llamadas sin poda:"+grilla.getLlamadasSinPoda());
		System.out.println(grilla.getCaminosValidos().size());
		System.out.println("tiempo de ejecucion:"+grilla.getTiempoSinPoda());
		
		
		modeloTabla.setValueAt(String.valueOf(grilla.getTiempoConPoda()), 0, 1); 
		modeloTabla.setValueAt(String.valueOf(grilla.getTiempoSinPoda()), 0, 2);
		modeloTabla.setValueAt(String.valueOf(grilla.getLlamadasConPoda()), 1, 1); 
		modeloTabla.setValueAt(String.valueOf(grilla.getLlamadasSinPoda()), 1, 2); 

		if(!grilla.getCaminosValidos().isEmpty()) {
			
			dibujarCaminosConTimerLambda();
		}
		   

		setVisible(true);
	


	
	}

	private void crearGrilla(int n, int m) {
		// TODO Auto-generated method stub
		for(int f=0;f<n;f++) {
			for(int C=0;C<m;C++) {
				JLabel elem=new JLabel(grilla.getElemnto(f, C)+"");
		        elem.setHorizontalAlignment(JLabel.CENTER);

				elem.setVerticalAlignment(JLabel.CENTER);
		        elem.setOpaque(true); // Para poder ver colores de fondo, si los usás
		        elem.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                celdas[f][C] = elem; // Guardamos la referencia

				laminaGrilla.add(elem);
			}
		}
	}

	private void dibujarCaminosConTimerLambda() {
	    List<Coordenada> camino = grilla.getCaminosValidos().get(0); // o el camino que quieras

	    final int[] indice = {0}; // contador mutable

	    Timer timer = new Timer(500, null); // 300ms por paso

	    timer.addActionListener(e -> {
	        if (indice[0] < camino.size()) {
	            Coordenada coord = camino.get(indice[0]);
	            int posX=coord.getX();
	            int posY=coord.getY();
	            celdas[posX][posY].setBackground(Color.YELLOW);
	            indice[0]++;
	        } else {
	            timer.stop(); // Detener al final del camino
	        }
	    });

	    timer.start();
	}
		
	

}
