package Controladores;

import java.util.List;

import Negocio.Lista;
import Negocio.Usuario;
import Persistencia.AdmPersistenciaLogin;

public class ABMListas {

	private List<Lista> listas;
	private static ABMListas instancia;
	
	public static ABMListas getInstancia()
	{
		if (instancia == null)
			instancia = new ABMListas();
		return instancia;
	}
	
	public void crearLista(){
		//Crear una lista
	}
	
	public void eliminarLista(Lista lista){
		/*Saca el parametro pasado de la lista de esta clase*/
		
	}
	
	public void modificarLista(){
		/*Permite cambiar algo de la lista, pueden ser varias cosas y haber
		 * sobrecarga.*/
	}
	
	public Usuario LoginUser(String usuario, String password) throws Exception
	{		
		try 
		{
			return AdmPersistenciaLogin.getInstancia().LoginUser(usuario, password);
		}
		catch (Exception e) 
		{
			throw e;
		} 
	}
}
