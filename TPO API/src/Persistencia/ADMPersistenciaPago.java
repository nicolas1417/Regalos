package Persistencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ADMPersistenciaPago {
	private static ADMPersistenciaPago instancia;
	
	private ADMPersistenciaPago()
	{
		
	}
	
	public static ADMPersistenciaPago getInstancia()
	{
		if (instancia == null)
			instancia = new ADMPersistenciaPago();
		return instancia;
	}
	
	public int registrarPago(int monto,Date fechaMov) throws Exception
	{
		Connection con = DataAccess.getConexion().getInstanciaDB();
		PreparedStatement s;
		try
		{
			s = con.prepareStatement("INSERT INTO PAGO (monto,fechaMov) VALUES (?,?)");
			s.setInt(1,monto);
			
			DateFormat daf = new SimpleDateFormat("yyyy-M-d");
			String fechaMovParaSQL = daf.format(fechaMov);
			s.setDate(2, java.sql.Date.valueOf(fechaMovParaSQL));	
			
			s.execute();
			
			DataAccess.getConexion().cerrarConexion();
			
			return obtenerUltimoID();
		}
		catch(Exception e) 
		{
			throw e;
		}
	}
	
	private int obtenerUltimoID() throws Exception
	{
		try
		{
			PreparedStatement s;
			Connection con = DataAccess.getConexion().getInstanciaDB();
			s = con.prepareStatement("select MAX(idPago) from pago");
			ResultSet rs = s.executeQuery();
			
			rs.next();			
			int res = rs.getInt(1);
			
			DataAccess.getConexion().cerrarConexion();
			
			return res;
		}
		catch(Exception e)
		{
			throw e;
		}
	}
	
	
}
