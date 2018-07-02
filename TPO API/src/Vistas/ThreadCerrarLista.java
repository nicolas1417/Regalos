package Vistas;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.util.Date;
import java.util.Vector;
import Controladores.CtrlMail;

import Controladores.CtrlABMListas;

public class ThreadCerrarLista extends Thread{
	
	public ThreadCerrarLista() throws InterruptedException
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
			Date fecha = new Date();
			
			Vector<String> mails = CtrlABMListas.getInstancia().notificarRegalos(fecha);
			
			for(int i=0;i<mails.size();i++)
			{
				CtrlMail.getInstancia().sendEmail(mails.elementAt(i));
			}	
		}
		catch(Exception e)
		{
			throw e;
		}
	}
}
