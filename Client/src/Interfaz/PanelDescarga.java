package Interfaz;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.*;


public class PanelDescarga extends JPanel implements ActionListener {
	
	
	 private JList listaArchivos;
	    private JButton botonDescargar;
	    private JButton botonDetener;
	    private VentanaPrincipal ventana;
	    public static String DESCARGAR="Descargar";
	    public static String DETENER="Detener";

	 
	    public PanelDescarga( VentanaPrincipal ventana )
	    {
	        ventana = this.ventana;

	        setBorder( BorderFactory.createTitledBorder( "Lista de Archivos" ) );
	        setLayout( new BorderLayout( ) );

	        listaArchivos = new JList( );
	        JScrollPane scroll = new JScrollPane( );
	        scroll.setViewportView( listaArchivos );
	        scroll.setVerticalScrollBarPolicy( JScrollPane.VERTICAL_SCROLLBAR_ALWAYS );
	        scroll.setHorizontalScrollBarPolicy( JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED );
	        add( scroll, BorderLayout.CENTER );

	        botonDescargar = new JButton( );
	        botonDescargar.setText( "Descargar" );
	        botonDescargar.setActionCommand( DESCARGAR );
	        botonDescargar.addActionListener( this );

	        botonDetener = new JButton( );
	        botonDetener.setText( "Detener" );
	        botonDetener.setActionCommand( DETENER );
	        botonDetener.addActionListener( this );

	        JPanel panel = new JPanel( );
	        panel.add( botonDescargar );
	        panel.add( botonDetener );
	        add( panel, BorderLayout.SOUTH );
	    }

	  
	    public void actualizarArchivos( ArrayList archivos )
	    {
	        String[] lista = new String[archivos.size( )];

	        for( int i = 0; i < archivos.size( ); i++ )
	            lista[ i ] = (String) archivos.get(i);

	        listaArchivos.removeAll( );
	        listaArchivos.setListData( lista );
	    }

	   
	    public String darArchivoSeleccionado( )
	    {
	        int p = listaArchivos.getSelectedIndex( );
	        if( p < 0 )
	            return null;
	        return ( String )listaArchivos.getSelectedValue( );
	    }

	    public void actionPerformed( ActionEvent evento )
	    {
	        String comando = evento.getActionCommand( );
	        String titulo = darArchivoSeleccionado( );
	        if( comando.equals( DESCARGAR ) )
	        {
	            if( titulo == null )
	            {
	                JOptionPane.showMessageDialog( this, "Debe seleccionar un archivo", "Mostrar Archivo", JOptionPane.ERROR_MESSAGE );
	                return;
	            }
	            //ventana.mostrarPelicula( titulo );
	        }
	        else if( comando.equals( DETENER ) )
	        {
	            
	        }
	    }
}
