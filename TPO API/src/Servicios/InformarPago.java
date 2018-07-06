package Servicios;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Month;
import java.util.Date;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.channels.FileChannel;

import Controladores.CtrlABMListas;
import ObservadorDePago.ObservadorPago;

public class InformarPago extends Thread{	
	
	ObservadorPago miObservador;
	public InformarPago(ObservadorPago miObservador) throws InterruptedException
	{
		this.miObservador = miObservador;
	}
	
	public void run ()
	{
		try {
			ejecutarRutina();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void ejecutarRutina() throws Exception
	{
		try
		{
			sleep(20000);
			LocalDate finFecha = LocalDate.of(2018, Month.JULY,20);
			LocalDate fecha = LocalDate.now();
			
			while (fecha.isBefore(finFecha))
			{	
				File archivo = null;
				Date fechaProcesadoOK;
			    FileReader fr = null;
			    BufferedReader br = null;

			    try
			    {
			    	// Apertura del fichero y creacion de BufferedReader para poder
			        // hacer una lectura comoda (disponer del metodo readLine()).
			        archivo = new File ("Pagos/Pagos.txt");
			        
			        if (archivo.exists())
			        {
				        fechaProcesadoOK = new Date();
				        DateFormat fechaHora = new SimpleDateFormat("yyyyMMddHHmmss");
						String mascara = fechaHora.format(fechaProcesadoOK);
				        	        
				        fr = new FileReader (archivo);
				        br = new BufferedReader(fr);
	
				        // Lectura del fichero
				        String linea;
				        while((linea = br.readLine()) != null)
				        {
				        	System.out.println(linea);
				        	String [] datos = linea.split("\\|");
				        	
				        	int idLista = Integer.parseInt(datos[0]);
				        	String usuario = datos[1];
				        	int monto = Integer.parseInt(datos[2]);
				        	Date fechaMov = new SimpleDateFormat("yyyy/MM/dd").parse(datos[3]);
				        	
				        	int montoRecaudado = CtrlABMListas.getInstancia().RegistrarPago(usuario,idLista,monto,fechaMov);
				        	
				        	miObservador.setMontoRecaudado(montoRecaudado, idLista);
				        }
				        
				        fr.close();
				        
				        copyFile(archivo,new File ("Pagos\\Pagos_PROCESADOOK_"+ mascara +".txt" ));
				        archivo.delete();
			        }
			    }
			    catch(Exception e)
			    {
			    	e.printStackTrace();
			    }
			   
				try {
					sleep(120000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			
		}
		catch(Exception e)
		{
			throw e;
		}
	}
		
	private void copyFile(File s, File t)
    {
        try{
              FileChannel in = (new FileInputStream(s)).getChannel();
              FileChannel out = (new FileOutputStream(t)).getChannel();
              in.transferTo(0, s.length(), out);
              in.close();
              out.close();
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
    }
}
