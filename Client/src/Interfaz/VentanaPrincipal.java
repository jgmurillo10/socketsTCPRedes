package Interfaz;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UIManager;


public class VentanaPrincipal extends JFrame {
	
	
	 public VentanaPrincipal( )
	    {
	        setLayout( new BorderLayout( ) );

	        PanelConexion panelConexion = new PanelConexion( this );
	        add( panelConexion, BorderLayout.NORTH );
	        PanelDescarga panelDescarga = new PanelDescarga( this );
	        PanelArchivos panelArchivos = new PanelArchivos( this );
	        JPanel panelMedio = new JPanel( new BorderLayout( ) );
	        panelMedio.add( panelDescarga, BorderLayout.CENTER );
	        panelMedio.add( panelArchivos, BorderLayout.SOUTH );
	        add( panelMedio, BorderLayout.SOUTH );
	       

	      

	        pack( );
	        setTitle( "Interfaz Cliente" );
	        setResizable( true );
	        setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );

	    }

	
	public static void main( String[] args )
    {
        try
        {
            UIManager.setLookAndFeel( UIManager.getSystemLookAndFeelClassName( ) );
        }
        catch( Exception e )
        {
            //Ignora el look & feel
        }
        VentanaPrincipal i = new VentanaPrincipal( );
        i.setVisible( true );
    }

}
