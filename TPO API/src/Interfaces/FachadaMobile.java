package Interfaces;
import Controladores.CtrlABMUsuarios;

import java.util.Date;
import java.util.List;
import Controladores.CtrlABMListas;

public class FachadaMobile 
{
	public Object[][] buscarUsuario()
	{
		try {
			return CtrlABMUsuarios.getInstancia().buscarUsuarios();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public void crearUsuario(String usuario, String contrasena, String nombre, boolean idTipoUsuario, Date fechaNacimiento, String mail)
	{
		try {
			CtrlABMUsuarios.getInstancia().crearUsuario(usuario, contrasena, nombre, idTipoUsuario, fechaNacimiento, mail);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void modificarUsuario(String usuario, String contrasena,String nombre, boolean idTipoUsuario, Date fechaNacimiento, String mail)
	{
		try {
			CtrlABMUsuarios.getInstancia().modificarUsuario(usuario, contrasena, nombre, idTipoUsuario, fechaNacimiento, mail);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void crearLista(String nombreAgasajado, int montoPart, Date fechaInicio, String mail, Date fechaFin, Date fechaAgasajo,List<String> usuarios)
	{
		try {
			CtrlABMListas.getInstancia().crearLista(nombreAgasajado, montoPart, fechaInicio, mail, fechaFin, fechaAgasajo, usuarios);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
