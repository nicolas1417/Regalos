package Persistencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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

	public List<String> buscarMisPagos(String logueado) throws SQLException {
		List<String> res = new ArrayList<String>();
		PreparedStatement s;
		Connection con = DataAccess.getConexion().getInstanciaDB();
		s = con.prepareStatement("select ul.idLista, p.monto, p.fechaMov from USUARIODELISTA ul Inner Join PAGO p ON ul.IdPago = p.idPago where ul.usuario = ?;");
		s.setString(1, logueado);
		ResultSet rs = s.executeQuery();
		while(rs.next()){
			int idLista = rs.getInt(1);
			int monto = rs.getInt(2);
			Date fechaMov = rs.getDate(3);
			
			SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
			String fecha = sdf.format(fechaMov);
			
			String concat = String.valueOf(idLista) + "|" + String.valueOf(monto) + "|" + fecha;
			
			res.add(concat);
		}
		
		DataAccess.getConexion().cerrarConexion();
		
		return res;	
	}
	
	
}
