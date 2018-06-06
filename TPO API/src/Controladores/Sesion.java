package Controladores;

import Negocio.Usuario;
import Persistencia.AdmPersistenciaLogin;

public class Sesion {
	
	private  Usuario usuarioLogueado;
	
	private static Sesion instancia;
	
	public static Sesion getInstancia()
	{
		if (instancia == null)
			instancia = new Sesion();
		return instancia;
	}
	
	public void LoginUser(String usuario, String password) throws Exception
	{		
		try 
		{
			usuarioLogueado = AdmPersistenciaLogin.getInstancia().LoginUser(usuario, password);
		}
		catch (Exception e) 
		{
			throw e;
		} 
	}

}
