package Controladores;

import java.util.Date;
import java.util.List;
import java.util.Vector;

import Negocio.Lista;
import Negocio.TipoUsuario;
import Negocio.Usuario;
import Negocio.UsuarioDeLista;
import Persistencia.ADMPersistenciaUsuarios;

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
	
	public void crearLista(java.util.Date fechaAga, int montoPart, java.util.Date fechaF, String correo, java.util.Date fechaI, String user) throws Exception
	{
		try
		{
			Lista.getInstancia().altaListas(fechaAga,montoPart,fechaF,correo,fechaI,user);		
		}
		catch(Exception e)
		{
			throw e;
		}
	}
	
	public void agregarParticipante(UsuarioDeLista participante){
		Lista.getInstancia().agregarParticipante(participante);
	}
	
	public Vector<Usuario> buscarUsuarios() throws Exception
	{
		return Usuario.getInstancia().buscarUsuarios();
	}
	
	public Object[][] buscarListas(Usuario usuario) throws Exception
	{
		try
		{
			Vector<Lista> v = Lista.getInstancia().buscarListas(usuario.getUsuario());
			
			Object[][] data = new Object[v.size()][8]; 
			
			for(int i=0;i<v.size();i++)
			{
				data[i][0] = v.elementAt(i).getIdLista();
				data[i][1] = v.elementAt(i).getNombreAgasajado();
				data[i][2] = v.elementAt(i).getFechaAgasajado();
				data[i][3] = v.elementAt(i).getMontoPartipante();
				data[i][4] = v.elementAt(i).getMontoRecaudado();
				data[i][5] = v.elementAt(i).getFechaInicio();
				data[i][6] = v.elementAt(i).getFechaFin();
				data[i][7] = v.elementAt(i).getDescEstado();
			}
			
			return data;
		}
		catch(Exception e)
		{
			throw e;
		}
	}
	
}
