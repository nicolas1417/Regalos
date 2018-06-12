package Persistencia;

import java.sql.Connection;
import java.util.Date;
import java.util.Vector;

import Negocio.Lista;
import Negocio.TipoUsuario;
import Negocio.Usuario;
import Negocio.UsuarioDeLista;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
	
	public Vector<Lista> buscarListas(String usuario) throws Exception
	{
		Vector<Lista> v = new Vector<Lista>();
		try
		{
			Connection con = DataAccess.getConexion().getInstanciaDB();
			PreparedStatement s = con.prepareStatement("SELECT l.idLista, l.fecha, l.montoPorParticipante, l.montoRecaudado, l.fechaInicio, l.fechaFin, l.estado, u.usuario, u.contrasena, u.nombre,tu.id, tu.codigo, u.fechaNacimiento, u.mail, u.estado FROM TIPOUSUARIO tu, USUARIO u, USUARIODELISTA ul, LISTA l WHERE u.usuario = ? AND u.usuario = 1 AND u.tipo = tu.id AND u.usuario = ul.usuario AND ul.idLista = l.idLista");		
			
			s.setString(1,usuario);
			
			ResultSet result = s.executeQuery();
			
			while(result.next())
			{
				int idLista = result.getInt(1);
				Date fechaAgasajo = result.getDate(2);
				int montoParticipante = result.getInt(3);
				int montoRecaudado = result.getInt(4);
				Date fechaInicio = result.getDate(5);
				Date fechaFin = result.getDate(6);
				int estadoLista = result.getInt(7);	
				
				String user = result.getString(8);
				String contrasena = result.getString(9);
				String nombre = result.getString(10);
				int idTipoUsuario = result.getInt(11);
				String codTipoUsuario = result.getString(12);
				Date fecha_nac = result.getDate(13);
				String mail = result.getString(14);
				int estadoUsuario = result.getInt(15);
				
				TipoUsuario tu = new TipoUsuario(idTipoUsuario,codTipoUsuario);
				
				Usuario u = new Usuario(nombre,user,contrasena,fecha_nac,estadoUsuario==1,mail,tu);
				
				UsuarioDeLista ul = new UsuarioDeLista(u, true, true);
						
				Lista l = new Lista(ul, fechaInicio, fechaFin, ""); //TODO: Nombre Agasajado
				
								
				v.add(l);
			}										
			
			DataAccess.getConexion().cerrarConexion();
					
			return v;
		}
		catch(Exception e)
		{
			throw e;
		}
	}
}
