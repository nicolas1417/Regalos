package Controladores;

import Negocio.Usuario;
import Persistencia.ADMPersistenciaUsuarios;

public class CtrlSesion {
	
	private Usuario usuarioLogueado;
	
	private static CtrlSesion instancia;
	
	public static CtrlSesion getInstancia()
	{
		if (instancia == null)
			instancia = new CtrlSesion();
		return instancia;
	}
	
	public void LoginUser(String usuario, String password) throws Exception
	{		
		try 
		{
			usuarioLogueado = ADMPersistenciaUsuarios.getInstancia().LoginUser(usuario, password);	
		}
		catch (Exception e) 
		{
			throw e;
		} 
	}
	
	public Usuario getUsuarioLogueado() {
		return this.usuarioLogueado;
	}

}
