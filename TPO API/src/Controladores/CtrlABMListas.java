package Controladores;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Vector;

import Negocio.Lista;
import Negocio.Usuario;
import Negocio.UsuarioDeLista;

public class CtrlABMListas {
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
		
	public void crearLista(String nombreAgasajado, int montoPart, java.util.Date fechaInicio, String correo, java.util.Date fechaFin, java.util.Date fechaAgasajo, List<String> usuarios) throws Exception
	{
		try
		{
			Lista nueva = new Lista();
			Usuario logueado;
			logueado = CtrlSesion.getInstancia().getUsuarioLogueado();
			nueva.altaListas(nombreAgasajado,montoPart,fechaInicio,correo,fechaFin,fechaAgasajo, usuarios, logueado);		
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
	
	public List<String> buscarMisPagos() throws SQLException{
		Lista obj = new Lista();
		return obj.buscarMisPagos(CtrlSesion.getInstancia().getUsuarioLogueado().getUsuario());
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
	
	public List<String> buscarUsuariosParticipantes() throws Exception
	{
		List<String> l = new ArrayList<String>();
		Usuario obj = new Usuario();
		Vector<Usuario> v = obj.buscarUsuariosParticipantes();
		
		for(Usuario u:v)
			l.add(u.getUsuario());
		
		return l;
	}
	
	public List<List<String>> buscarMisListas() throws Exception
	{
		Lista obj = new Lista();
		String logueado = CtrlSesion.getInstancia().getUsuarioLogueado().getUsuario();
		return obj.buscarMisListas(logueado);
	}
	
	public Vector<Usuario> buscarUsuarios() throws Exception
	{
		Usuario obj = new Usuario();
		return obj.buscarUsuarios();
	}
	
	public boolean bajaParticipante(String id,String admin, String usuario) throws Exception {
		Lista obj = new Lista();
		return obj.bajaParticipante(id,admin,usuario);
	}
	
	public Object[][] buscarListas(Usuario usuario) throws Exception
	{
		try
		{
			Lista objLista = new Lista();
			Vector<Lista> v = objLista.buscarListas(usuario.getUsuario());
			
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
	
	private Lista buscarLista(int lista)  throws Exception
	{
		try
		{
			Lista objLista = new Lista();
			Vector<Lista> v = objLista.buscarListas(CtrlSesion.getInstancia().getUsuarioLogueado().getUsuario());
			
			for (Lista l:v)
				if (l.getIdLista() == lista)	
					return l;
			return null;
		}
		catch(Exception e)
		{
			throw e;
		}
	}
	
	public void avisoCierre() throws Exception
	{
		try
		{
			Calendar calendario = Calendar.getInstance();
			Date fecha = new Date();	
			calendario.setTime(fecha);
			calendario.add(Calendar.DAY_OF_WEEK, 7);
			fecha = calendario.getTime();
			
			Lista objLista = new Lista();
			Vector<Lista> listasParaNotificar = objLista.avisoCierre(fecha,CtrlSesion.getInstancia().getUsuarioLogueado().getUsuario());
			
			for(int i=0;i<listasParaNotificar.size();i++)
			{
				Lista l = listasParaNotificar.elementAt(i);
				
				for(int j=0;j<l.getParticipantes().size();j++)
					if (!l.getParticipantes().get(j).getPagoRealizado())
						CtrlMail.getInstancia().EnviarEmailAvisoCierre(l.getParticipantes().get(j).getUsuario().getMail());
				
			}	
		}
		catch(Exception e)
		{
			throw e;
		}
	}
	
	public void notificarRegalos() throws Exception
	{
		try
		{
			Lista objLista = new Lista();
			Vector<Lista> listasParaNotificar = objLista.notificarRegalos(new Date(),CtrlSesion.getInstancia().getUsuarioLogueado().getUsuario());
			
			for(int i=0;i<listasParaNotificar.size();i++)
			{
				Lista l = listasParaNotificar.elementAt(i);
				String participantes = "";
				
				for(int j=0;j<l.getParticipantes().size();j++)
				{
					UsuarioDeLista ul = l.getParticipantes().get(j);
					
					participantes = participantes + ul.getUsuario().getNombre() + ", ";
				}
				
				participantes = participantes.substring(0, participantes.length()-2);
				
				CtrlMail.getInstancia().EnviarEmailNotificarRegalos(l.getMail(),participantes);	
			}			
		}
		catch(Exception e)
		{
			throw e;
		}
	}
	
	public void avisoInicio() throws Exception
	{
		try
		{			
			Lista objLista = new Lista();
			Vector<Lista> listasParaNotificar = objLista.avisoInicio(new Date(),CtrlSesion.getInstancia().getUsuarioLogueado().getUsuario());
			
			for(int i=0;i<listasParaNotificar.size();i++)
			{
				Lista l = listasParaNotificar.elementAt(i);
				
				for(int j=0;j<l.getParticipantes().size();j++)
						CtrlMail.getInstancia().EnviarEmailAvisoInicio(l.getParticipantes().get(j).getUsuario().getMail());
				
			}	
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
			Lista l = new Lista();
			
			return l.RegistrarPago(usuario,idLista,monto,fechaMov);
		}
		catch(Exception e)
		{
			
		}
		return montoRecaudado;	
	}
}
