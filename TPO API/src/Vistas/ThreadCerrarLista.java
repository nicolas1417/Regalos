package Vistas;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.util.Date;
import java.util.Vector;

import Controladores.CtrlABMListas;

public class ThreadCerrarLista extends Thread{
	
	public ThreadCerrarLista() throws InterruptedException
	{
		
	}
	
	public void run ()
	{
		ejecutarRutina();
	}
	
	private void ejecutarRutina()
	{
		Date fecha;
		
		//Vector<String> mails = CtrlABMListas.getInstancia().notificarRegalos(fecha);	
	}
}
