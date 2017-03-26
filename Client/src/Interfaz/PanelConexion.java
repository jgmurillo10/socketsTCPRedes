package Interfaz;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class PanelConexion extends JPanel implements ActionListener {
	
	private VentanaPrincipal principal;
	private JLabel solicitar;
	private JButton conexion;
	public static String CONECTAR="Conectar";
	private JLabel estado;
	private JTextField estadoC;
	
	public PanelConexion(VentanaPrincipal ventana){
		
		principal = ventana;
	    setLayout(new GridLayout(2,2));

        setBorder( BorderFactory.createTitledBorder( "Conexi�n Servidor" ) );

        solicitar = new JLabel( "Solicitar conexi�n" );
        add( solicitar );
        
        conexion = new JButton( );
        conexion.setText( "AQU�" );
        conexion.setActionCommand( CONECTAR );
        conexion.addActionListener( this );
        add( conexion );
		
        estado = new JLabel("Estado de la conexi�n");
        add(estado);
        
        estadoC = new JTextField();
        estadoC.setEditable(false);
        estadoC.setText("Sin conexi�n");
        add(estadoC);
	}
	
	public void cambiarEstado(){
		estadoC.setText("Conectado");
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

}
