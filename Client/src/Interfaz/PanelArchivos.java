package Interfaz;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.*;

public class PanelArchivos extends JPanel implements ActionListener{
	
	
    private static final String VER = "Ver";

   
    private VentanaPrincipal ventanaPrincipal;

    
    private JList listaArchivos;

    private JButton botonVer;

 
    public PanelArchivos( VentanaPrincipal ventana )
    {
        ventanaPrincipal = ventana;

        setBorder( BorderFactory.createTitledBorder( "Archivos descargados" ) );
        setLayout( new BorderLayout( ) );

        listaArchivos = new JList( );
        JScrollPane scroll = new JScrollPane( );
        scroll.setViewportView( listaArchivos );
        scroll.setVerticalScrollBarPolicy( JScrollPane.VERTICAL_SCROLLBAR_ALWAYS );
        scroll.setHorizontalScrollBarPolicy( JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED );
        add( scroll, BorderLayout.CENTER );

        botonVer = new JButton( );
        botonVer.setText( "Ver archivo" );
        botonVer.setActionCommand( VER );
        botonVer.addActionListener( this );
        add(botonVer, BorderLayout.SOUTH);

     
    }

    public void actualizarArchivos( ArrayList archivos )
    {
        String[] lista = new String[archivos.size( )];
        String c;

        for( int i = 0; i < archivos.size( ); i++ )
        {
            c = (String) archivos.get( i );
            lista[ i ] = c;
        }

        listaArchivos.removeAll( );
        listaArchivos.setListData( lista );
    }

    
    public String darArchivoSeleccionado( )
    {
        int c = listaArchivos.getSelectedIndex( );
        if( c < 0 )
            return null;
        String archivo = ( String )listaArchivos.getSelectedValue( );
        return archivo;
    }

   
    public void actionPerformed( ActionEvent evento )
    {
        String comando = evento.getActionCommand( );
        String archivo = darArchivoSeleccionado( );

        if( comando.equals( VER ) )
        {
            if( archivo == null )
            {
                JOptionPane.showMessageDialog( this, "Debe seleccionar un archivo", "Mostrar Archivo", JOptionPane.ERROR_MESSAGE );
                return;
            }
           // ventanaPrincipal.mostrarCliente( cedula );
        }
        
    }
}
