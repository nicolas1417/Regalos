package Persistencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;

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
			PreparedStatement s = con.prepareStatement("select * from usuario where usuario = ? AND contrasena = ?");
			s.setString(1,usuario);
			s.setString(2, password);
			ResultSet result = s.executeQuery();
			
			if (!result.next())
				throw new Exception("El usuario o contraseña ingresada es incorrecta");	
			
			int id = result.getInt(1);
			String nombre = result.getString(2);
			String mail = result.getString(3);
			String user = result.getString(4);
			String contrasena = result.getString(5);
			Date fecha_nac = result.getDate(6);
			String estado = result.getString(7);
			
			Usuario u = new Usuario(id,nombre,user,contrasena,fecha_nac,(estado=="Activo")?true:false,mail);
			
			DataAccess.getConexion().cerrarConexion();
			
			return u;
		}
		catch (Exception e)
		{
			throw e;
		}
	}

}
