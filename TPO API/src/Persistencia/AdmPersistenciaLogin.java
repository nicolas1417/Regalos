package Persistencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;

import Negocio.TipoUsuario;
import Negocio.Usuario;

public class AdmPersistenciaLogin {
	
	private static AdmPersistenciaLogin instancia;
	
	private AdmPersistenciaLogin()
	{
		
	}
	
	public static AdmPersistenciaLogin getInstancia()
	{
		if (instancia == null)
			instancia = new AdmPersistenciaLogin();
		return instancia;
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

}
