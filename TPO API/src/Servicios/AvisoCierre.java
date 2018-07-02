package Servicios;

import java.time.LocalDate;
import java.time.Month;
import java.time.ZoneId;
import java.util.Date;

import Controladores.CtrlABMListas;

public class AvisoCierre extends Thread{
	
	public AvisoCierre() throws InterruptedException
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
				CtrlABMListas.getInstancia().avisoCierre();
				
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

