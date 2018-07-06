 	package Negocio;

import java.util.Date;

import Persistencia.ADMPersistenciaPago;

public class Pago {
	private Date fechaMovimiento;
	private int monto;
	
	public Pago(int monto, Date fechaMov)
	{
		
	}
	
	public int RegistrarPago()
	{
		int monto = 0;
		try
		{
			monto = ADMPersistenciaPago.getInstancia().registrarPago(this.monto,this.fechaMovimiento);
		}
		catch(Exception e)
		{
			
		}
		return monto;
	}
}
