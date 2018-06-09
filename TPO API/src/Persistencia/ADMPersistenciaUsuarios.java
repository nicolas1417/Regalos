package Persistencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;

import Negocio.TipoUsuario;
import Negocio.Usuario;

public class ADMPersistenciaUsuarios {
	
private static ADMPersistenciaUsuarios instancia;
	
	private ADMPersistenciaUsuarios()
	{
		
	}
	
	public static ADMPersistenciaUsuarios getInstancia()
	{
		if (instancia == null)
			instancia = new ADMPersistenciaUsuarios();
		return instancia;
	}
	
	public Vector<TipoUsuario> obtenerTiposDeUsuario() throws Exception
	{
		Vector<TipoUsuario> vTiposUsuarios = new Vector<TipoUsuario>();
		try
		{
			
			Connection con = DataAccess.getConexion().getInstanciaDB();
			PreparedStatement s = con.prepareStatement("select * from tipousuario");
	
			ResultSet result = s.executeQuery();
			
			while (result.next()) 
			{
				TipoUsuario tu = new TipoUsuario(result.getInt(1),result.getString(2));
				
				vTiposUsuarios.add(tu);
			}
			
			if( vTiposUsuarios.size() == 0)
				throw new Exception("No existen tipos de usuario");	
			
			DataAccess.getConexion().cerrarConexion();
		}
		catch(Exception e)
		{
			throw e;
		}
		return vTiposUsuarios;
	}
	
	public void altaUsuario(String usuario, String contrasena, String nombre,int idTipoUsuario, Date fechaNacimiento,String mail) throws Exception
	{
		Connection con = DataAccess.getConexion().getInstanciaDB();
		PreparedStatement s;
		try 
		{
			s = con.prepareStatement("INSERT INTO USUARIO (usuario,contrasena,nombre,tipo,fechaNacimiento,mail) VALUES (?,?,?,?,?,?)");
			
			s.setString(1,usuario);
			s.setString(2, contrasena);
			s.setString(3, nombre);
			s.setInt(4, idTipoUsuario);
			
			DateFormat df = new SimpleDateFormat("yyyy-M-d");
			String fechaParaSQL = df.format(fechaNacimiento);
			s.setDate(5, java.sql.Date.valueOf(fechaParaSQL));
			
			s.setString(6, mail);
			
			s.execute();
			
			DataAccess.getConexion().cerrarConexion();
		} 
		catch (Exception e) 
		{
			throw e;
		}
	}
	
	public boolean validarUsuario(String usuario)
	{	
		try
		{
			Connection con = DataAccess.getConexion().getInstanciaDB();
			PreparedStatement s = con.prepareStatement("select 1 from usuario where usuario = ?");
			s.setString(1,usuario);			
			
			ResultSet result = s.executeQuery();
				
			if (result.next())
				return false;	
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		DataAccess.getConexion().cerrarConexion();
		return true;
	}
	
	public Usuario LoginUser(String usuario, String password) throws Exception
	{
		try
		{
			
			Connection con = DataAccess.getConexion().getInstanciaDB();
			PreparedStatement s = con.prepareStatement("select u.usuario,u.contrasena,u.nombre,tu.id,tu.codigo,u.fechaNacimiento,u.mail,u.estado from usuario u, tipousuario tu where u.tipo = tu.id AND usuario = ? AND contrasena = ?");
			s.setString(1,usuario);
			s.setString(2, password);
			ResultSet result = s.executeQuery();
			
			if (!result.next())
				throw new Exception("El usuario o contraseña ingresada es incorrecta");	
						
			String user = result.getString(1);
			String contrasena = result.getString(2);
			String nombre = result.getString(3);
			int idTipoUsuario = result.getInt(4);
			String codTipoUsuario = result.getString(5);
			Date fecha_nac = result.getDate(6);
			String mail = result.getString(7);
			int estado = result.getInt(8);
			
			TipoUsuario tu = new TipoUsuario(idTipoUsuario,codTipoUsuario);
			
			Usuario u = new Usuario(nombre,user,contrasena,fecha_nac,estado==1,mail,tu);			
			
			DataAccess.getConexion().cerrarConexion();
			
			return u;
		}
		catch (Exception e)
		{
			throw e;
		}
	}
	
	public Vector<Usuario> buscarUsuarios() throws Exception
	{
		Vector<Usuario> v = new Vector<Usuario>();
		try
		{
			Connection con = DataAccess.getConexion().getInstanciaDB();
			PreparedStatement s = con.prepareStatement("select u.usuario,u.contrasena,u.nombre,tu.id,tu.codigo,u.fechaNacimiento,u.mail,u.estado from usuario u, tipousuario tu where u.tipo = tu.id");		
			
			ResultSet result = s.executeQuery();
			
			while(result.next())
			{
				String user = result.getString(1);
				String contrasena = result.getString(2);
				String nombre = result.getString(3);
				int idTipoUsuario = result.getInt(4);
				String codTipoUsuario = result.getString(5);
				Date fecha_nac = result.getDate(6);
				String mail = result.getString(7);
				int estado = result.getInt(8);
				
				TipoUsuario tu = new TipoUsuario(idTipoUsuario,codTipoUsuario);
				
				Usuario u = new Usuario(nombre,user,contrasena,fecha_nac,estado==1,mail,tu);
				
				v.add(u);
			}
			
			DataAccess.getConexion().cerrarConexion();
					
			return v;
		}
		catch(Exception e)
		{
			throw e;
		}
	}
	
	public void modificarUsuario(String usuario, String contrasena, String nombre,TipoUsuario tipoUsuario, Date fechaNacimiento,String mail) throws Exception
	{
		Connection con = DataAccess.getConexion().getInstanciaDB();
		PreparedStatement s;
		try
		{
			s = con.prepareStatement("UPDATE usuario SET contrasena = ?,nombre = ?,tipo = ?,fechaNacimiento = ?,mail = ? WHERE usuario = ?");
					
			s.setString(1, contrasena);
			s.setString(2, nombre);
			s.setInt(3, tipoUsuario.getId());
			
			DateFormat df = new SimpleDateFormat("yyyy-M-d");
			String fechaParaSQL = df.format(fechaNacimiento);
			s.setDate(4, java.sql.Date.valueOf(fechaParaSQL));
			
			s.setString(5, mail);
			s.setString(6,usuario);
			
			s.execute();
			
			DataAccess.getConexion().cerrarConexion();
		}
		catch(Exception e) 
		{
			throw e;
		}
	}
	
	public void eliminarUsuario(String usuario) throws Exception
	{
		Connection con = DataAccess.getConexion().getInstanciaDB();
		PreparedStatement s;
		try
		{
			s = con.prepareStatement("UPDATE usuario SET estado = 0 where USUARIO = ?");
			s.setString(1,usuario);
			s.execute();
			
			DataAccess.getConexion().cerrarConexion();
		}
		catch(Exception e) 
		{
			throw e;
		}
	}

}
