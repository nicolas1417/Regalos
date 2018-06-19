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

	private Vector<Lista> listas;
	private static CtrlABMListas instancia;
	
	public static CtrlABMListas getInstancia()
	{
		if (instancia == null)
			instancia = new CtrlABMListas();
		return instancia;
	}
	
	public void cerrarLista(Lista lista) throws Exception
	{
		try
		{
			lista.cerrarLista();		
		}
		catch (Exception e)
		{
			throw e;
		}
	}
	
	public void modificarLista(){
		/*Permite cambiar algo de la lista, pueden ser varias cosas y haber
		 * sobrecarga.*/
	}		
	
	public void crearLista(java.util.Date fechaAga, int montoPart, java.util.Date fechaF, String correo, java.util.Date fechaI, String agasajado) throws Exception
	{
		try
		{
			Lista.getInstancia().altaListas(fechaAga,montoPart,fechaF,correo,fechaI,agasajado);		
		}
		catch(Exception e)
		{
			throw e;
		}
	}
	
	public void modificarLista(int idLista, java.util.Date fechaAga, int montoPart, java.util.Date fechaF, String correo, java.util.Date fechaI) throws Exception
	{
		try
		{
			Lista l = this.buscarLista(idLista);	
			
			l.modificarLista(idLista, fechaAga,montoPart,fechaF,correo,fechaI);
		}
		catch(Exception e)
		{
			
		}
	}
	
	public void eliminarLista(int idLista)
	{
		try
		{
			Lista l = this.buscarLista(idLista);	
			
			l.cerrarLista();
		}
		catch(Exception e)
		{
			
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
				
				this.agregarLista(v.elementAt(i));
			}
			
			return data;
		}
		catch(Exception e)
		{
			throw e;
		}
	}
	
	private void agregarLista(Lista l) 
	{
		if(listas == null)
			listas = new Vector<Lista>();
		
		listas.add(l);
	}
	
	public String[] buscarListaParaModificar(int lista) throws Exception
	{
		try
		{
			Lista l = buscarLista(lista);
			
			if (l != null && l.getEstado())
			{
				String[] s = new String[6];
				s[0] = String.valueOf(l.getFechaAgasajado());
				s[1] = String.valueOf(l.getMontoPartipante());
				s[2] = String.valueOf(l.getFechaFin());
				s[3] = l.getMail();
				s[4] = String.valueOf(l.getFechaInicio());
				s[5] = l.getAdmin().getUsuario().getUsuario();
				
				return s;
			}					
		}
		catch(Exception e)
		{
			throw new Exception("No se ha encontrado al usuario");
		}
		return null;	
	}
	
	private Lista buscarLista(int lista)
	{
		for (Lista l:listas)
			if (l.getIdLista() == lista)	
				return l;
		return null;
	}
	
}
