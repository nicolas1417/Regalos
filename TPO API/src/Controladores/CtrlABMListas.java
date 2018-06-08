package Controladores;

import java.util.Date;
import java.util.List;

import Negocio.Lista;
import Negocio.TipoUsuario;
import Negocio.Usuario;
import Negocio.UsuarioDeLista;

public class CtrlABMListas {

	private List<Lista> listas;
	private static CtrlABMListas instancia;
	
	public static CtrlABMListas getInstancia()
	{
		if (instancia == null)
			instancia = new CtrlABMListas();
		return instancia;
	}
	
	public void cerrarLista(Lista lista){
		lista.cerrarLista();		
	}
	
	public void modificarLista(){
		/*Permite cambiar algo de la lista, pueden ser varias cosas y haber
		 * sobrecarga.*/
	}		
	
	public void crearLista(String fechaAgasajo, int montoParticipante, String fechaFin, String mail, String usuario) throws Exception
	{
		try
		{
			Lista.getInstancia().altaListas(fechaAgasajo,montoParticipante,fechaFin,mail,usuario);		
		}
		catch(Exception e)
		{
			throw e;
		}
	}
	
	public void agregarParticipante(UsuarioDeLista participante){
		Lista.getInstancia().agregarParticipante(participante);
	}
}
