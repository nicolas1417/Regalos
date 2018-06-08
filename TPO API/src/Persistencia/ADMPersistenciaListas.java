package Persistencia;

import java.sql.Connection;
import java.sql.Date;
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
	
	public void altaLista(String fechaAgasajo, int montoParticipante, String fechaFin, String mail, String usuario) throws Exception 
	{
		
		Connection con = DataAccess.getConexion().getInstanciaDB();
		PreparedStatement s;
		try {
			s = con.prepareStatement("INSERT INTO LISTA (fecha,montoPorParticipante,montoRecaudado,fechaInicio,fechaFin,estado,mail,usuarioAdmin) VALUES (?,?,?,?,?,?,?,?)");
			
			s.setString(1, fechaAgasajo);
			s.setInt(2, montoParticipante);
			s.setInt(3, 0);
			DateFormat daf = new SimpleDateFormat("yyyy-m-d");
			String fechaInicio = daf.format(Calendar.getInstance().getTime());
			s.setDate(4, java.sql.Date.valueOf(fechaInicio));
			s.setDate(5, java.sql.Date.valueOf(fechaFin));
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
