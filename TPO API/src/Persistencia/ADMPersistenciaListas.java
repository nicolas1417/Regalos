package Persistencia;

import java.sql.Connection;
import java.util.Date;
import java.sql.PreparedStatement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class ADMPersistenciaListas {

private static ADMPersistenciaListas instancia;
	
	private ADMPersistenciaListas()
	{
		
	}
	
	public static ADMPersistenciaListas getInstancia()
	{
		if (instancia == null)
			instancia = new ADMPersistenciaListas();
		return instancia;
	}
	
	public void altaLista(java.util.Date fechaAgasajo, int montoParticipante, java.util.Date fechaFin, String mail, java.util.Date fechaInicio, String usuario) throws Exception 
	{
		
		Connection con = DataAccess.getConexion().getInstanciaDB();
		PreparedStatement s;
		try {
			s = con.prepareStatement("INSERT INTO LISTA (fecha,montoPorParticipante,montoRecaudado,fechaInicio,fechaFin,estado,mail,usuarioAdmin) VALUES (?,?,?,?,?,?,?,?)");
			DateFormat daf = new SimpleDateFormat("yyyy-M-d");
			String fechaParaSQL = daf.format(fechaAgasajo);
			s.setDate(1, java.sql.Date.valueOf(fechaParaSQL));
			s.setInt(2, montoParticipante);
			s.setInt(3, 0);
			String fechaIParaSQL = daf.format(fechaInicio);
			s.setDate(4, java.sql.Date.valueOf(fechaIParaSQL));
			String fechaFParaSQL = daf.format(fechaFin);
			s.setDate(5, java.sql.Date.valueOf(fechaFParaSQL));
			s.setInt(6, 1);
			s.setString(7, mail);
			s.setString(8, usuario);
			
			s.execute();
			DataAccess.getConexion().cerrarConexion();
		}catch(Exception ex){
			ex.printStackTrace();
			throw ex;
		}
	}
}
