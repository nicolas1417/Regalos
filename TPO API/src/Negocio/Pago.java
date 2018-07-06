 	package Negocio;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import Persistencia.ADMPersistenciaPago;

public class Pago {
	private Date fechaMovimiento;
	private int monto;
	
	public Pago(int monto, Date fechaMov)
	{
		
	}
	
	public Pago(){
		
	}
	public List<String> buscarMisPagos(String logueado) throws SQLException{
		return ADMPersistenciaPago.getInstancia().buscarMisPagos(logueado);
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
