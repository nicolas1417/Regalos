package Persistencia;

import java.sql.Connection;
import java.util.Date;
import java.util.Vector;

import Controladores.CtrlSesion;
import Negocio.Lista;
import Negocio.Usuario;
import Negocio.UsuarioDeLista;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class ADMPersistenciaListas {

	private Connection laConexion;
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
	
	public void altaLista(String nombreAgasajado, int montoParticipante, java.util.Date fechaInicio, String mail, java.util.Date fechaFin, java.util.Date fechaDelAgasajo) throws Exception 
	{
		PreparedStatement s;
		try {
			DateFormat daf = new SimpleDateFormat("yyyy-M-d");
			
			
			laConexion = DataAccess.Conectar();
			s = laConexion.prepareStatement("INSERT INTO LISTA (fecha, montoPorParticipante, montoRecaudado, fechaInicio, fechaFin, estado, mail, usuarioAdmin, nombreAgasajado) VALUES(?,?,?,?,?,?,?,?,?)");
			String fechaAgaParaSQL = daf.format(fechaDelAgasajo);
			s.setDate(1, java.sql.Date.valueOf(fechaAgaParaSQL));			
			s.setInt(2, montoParticipante);
			s.setInt(3, 0);
			String fechaIniParaSQL = daf.format(fechaInicio);
			s.setDate(4, java.sql.Date.valueOf(fechaIniParaSQL));
			String fechaFinParaSQL = daf.format(fechaFin);
			s.setDate(5, java.sql.Date.valueOf(fechaFinParaSQL));
			s.setInt(6, 1);
			s.setString(7, mail);
			s.setString(8, CtrlSesion.getInstancia().getUsuarioLogueado().getUsuario());//Usuario en la sesión
			s.setString(9, nombreAgasajado);
			
			
			s.execute();
			laConexion.close();
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
			laConexion = DataAccess.Conectar();
			PreparedStatement s = laConexion.prepareStatement("SELECT l.idLista,l.nombreAgasajado, l.fechaAgasajo, l.montoPorParticipante, l.montoRecaudado, l.fechaInicio, l.fechaFin,l.mail, l.estado, u.usuario, u.contrasena, u.nombre,tu.id, tu.codigo, u.fechaNacimiento, u.mail, u.estado FROM TIPOUSUARIO tu, USUARIO u, USUARIODELISTA ul, LISTA l WHERE u.usuario = ? AND u.tipo = 1 AND u.tipo = tu.id AND u.usuario = ul.usuario AND ul.idLista = l.idLista;");		
			
			s.setString(1,usuario);
			
			ResultSet result = s.executeQuery();
			
			while(result.next())
			{
				//Datos Lista
				int idLista = result.getInt(1);
				String nombreAgasajado = result.getString(2);
				Date fechaAgasajo = result.getDate(3);
				int montoParticipante = result.getInt(4);
				int montoRecaudado = result.getInt(5);
				Date fechaInicio = result.getDate(6);
				Date fechaFin = result.getDate(7);
				String mailLista = result.getString(8);
				int estadoLista = result.getInt(9);	
				
				//Datos admin de la lista
				String user = result.getString(10);
				String contrasena = result.getString(11);
				String nombre = result.getString(12);
				int idTipoUsuario = result.getInt(13);
				String codTipoUsuario = result.getString(14);
				Date fecha_nac = result.getDate(15);
				String mailUsuario = result.getString(16);
				int estadoUsuario = result.getInt(17);
				boolean tipousuario;
				if(idTipoUsuario > 0)
					tipousuario = true;
				else
					tipousuario = false;
				//TipoUsuario tu = new TipoUsuario(idTipoUsuario,codTipoUsuario);
				
				Usuario u = new Usuario(nombre,user,contrasena,fecha_nac,estadoUsuario==1,mailUsuario,tipousuario);
				
				UsuarioDeLista ul = new UsuarioDeLista(u, true, true);
						
				Lista l = new Lista(idLista, nombreAgasajado, fechaAgasajo, montoParticipante, montoRecaudado, fechaInicio, fechaFin, estadoLista==1, mailLista, ul);
											
				v.add(l);
			}										
			
			laConexion.close();
					
			return v;
		}
		catch(Exception e)
		{
			throw e;
		}
	}
	
	public void modificarLista(int idLista, java.util.Date fechaAga, int montoPart, java.util.Date fechaF, String correo, java.util.Date fechaI) throws Exception
	{
		laConexion = DataAccess.Conectar();
		PreparedStatement s;
		
		try
		{
			s = laConexion.prepareStatement("UPDATE LISTA SET fechaAgasajo = ?,montoPorParticipante = ?,fechaFin = ?, mail = ?, fechaInicio = ? WHERE idLista = ?");
			DateFormat daf = new SimpleDateFormat("yyyy-M-d");
			String fechaParaSQL = daf.format(fechaAga);
			s.setDate(1, java.sql.Date.valueOf(fechaParaSQL));
			s.setInt(2, montoPart);
			fechaParaSQL = daf.format(fechaF);
			s.setDate(3, java.sql.Date.valueOf(fechaParaSQL));			
			s.setString(4,correo);
			fechaParaSQL = daf.format(fechaI);
			s.setDate(5, java.sql.Date.valueOf(fechaParaSQL));
			s.setInt(6, idLista);
						
			s.execute();
			
			laConexion.close();
		}
		catch(Exception e)
		{
			throw e;
		}
	}
	
	public void eliminarLista(int idLista) throws Exception
	{
		laConexion = DataAccess.Conectar();
		PreparedStatement s;
		try
		{
			s = laConexion.prepareStatement("UPDATE lista SET estado = 0 where idlista = ?");
			s.setInt(1,idLista);
			s.execute();
			
			laConexion.close();
		}
		catch(Exception e) 
		{
			throw e;
		}
	}
}
