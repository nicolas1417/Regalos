package Negocio;

import java.util.List;
import java.util.Vector;

import Persistencia.ADMPersistenciaUsuarios;

public class TipoUsuario {
	private int id;
	private String codigo;
	private List<String> tipos;
	//private static TipoUsuario instancia;
	
	/*public static TipoUsuario getInstancia()
	{
		if (instancia == null)
			instancia = new TipoUsuario();
		return instancia;
	}*/
	
	/*El id es el indice del vector y el nombre del tipo es el contenido de la
	 * posición*/
	
	public TipoUsuario()
	{
		try {
			tipos = ADMPersistenciaUsuarios.getInstancia().obtenerTiposDeUsuario();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/*public  obtenerTiposDeUsuario() throws Exception
	{
		try
		{
			return ADMPersistenciaUsuarios.getInstancia().obtenerTiposDeUsuario();
		}
		catch (Exception e) 
		{
			throw e;
		}
	}*/
	public List<String> getTipos(){
		return this.tipos;
	}
}
