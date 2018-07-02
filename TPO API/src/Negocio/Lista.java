package Negocio;

import java.util.Date;
import java.util.List;
import java.util.Vector;

import Controladores.CtrlSesion;
import Persistencia.ADMPersistenciaListas;
import Persistencia.ADMPersistenciaUsuarios;

public class Lista {
	public int idLista;
	public String nombreAgasajado;
	public Date fechaAgasajo;
	public List<UsuarioDeLista> participantes;
	public int montoPorParticipante;
	public int montoRecaudado;
	public Date fechaInicio;
	public Date fechaFin;
	public boolean estado;
	public String mail;
	public UsuarioDeLista administrador;
	
	//private static Lista instancia;
	
	public Lista()
	{
		
	}
	
	/*public static Lista getInstancia()
	{
		if (instancia == null)
			instancia = new Lista();
		return instancia;
	}*/
	
	
	/*Constructor de la lista, deberia decirse quien es el administrador, que fecha
	 * de inicio y fin tiene, monto que tiene que poner cada uno, nombre del agasajado
	 * */
	public Lista(int idLista, String nombreAgasajado, Date fechaAgasajo,int montoParticipante, int montoRecaudado,Date FechaInicio, Date FechaFin, boolean estado, String mail, UsuarioDeLista admin)
	{
		this.idLista = idLista;
		this.fechaAgasajo = fechaAgasajo;
		this.montoPorParticipante = montoParticipante;
		this.montoRecaudado = montoRecaudado;
		this.administrador = admin;
		this.fechaInicio = FechaInicio;
		this.fechaFin = FechaFin;
		this.nombreAgasajado = nombreAgasajado;
		this.estado = estado;
		this.mail = mail;
		this.administrador = admin;
	}
	
	public void agregarParticipante(UsuarioDeLista nuevo){
		this.participantes.add(nuevo);
	}
	
	//El echado que se pase debe estar en la lista
	public void quitarParticipante(UsuarioDeLista echado){
		try {
			this.participantes.remove(echado);
		} catch (Exception e) {
			System.out.println(e.getMessage());//Seria mejor poner un messagebox.
		}
		
	}
	
	public void altaListas(String nameAgasajado, int montoParticipante, java.util.Date fechaIni, String mail, java.util.Date fechaF, java.util.Date fechaAgasajo, List<String> usuarios, Usuario logueado) throws Exception
	{
		try
		{
			ADMPersistenciaListas.getInstancia().altaLista(nameAgasajado,montoParticipante,fechaIni,mail,fechaF,fechaAgasajo, usuarios, logueado);
		}
		catch(Exception e)
		{
			throw e;
		}
	}
	
	public List<List<String>> buscarMisListas(String logueado) throws Exception{
		try
		{
			return ADMPersistenciaListas.getInstancia().buscarMisListas(logueado);
		}
		catch(Exception e)
		{
			throw e;
		}
	}
	
	public Vector<Lista> buscarListas(String usuario) throws Exception
	{
		try
		{
			return ADMPersistenciaListas.getInstancia().buscarListas(usuario);
		}
		catch(Exception e)
		{
			throw e;
		}
	}
	
	public void cerrarLista() throws Exception
	{
		try
		{
			this.estado = false;
			ADMPersistenciaListas.getInstancia().eliminarLista(this.idLista);
		}
		catch(Exception e)
		{
			throw e;
		}
		
	}
	
	public Vector<Lista> notificarRegalos(Date fecha) throws Exception
	{
		try
		{
			Vector<Lista> v = buscarListas(CtrlSesion.getInstancia().getUsuarioLogueado().getUsuario());
			Vector<Lista> listasParaNotificar = new Vector<Lista>();
			
			for(int i=0;i<v.size();i++)
			{
				if(v.elementAt(i).getEstado() && v.elementAt(i).getFechaFin().compareTo(fecha) <= 0)
				{
					v.elementAt(i).cerrarLista();
					listasParaNotificar.add(v.elementAt(i));			
				}
			}
			
			return listasParaNotificar;
		}
		catch(Exception e)
		{
			throw e;
		}
		
	}
	
	public Vector<Lista> avisoCierre(Date fecha) throws Exception
	{
		try
		{
			Vector<Lista> v = buscarListas(CtrlSesion.getInstancia().getUsuarioLogueado().getUsuario());
			Vector<Lista> listasParaNotificar = new Vector<Lista>();
			
			for(int i=0;i<v.size();i++)
			{
				if(v.elementAt(i).getEstado() && v.elementAt(i).getFechaFin().compareTo(fecha) <= 0)
				{
					listasParaNotificar.add(v.elementAt(i));			
				}
			}
			
			return listasParaNotificar;
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
			ADMPersistenciaListas.getInstancia().modificarLista(idLista, fechaAga, montoPart, fechaF, correo, fechaI);
		}
		catch(Exception e)
		{
			throw e;
		}
	}
	
	public int getIdLista()
	{
		return this.idLista;
	}
	
	public String getNombreAgasajado()
	{
		return this.nombreAgasajado;
	}
	
	public Date getFechaAgasajado()
	{
		return this.fechaAgasajo;
	}
	
	public int getMontoPartipante()
	{
		return this.montoPorParticipante;
	}
	
	public int getMontoRecaudado()
	{
		return this.montoRecaudado;
	}
	
	public Date getFechaInicio()
	{
		return this.fechaInicio;
	}
	
	public Date getFechaFin()
	{
		return this.fechaFin;
	}

	public String getDescEstado()
	{
		return estado ? "Activa" : "Inhabilitada";
	}
	
	public boolean getEstado()
	{
		return this.estado;
	}
	
	public String getMail()
	{
		return this.mail;
	}
	
	public UsuarioDeLista getAdmin()
	{
		return this.administrador;
	}
}
