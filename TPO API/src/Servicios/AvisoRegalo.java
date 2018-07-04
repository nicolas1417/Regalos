package Servicios;

import java.time.LocalDate;
import java.time.Month;
import Controladores.CtrlABMListas;

public class AvisoRegalo extends Thread{
	
	public AvisoRegalo() throws InterruptedException
	{
		
	}
	
	public void run ()
	{
		try {
			ejecutarRutina();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void ejecutarRutina() throws Exception
	{
		try
		{
			LocalDate finFecha = LocalDate.of(2018, Month.JULY,20);
			LocalDate fecha = LocalDate.now();
			
			while (fecha.isBefore(finFecha))
			{
				CtrlABMListas.getInstancia().notificarRegalos();
				
				try {
					sleep(120000); //dos minutos
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
}
