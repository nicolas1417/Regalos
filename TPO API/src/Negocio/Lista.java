package Negocio;

import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Vector;

import Persistencia.ADMPersistenciaListas;
import Persistencia.ADMPersistenciaPago;

public class Lista {
	private int idLista;
	private String nombreAgasajado;
	private Date fechaAgasajo;
	private List<UsuarioDeLista> participantes;
	private int montoPorParticipante;
	private int montoRecaudado;
	private Date fechaInicio;
	private Date fechaFin;
	private boolean estado;
	private String mail;
	private UsuarioDeLista administrador;
	
	public Lista()
	{
		
	}
	
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
	
	public List<String> buscarMisPagos(String logueado) throws SQLException{
		UsuarioDeLista obj = new UsuarioDeLista();
		return obj.buscarMisPagos(logueado);
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
	
	public boolean bajaParticipante(String id, String admin, String usuario) throws Exception {
		
		if (!usuario.equals(admin))
			return ADMPersistenciaListas.getInstancia().bajaParticipante(id, usuario);
		
		return false;
	}
	
	public Vector<Lista> buscarListas(String usuario) throws Exception
	{
		try
		{
			Vector<Lista> listas = ADMPersistenciaListas.getInstancia().buscarListas(usuario);
			
			for(Lista l : listas)
			{
				List<UsuarioDeLista> lul = obtenerParticipantes(l.getIdLista());
				
				l.participantes = lul;
			}
			
			return listas;
		}
		catch(Exception e)
		{
			throw e;
		}
	}
	
	public List<UsuarioDeLista> obtenerParticipantes(int idLista) throws Exception
	{
		List<UsuarioDeLista> participantes = null;
		try
		{
			participantes = ADMPersistenciaListas.getInstancia().obtenerParticipantes(idLista);
		}
		catch (Exception e)
		{
			throw e;
		}
		return participantes;
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
	
	public Vector<Lista> notificarRegalos(Date fecha,String usuario) throws Exception
	{
		try
		{
			Vector<Lista> v = buscarListas(usuario);
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
	
	public Vector<Lista> avisoCierre(Date fecha,String usuario) throws Exception
	{
		try
		{
			Vector<Lista> v = buscarListas(usuario);
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
	
	public Vector<Lista> avisoInicio(Date fecha,String usuario) throws Exception
	{
		try
		{
			Vector<Lista> v = buscarListas(usuario);
			Vector<Lista> listasParaNotificar = new Vector<Lista>();
			
			for(int i=0;i<v.size();i++)
			{
				if(v.elementAt(i).getEstado())
				{
					DateFormat daf = new SimpleDateFormat("yyyyMMdd");
					String fechaInicioLista = daf.format(v.elementAt(i).getFechaInicio());
					String fechaHoy = daf.format(fecha);
					
					if (fechaHoy.equals(fechaInicioLista))
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
	
	public int RegistrarPago(String usuario,int idLista,int monto,Date fechaMov) throws Exception
	{
		int montoRecaudado = 0;
		try
		{
			int idPago = ADMPersistenciaPago.getInstancia().registrarPago(monto, fechaMov);
			
			return ADMPersistenciaListas.getInstancia().RegistrarPago(usuario, idLista, monto,idPago);
		}
		catch(Exception e) 
		{
			
		}
		return montoRecaudado;
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
	
	public List<UsuarioDeLista> getParticipantes()
	{
		return this.participantes;
	}
	
}
