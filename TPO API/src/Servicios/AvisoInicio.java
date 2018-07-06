package Servicios;

import java.time.LocalDate;
import java.time.Month;
import Controladores.CtrlABMListas;

public class AvisoInicio extends Thread{
	
	public AvisoInicio() throws InterruptedException
	{
		
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
				CtrlABMListas.getInstancia().avisoInicio();
				
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
