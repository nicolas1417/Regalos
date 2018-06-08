package Controladores;

import java.util.Date;
import java.util.List;

import Negocio.Lista;
import Negocio.TipoUsuario;
import Negocio.Usuario;

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
	
	public void crearLista(String fechaAgasajo, int montoParticipante, String fechaFin, String mail, String usuario) throws Exception
	{
		try
		{
			Lista.getInstancia().AltaListas(fechaAgasajo,montoParticipante,fechaFin,mail,usuario);
		
		}
		catch(Exception e)
		{
			throw e;
		}
	}
}
