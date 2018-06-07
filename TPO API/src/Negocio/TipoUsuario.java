package Negocio;

import java.util.Vector;

import Persistencia.ADMPersistenciaUsuarios;

public class TipoUsuario {
	private int id;
	private String codigo;
	private static TipoUsuario instancia;
	
	public TipoUsuario()
	{
		
	}
	
	public static TipoUsuario getInstancia()
	{
		if (instancia == null)
			instancia = new TipoUsuario();
		return instancia;
	}
	
	public TipoUsuario(int id, String codigo)
	{
		this.id = id;
		this.codigo = codigo;
	}
	
	public Vector<TipoUsuario> obtenerTiposDeUsuario() throws Exception
	{
		try
		{
			return ADMPersistenciaUsuarios.getInstancia().obtenerTiposDeUsuario();
		}
		catch (Exception e) 
		{
			throw e;
		}
	}

	public String getCodigo() {
		return codigo;
	}
	
	public int getId() {
		return id;
	}
	
	
}
